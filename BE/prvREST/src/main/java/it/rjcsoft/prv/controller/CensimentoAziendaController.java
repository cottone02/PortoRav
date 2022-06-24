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

import it.rjcsoft.prv.dto.CodiceIstatAttivitaDTO;
import it.rjcsoft.prv.dto.censimentoazienda.CensimentoAziendaDTO;
import it.rjcsoft.prv.dto.censimentoazienda.CensimentoAziendaFullDTO;
import it.rjcsoft.prv.dto.censimentoazienda.CensimentoUpdateDTO;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.model.CensimentoAzienda;
import it.rjcsoft.prv.repository.ICensimentoAziendaRepository;
import it.rjcsoft.prv.service.ICensimentoAziendaService;
import it.rjcsoft.prv.utils.PrvUtils;

@RestController
@RequestMapping("/censimentiAzienda")
public class CensimentoAziendaController extends BaseController {

	@Autowired
	private ICensimentoAziendaService censimentoAziendaService;

	@Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_READER, ROLE_WRITER_EXT, ROLE_READER_EXT })
	@GetMapping("")
	public ResponseEntity<Page<CensimentoAziendaFullDTO>> getAll(
			@QuerydslPredicate(root = CensimentoAzienda.class, bindings = ICensimentoAziendaRepository.class) Predicate predicate,
			@PageableDefault(sort = { "id" }) Pageable pageable) {
		MDC.put(PRV_TID, PrvUtils.generateTID());
		log.debug("START - predicate={}, pageable={}", predicate, pageable);
		Page<CensimentoAziendaFullDTO> page = censimentoAziendaService.findAll(predicate, pageable);
		if (page.isEmpty()) {
			log.info("END - no content");
			return ResponseEntity.noContent().build();
		} else {
			log.info("END - 200, page={}", page);
			return ResponseEntity.ok(page);
		}

	}

	@Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_WRITER_EXT })
	@PostMapping("")
	public ResponseEntity<Object> newCensimentoAzienda(
			@Valid @RequestBody CensimentoAziendaDTO newCensimentoAziendaDTO) {
		MDC.put(PRV_TID, PrvUtils.generateTID());
		log.debug("START - newCensimentoAzienda={}", newCensimentoAziendaDTO);
		if (newCensimentoAziendaDTO.getId() != null) {
			log.warn("END - id NOT NULL, id={}", newCensimentoAziendaDTO.getId());
			return ResponseEntity.badRequest().build();
		}
		Integer userId = getLoggedUserId();
		if (userId == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		newCensimentoAziendaDTO.setCreatedBy(userId);
		CensimentoAziendaDTO savedAzienda = null;

		try {
			savedAzienda = censimentoAziendaService.save(newCensimentoAziendaDTO);
		} catch (BaseEx e) {
			log.error("END - Internal error, message={}", e.getMessage(), e);
			return e.getResponseEntity();
		}

		if (savedAzienda == null) {
			log.warn("END - not saved!");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} else {
			log.info("END - saved {}", savedAzienda);
			return new ResponseEntity<>(savedAzienda, HttpStatus.CREATED);
		}
	}

	@Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_WRITER_EXT })
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteCensimentoAziendaById(@PathVariable int id) {
		MDC.put(PRV_TID, PrvUtils.generateTID());
		log.debug("START - delete censimentoAziendaId={}", id);
		try {
			censimentoAziendaService.deleteById(id);
		} catch (BaseEx e) {
			log.error("END - DELETE Internal error, message={}", e.getMessage(), e);
			return e.getResponseEntity();
		}
		return ResponseEntity.ok().build();
	}

	@Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_WRITER_EXT })
	@PutMapping("/update")
	public ResponseEntity<Object> updateCensimento(@RequestBody CensimentoUpdateDTO censimentoUpdateDTO) {
		MDC.put(PRV_TID, PrvUtils.generateTID());
		log.debug("START - update censimentoAziendaDTO={}", censimentoUpdateDTO);

		Integer userId = getLoggedUserId();
		if (userId == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		censimentoUpdateDTO.setUpdatedBy(userId);

		try {
			censimentoAziendaService.update(censimentoUpdateDTO);
		} catch (BaseEx e) {
			log.error("END - UPDATE Internal error, message={}", e.getMessage(), e);
			return e.getResponseEntity();
		}
		return ResponseEntity.ok().build();
	}

	@Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_READER, ROLE_WRITER_EXT, ROLE_READER_EXT })
	@GetMapping("/codiciIstat")
	public ResponseEntity<Object> getCodiciIstat() {

		MDC.put(PRV_TID, PrvUtils.generateTID());

		List<CodiceIstatAttivitaDTO> codiciList = censimentoAziendaService.findAllCodiciIstat();
		if (codiciList.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(codiciList);
		}

	}

	@Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_READER, ROLE_WRITER_EXT, ROLE_READER_EXT, ROLE_GUEST })
	@GetMapping(path = "/getTotal")
    public ResponseEntity<Object> getTotalNumber() {
        MDC.put(PRV_TID, PrvUtils.generateTID());

        return ResponseEntity.ok(censimentoAziendaService.getTotalNumber());
    }
}
