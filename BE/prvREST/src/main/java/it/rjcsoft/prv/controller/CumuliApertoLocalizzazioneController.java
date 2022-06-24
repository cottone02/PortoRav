package it.rjcsoft.prv.controller;

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

import it.rjcsoft.prv.dto.cumuliaperto.CumuliApertoLocalizzazioneDTO;
import it.rjcsoft.prv.dto.cumuliaperto.CumuliApertoLocalizzazioneSaveDTO;
import it.rjcsoft.prv.dto.cumuliaperto.CumuliApertoLocalizzazioneUpdateDTO;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.model.CumuliApertoLocalizzazione;
import it.rjcsoft.prv.repository.ICumuliApertoLocalizzazioneRepository;
import it.rjcsoft.prv.service.ICumuliApertoLocalizzazioneService;
import it.rjcsoft.prv.utils.PrvUtils;

@RestController
@RequestMapping("/cumuli")
public class CumuliApertoLocalizzazioneController extends BaseController {
    
    @Autowired
	private ICumuliApertoLocalizzazioneService cumuliApertoService;

	@Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_READER, ROLE_WRITER_EXT, ROLE_READER_EXT })
	@GetMapping("")
	public ResponseEntity<Page<CumuliApertoLocalizzazioneDTO>> getAll(
			@QuerydslPredicate(root = CumuliApertoLocalizzazione.class, bindings = ICumuliApertoLocalizzazioneRepository.class) Predicate predicate,
			@PageableDefault(sort = { "id" }) Pageable pageable) {
		MDC.put(PRV_TID, PrvUtils.generateTID());
		log.debug("START - predicate={}, pageable={}", predicate, pageable);
		Page<CumuliApertoLocalizzazioneDTO> page = cumuliApertoService.findAll(predicate, pageable);
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
	public ResponseEntity<Object> newCumulo(
			@Valid @RequestBody CumuliApertoLocalizzazioneSaveDTO newCumuliDTO) {
		MDC.put(PRV_TID, PrvUtils.generateTID());
		log.debug("START - newCumuliDTO={}", newCumuliDTO);

		CumuliApertoLocalizzazioneDTO savedCumuli = null;

		try {
			savedCumuli = cumuliApertoService.save(newCumuliDTO);
		} catch (BaseEx e) {
			log.error("END - Internal error, message={}", e.getMessage(), e);
			return e.getResponseEntity();
		}

		if (savedCumuli == null) {
			log.warn("END - not saved!");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} else {
			log.info("END - saved {}", savedCumuli);
			return new ResponseEntity<>(savedCumuli, HttpStatus.CREATED);
		}
	}
	
	@Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_WRITER_EXT })
	@PutMapping("/update")
    public ResponseEntity<Object> updateCumuli(@RequestBody CumuliApertoLocalizzazioneUpdateDTO updateCumuliDTO) {
        MDC.put(PRV_TID, PrvUtils.generateTID());
        log.debug("START - update updateCumuliDTO={}", updateCumuliDTO);
        try {
        	cumuliApertoService.update(updateCumuliDTO);
        } catch (BaseEx e) {
            log.error("END - UPDATE Internal error, message={}", e.getMessage(), e);
            return e.getResponseEntity();
        }
        return ResponseEntity.ok().build();
    }
	

	@Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_WRITER_EXT })
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteLocById(@PathVariable int id) {
		MDC.put(PRV_TID, PrvUtils.generateTID());
		log.debug("START - delete cumuloLoc={}", id);
		try {
			cumuliApertoService.deleteById(id);
		} catch (BaseEx e) {
			log.error("END - DELETE Internal error, message={}", e.getMessage(), e);
			return e.getResponseEntity();
		}
		return ResponseEntity.ok().build();
	}


}
