package it.rjcsoft.prv.controller;

import java.util.List;

import com.querydsl.core.types.Predicate;

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

import it.rjcsoft.prv.dto.censimentoprodotti.CensimentoProdottiDTO;
import it.rjcsoft.prv.dto.censimentoprodotti.CensimentoProdottiFullDTO;
import it.rjcsoft.prv.dto.censimentoprodotti.CensimentoProdottiUpdateDTO;
import it.rjcsoft.prv.dto.censimentoprodotti.ClasseGranulometricaDTO;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.model.CensimentoProdotti;
import it.rjcsoft.prv.repository.ICensimentoProdottiRepository;
import it.rjcsoft.prv.service.ICensimentoProdottiService;
import it.rjcsoft.prv.utils.PrvUtils;

@RestController
@RequestMapping("/censimentiProdotti")
public class CensimentoProdottiController extends BaseController {

	@Autowired
	private ICensimentoProdottiService censimentoProdottiService;

	@Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_READER, ROLE_WRITER_EXT, ROLE_READER_EXT })
	@GetMapping("")
	public ResponseEntity<Page<CensimentoProdottiFullDTO>> getAll(
			@QuerydslPredicate(root = CensimentoProdotti.class, bindings = ICensimentoProdottiRepository.class) Predicate predicate,
			@PageableDefault(sort = { "prodotto.nome","prodotto.polverosita" }) Pageable pageable) {
		MDC.put(PRV_TID, PrvUtils.generateTID());
		log.debug("START - predicate={}, pageable={}", predicate, pageable);
		Page<CensimentoProdottiFullDTO> page = censimentoProdottiService.findAll(predicate, pageable);
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
	public ResponseEntity<Object> newCensimentoProdotti(@PathVariable int censimentoAziendaId,
			@RequestBody Integer prodottoId) {
		MDC.put(PRV_TID, PrvUtils.generateTID());
		log.debug("START - prodottoId={}, censimentoAziendaId={}", prodottoId, censimentoAziendaId);

		CensimentoProdottiDTO saved = null;

		try {
			saved = censimentoProdottiService.save(prodottoId, censimentoAziendaId);
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
	public ResponseEntity<Object> deleteCensimentoProdottiById(@PathVariable int id) {
		MDC.put(PRV_TID, PrvUtils.generateTID());
		log.debug("START - delete censimentoProdottiId={}", id);
		try {
			censimentoProdottiService.deleteById(id);
		} catch (BaseEx e) {
			log.error("END - DELETE Internal error, message={}", e.getMessage(), e);
			return e.getResponseEntity();
		}
		return ResponseEntity.ok().build();
	}

	@Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_WRITER_EXT })
	@PutMapping("/update")
	public ResponseEntity<Object> updateCensimentoProdotti(@RequestBody CensimentoProdottiUpdateDTO updateDTO) {
		MDC.put(PRV_TID, PrvUtils.generateTID());
		log.debug("START - updateDTO={}", updateDTO);

		try {
			censimentoProdottiService.update(updateDTO);
		} catch (BaseEx e) {
			log.error("END - UPDATE Internal error, message={}", e.getMessage(), e);
			return e.getResponseEntity();
		}
		return ResponseEntity.ok().build();
	}

	@Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_READER, ROLE_WRITER_EXT, ROLE_READER_EXT })
	@GetMapping("/classiGranulometriche")
	public ResponseEntity<Object> getClassiGranulometriche() {

		MDC.put(PRV_TID, PrvUtils.generateTID());

		List<ClasseGranulometricaDTO> classiGranulometricheList = censimentoProdottiService
				.findAllClassiGranulometriche();
		if (classiGranulometricheList.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(classiGranulometricheList);
		}

	}

}
