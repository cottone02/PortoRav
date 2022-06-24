package it.rjcsoft.prv.controller;

import java.util.List;

import javax.validation.Valid;

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

import it.rjcsoft.prv.dto.censimentointerventi.CensimentoInterventIMitigazioneDTO;
import it.rjcsoft.prv.dto.censimentointerventi.CensimentoInterventiDTO;
import it.rjcsoft.prv.dto.censimentointerventi.CensimentoInterventiFullDTO;
import it.rjcsoft.prv.dto.censimentointerventi.CensimentoInterventiUpdateDTO;
import it.rjcsoft.prv.dto.censimentointerventi.InterventoDTO;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.model.CensimentoInterventiMitigazione;
import it.rjcsoft.prv.repository.ICensimentoInterventiRepository;
import it.rjcsoft.prv.service.ICensimentoInterventiService;
import it.rjcsoft.prv.utils.PrvComparatorUtils;
import it.rjcsoft.prv.utils.PrvUtils;

@RestController
@RequestMapping("/censimentiInterventi")
public class CensimentoInterventiController extends BaseController {

	@Autowired
	private ICensimentoInterventiService censimentoInterventiService;
	
	@Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_READER, ROLE_WRITER_EXT, ROLE_READER_EXT })
	@GetMapping("")
	public ResponseEntity<Page<CensimentoInterventiFullDTO>> getAll(
			@QuerydslPredicate(root = CensimentoInterventiMitigazione.class, bindings = ICensimentoInterventiRepository.class) Predicate predicate,
			@PageableDefault(sort = { "intervento.tipologia" }) Pageable pageable) {
		MDC.put(PRV_TID, PrvUtils.generateTID());
		log.debug("START - predicate={}, pageable={}", predicate, pageable);
		Page<CensimentoInterventiFullDTO> page = censimentoInterventiService.findAll(predicate, pageable);
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
	public ResponseEntity<Object> newCensimentoInterventi(@PathVariable int censimentoAziendaId,
			@Valid @RequestBody CensimentoInterventIMitigazioneDTO newCensimentoInterventIMitigazioneDTO) {
		MDC.put(PRV_TID, PrvUtils.generateTID());
		log.debug("START - newCensimentoInterventIMitigazioneDTO={}", newCensimentoInterventIMitigazioneDTO);

		CensimentoInterventiDTO saved = null;

		try {
			saved = censimentoInterventiService.save(newCensimentoInterventIMitigazioneDTO, censimentoAziendaId);
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
	public ResponseEntity<Object> deleteCensimentoInterventiById(@PathVariable int id) {
		MDC.put(PRV_TID, PrvUtils.generateTID());
		log.debug("START - delete censimentoInterventiId={}", id);
		try {
			censimentoInterventiService.deleteById(id);
		} catch (BaseEx e) {
			log.error("END - DELETE Internal error, message={}", e.getMessage(), e);
			return e.getResponseEntity();
		}
		return ResponseEntity.ok().build();
	}
	
	@Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_WRITER_EXT })
	@PutMapping("/update")
	public ResponseEntity<Object> updateCensimentoInterventi(@RequestBody CensimentoInterventiUpdateDTO updateDTO) {
		MDC.put(PRV_TID, PrvUtils.generateTID());
		log.debug("START - updateDTO={}", updateDTO);

		try {
			censimentoInterventiService.update(updateDTO);
		} catch (BaseEx e) {
			log.error("END - UPDATE Internal error, message={}", e.getMessage(), e);
			return e.getResponseEntity();
		}
		return ResponseEntity.ok().build();
	}
	
	@Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_READER, ROLE_WRITER_EXT, ROLE_READER_EXT })
	@GetMapping("/interventi")
	public ResponseEntity<Object> getInterventi() {

		MDC.put(PRV_TID, PrvUtils.generateTID());

		List<InterventoDTO> interventoList = censimentoInterventiService.findAllInterventi();
		if (interventoList.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			PrvComparatorUtils.sortInterventi(interventoList);
			return ResponseEntity.ok(interventoList);
		}

	}

}
