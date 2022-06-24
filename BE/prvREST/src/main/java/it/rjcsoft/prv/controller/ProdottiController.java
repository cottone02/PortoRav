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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;

import it.rjcsoft.prv.dto.censimentoprodotti.ProdottoDTO;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.model.Prodotto;
import it.rjcsoft.prv.repository.IProdottoRepository;
import it.rjcsoft.prv.service.IProdottoService;
import it.rjcsoft.prv.utils.PrvUtils;

@RestController
@RequestMapping("/prodotti")
public class ProdottiController extends BaseController {
	@Autowired
	private IProdottoService prodottoService;
	
	@Secured({ ROLE_ADMIN })
	@GetMapping("")
	public ResponseEntity<Page<ProdottoDTO>> getAll(
			@QuerydslPredicate(root = Prodotto.class, bindings = IProdottoRepository.class) Predicate predicate,
			@PageableDefault(sort = { "nome","polverosita" }) Pageable pageable) {
		MDC.put(PRV_TID, PrvUtils.generateTID());
		log.debug("START - predicate={}, pageable={}", predicate, pageable);
		Page<ProdottoDTO> page = prodottoService.findAll(predicate, pageable);
		if (page.isEmpty()) {
			log.info("END - no content");
			return ResponseEntity.noContent().build();
		} else {
			log.info("END - 200, page={}", page);
			return ResponseEntity.ok(page);
		}

	}
	
	@Secured({ ROLE_ADMIN })
	@PostMapping("")
	public ResponseEntity<Object> newProdotto(
			@Valid @RequestBody ProdottoDTO newProdottoDTO) {
		MDC.put(PRV_TID, PrvUtils.generateTID());
		log.debug("START - newProdotto={}", newProdottoDTO);
		if (newProdottoDTO.getId() != null) {
			log.warn("END - id NOT NULL, id={}", newProdottoDTO.getId());
			return ResponseEntity.badRequest().build();
		}
		
		ProdottoDTO savedProdotto = null;
		try {
			savedProdotto = prodottoService.save(newProdottoDTO);
		} catch (BaseEx e) {
			log.error("END - Internal error, message={}", e.getMessage(), e);
			return e.getResponseEntity();
		}

		if (savedProdotto == null) {
			log.warn("END - not saved!");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} else {
			log.info("END - saved {}", savedProdotto);
			return new ResponseEntity<>(savedProdotto, HttpStatus.CREATED);
		}
	}
	
	@Secured({ ROLE_ADMIN })
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteProdottoById(@PathVariable int id) {
		MDC.put(PRV_TID, PrvUtils.generateTID());
		log.debug("START - delete ProdottoId={}", id);
		try {
			prodottoService.deleteById(id);
		} catch (BaseEx e) {
			log.error("END - DELETE Internal error, message={}", e.getMessage(), e);
			return e.getResponseEntity();
		}
		return ResponseEntity.ok().build();
	}
	
	//@Secured({ ROLE_ADMIN })
	// @PutMapping("/update/{id}")
	// public ResponseEntity<Object> updateProdotto(@PathVariable Integer id, @RequestBody String polverosita) {
	// 	MDC.put(PRV_TID, PrvUtils.generateTID());
	// 	log.debug("START - update id={}, polverosita={}", id, polverosita);

	// 	try {
	// 		prodottoService.update(id, polverosita);
	// 	} catch (BaseEx e) {
	// 		log.error("END - UPDATE Internal error, message={}", e.getMessage(), e);
	// 		return e.getResponseEntity();
	// 	}
	// 	return ResponseEntity.ok().build();
	// }

	@Secured({ ROLE_ADMIN })
	@GetMapping("/polverosita")
	public ResponseEntity<Object> getPolverosita() {

		MDC.put(PRV_TID, PrvUtils.generateTID());

		List<String> polverositaList = prodottoService.findAllPolverosita();
		if (polverositaList.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(polverositaList);
		}

	}
	

}
