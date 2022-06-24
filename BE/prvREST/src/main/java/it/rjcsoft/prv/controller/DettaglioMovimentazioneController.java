package it.rjcsoft.prv.controller;

import javax.validation.Valid;

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

import it.rjcsoft.prv.dto.movimentazione.DettaglioMovimentazioneDTO;
import it.rjcsoft.prv.dto.movimentazione.DettaglioMovimentazioneSaveDTO;
import it.rjcsoft.prv.dto.movimentazione.DettaglioMovimentazioneUpdateDTO;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.model.DettaglioMovimentazione;
import it.rjcsoft.prv.repository.IDettaglioMovimentazioneRepository;
import it.rjcsoft.prv.service.IDettaglioMovimentazioneService;
import it.rjcsoft.prv.utils.PrvUtils;

@RestController
@RequestMapping("/dettagliomovimentazione")
public class DettaglioMovimentazioneController extends BaseController {
    
    @Autowired
	private IDettaglioMovimentazioneService dettaglioMovimentazioneService;

	@Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_READER, ROLE_WRITER_EXT, ROLE_READER_EXT })
	@GetMapping("")
	public ResponseEntity<Page<DettaglioMovimentazioneDTO>> getAll(
			@QuerydslPredicate(root = DettaglioMovimentazione.class, bindings = IDettaglioMovimentazioneRepository.class) Predicate predicate,
			@PageableDefault(sort = { "id" }) Pageable pageable) {
		MDC.put(PRV_TID, PrvUtils.generateTID());
		log.debug("START - predicate={}, pageable={}", predicate, pageable);
		Page<DettaglioMovimentazioneDTO> page = dettaglioMovimentazioneService.findAll(predicate, pageable);
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
	public ResponseEntity<Object> newMovimentazione(
			@Valid @RequestBody DettaglioMovimentazioneSaveDTO newMovimentazioneDTO) {
		MDC.put(PRV_TID, PrvUtils.generateTID());
		log.debug("START - newMovimentazioneDTO={}", newMovimentazioneDTO);

		DettaglioMovimentazioneDTO savedMovimentazione = null;

		try {
			savedMovimentazione = dettaglioMovimentazioneService.save(newMovimentazioneDTO);
		} catch (BaseEx e) {
			log.error("END - Internal error, message={}", e.getMessage(), e);
			return e.getResponseEntity();
		}

		if (savedMovimentazione == null) {
			log.warn("END - not saved!");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} else {
			log.info("END - saved {}", savedMovimentazione);
			return new ResponseEntity<>(savedMovimentazione, HttpStatus.CREATED);
		}
	}
	
	@Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_WRITER_EXT })
	@PutMapping("/update")
    public ResponseEntity<Object> updateMovimentazioni(@RequestBody DettaglioMovimentazioneUpdateDTO updateMovimentazioneDTO) {
        MDC.put(PRV_TID, PrvUtils.generateTID());
        log.debug("START - update updateMovimentazioneDTO={}", updateMovimentazioneDTO);
        try {
        	dettaglioMovimentazioneService.update(updateMovimentazioneDTO);
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
		log.debug("START - delete dettaglioMovimentazione={}", id);
		try {
			dettaglioMovimentazioneService.deleteById(id);
		} catch (BaseEx e) {
			log.error("END - DELETE Internal error, message={}", e.getMessage(), e);
			return e.getResponseEntity();
		}
		return ResponseEntity.ok().build();
	}


}
