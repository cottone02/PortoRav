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

import it.rjcsoft.prv.dto.censimentodotazioniazienda.CensimentoDotazioniAziendaDTO;
import it.rjcsoft.prv.dto.censimentodotazioniazienda.CensimentoDotazioniDTO;
import it.rjcsoft.prv.dto.censimentodotazioniazienda.CensimentoDotazioniFullDTO;
import it.rjcsoft.prv.dto.censimentodotazioniazienda.DotazioneDTO;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.model.CensimentoDotazioniAzienda;
import it.rjcsoft.prv.repository.ICensimentoDotazioniRepository;
import it.rjcsoft.prv.service.ICensimentoDotazioniService;
import it.rjcsoft.prv.utils.PrvComparatorUtils;
import it.rjcsoft.prv.utils.PrvUtils;

@RestController
@RequestMapping("/censimentiDotazioni")
public class CensimentoDotazioniController extends BaseController {

	@Autowired
	private ICensimentoDotazioniService censimentoDotazioneService;
	
	@Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_READER, ROLE_WRITER_EXT, ROLE_READER_EXT })
	@GetMapping("")
	public ResponseEntity<Page<CensimentoDotazioniFullDTO>> getAll(
			@QuerydslPredicate(root = CensimentoDotazioniAzienda.class, bindings = ICensimentoDotazioniRepository.class) Predicate predicate,
			@PageableDefault(sort = { "dotazione.attrezzatura" }) Pageable pageable) {
		MDC.put(PRV_TID, PrvUtils.generateTID());
		log.debug("START - predicate={}, pageable={}", predicate, pageable);
		Page<CensimentoDotazioniFullDTO> page = censimentoDotazioneService.findAll(predicate, pageable);
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
	public ResponseEntity<Object> newCensimentoDotazione(@PathVariable int censimentoAziendaId,
			@Valid @RequestBody CensimentoDotazioniAziendaDTO newCensimentoDotazioniAziendaDTO) {
		MDC.put(PRV_TID, PrvUtils.generateTID());
		log.debug("START - newCensimentoDotazioniAziendaDTO={}", newCensimentoDotazioniAziendaDTO);

		CensimentoDotazioniDTO saved = null;

		try {
			saved = censimentoDotazioneService.save(newCensimentoDotazioniAziendaDTO, censimentoAziendaId);
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
	public ResponseEntity<Object> deleteCensimentoDotazioneById(@PathVariable int id) {
		MDC.put(PRV_TID, PrvUtils.generateTID());
		log.debug("START - delete censimentoDotazioneId={}", id);
		try {
			censimentoDotazioneService.deleteById(id);
		} catch (BaseEx e) {
			log.error("END - DELETE Internal error, message={}", e.getMessage(), e);
			return e.getResponseEntity();
		}
		return ResponseEntity.ok().build();
	}
	
	@Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_WRITER_EXT })
	@PutMapping("/update/{censimentoAziendaId}/{id}")
	public ResponseEntity<Object> updateCensimentoDotazioni(@Positive @RequestBody int quantita,
			@PathVariable int censimentoAziendaId, @PathVariable int id) {
		MDC.put(PRV_TID, PrvUtils.generateTID());
		log.debug("START - update id={}, censimentoAziendaId={}, quantita={}", id, censimentoAziendaId, quantita);

		try {
			censimentoDotazioneService.update(id, censimentoAziendaId, quantita);
		} catch (BaseEx e) {
			log.error("END - UPDATE Internal error, message={}", e.getMessage(), e);
			return e.getResponseEntity();
		}
		return ResponseEntity.ok().build();
	}
	
	
	@Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_READER, ROLE_WRITER_EXT, ROLE_READER_EXT })
	@GetMapping("/dotazioni")
	public ResponseEntity<Object> getDotazioni() {

		MDC.put(PRV_TID, PrvUtils.generateTID());

		List<DotazioneDTO> dotazioneList = censimentoDotazioneService.findAllDotazioni();
		if (dotazioneList.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			PrvComparatorUtils.sortDotazioni(dotazioneList);
			return ResponseEntity.ok(dotazioneList);
		}

	}

}
