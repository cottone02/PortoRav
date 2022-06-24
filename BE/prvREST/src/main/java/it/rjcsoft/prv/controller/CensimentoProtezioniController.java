package it.rjcsoft.prv.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.jboss.logging.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;

import it.rjcsoft.prv.dto.censimentoprotezioni.CensimentoProtezioneDTO;
import it.rjcsoft.prv.dto.censimentoprotezioni.CensimentoProtezioniFullDTO;
import it.rjcsoft.prv.dto.censimentoprotezioni.ProtezioneContenimentoDTO;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.model.CensimentoProtezioniContenimento;
import it.rjcsoft.prv.repository.ICensimentoProtezioniRepository;
import it.rjcsoft.prv.service.ICensimentoProtezioniService;
import it.rjcsoft.prv.utils.PrvComparatorUtils;
import it.rjcsoft.prv.utils.PrvUtils;

@RestController
@RequestMapping("/censimentiProtezioni")
public class CensimentoProtezioniController extends BaseController {

	@Autowired
	private ICensimentoProtezioniService censimentoProtezioniService;
	
	@Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_READER, ROLE_WRITER_EXT, ROLE_READER_EXT })
	@GetMapping("")
	public ResponseEntity<Page<CensimentoProtezioniFullDTO>> getAll(
			@QuerydslPredicate(root = CensimentoProtezioniContenimento.class, bindings = ICensimentoProtezioniRepository.class) Predicate predicate,
			@PageableDefault(sort = { "contenimento.tipologia" }) Pageable pageable) {
		MDC.put(PRV_TID, PrvUtils.generateTID());
		log.debug("START - predicate={}, pageable={}", predicate, pageable);
		Page<CensimentoProtezioniFullDTO> page = censimentoProtezioniService.findAll(predicate, pageable);
		if (page.isEmpty()) {
			log.info("END - no content");
			return ResponseEntity.noContent().build();
		} else {
			log.info("END - 200, page={}", page);
			return ResponseEntity.ok(page);
		}

	}
	
	@Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_WRITER_EXT })
	@PostMapping("/{censimentoAziendaId}/")
	public ResponseEntity<Object> newCensimentoProtezione(@PathVariable int censimentoAziendaId,
			@Valid @RequestBody CensimentoProtezioneDTO newCensimentoProtezioneDTO) {
		MDC.put(PRV_TID, PrvUtils.generateTID());
		log.debug("START - newCensimentoProtezioneDTO={}", newCensimentoProtezioneDTO);

		CensimentoProtezioneDTO saved = null;

		try {
			saved = censimentoProtezioniService.save(newCensimentoProtezioneDTO, censimentoAziendaId);
		} catch (BaseEx e) {
			log.error("END - Internal error, message={}", e.getMessage(), e);
			return e.getResponseEntity();
		}

		if (saved == null) {
			log.warn("END - not saved!");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} else {
			log.info("END - saved {}", saved);
			return new ResponseEntity<>(saved, HttpStatus.CREATED);
		}
	}

	@Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_WRITER_EXT })
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteCensimentoProtezioneById(@PathVariable int id) {
		MDC.put(PRV_TID, PrvUtils.generateTID());
		log.debug("START - delete censimentoProtezioneId={}", id);
		try {
			censimentoProtezioniService.deleteById(id);
		} catch (BaseEx e) {
			log.error("END - DELETE Internal error, message={}", e.getMessage(), e);
			return e.getResponseEntity();
		}
		return ResponseEntity.ok().build();
	}

	@Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_WRITER_EXT })
	@PutMapping("/update/{censimentoAziendaId}/{id}")
	public ResponseEntity<Object> updateCensimentoProtezione(
			@PathVariable int censimentoAziendaId, @PathVariable int id,@Positive @RequestBody String note) {
		MDC.put(PRV_TID, PrvUtils.generateTID());
		log.debug("START - update id={}, censimentoAziendaId={}, note={}", id, censimentoAziendaId, note);
                
		try {
			censimentoProtezioniService.update(id, censimentoAziendaId, note);
		} catch (BaseEx e) {
			log.error("END - UPDATE Internal error, message={}", e.getMessage(), e);
			return e.getResponseEntity();
		}
		return ResponseEntity.ok().build();
	}

	@Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_READER, ROLE_WRITER_EXT, ROLE_READER_EXT })
	@GetMapping("/protezioni")
	public ResponseEntity<Object> getProtezioni() {

		MDC.put(PRV_TID, PrvUtils.generateTID());

		List<ProtezioneContenimentoDTO> protezioneList = censimentoProtezioniService.findAllDotazioni();
		if (protezioneList.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			PrvComparatorUtils.sortProtezioni(protezioneList);
			return ResponseEntity.ok(protezioneList);
		}

	}

}
