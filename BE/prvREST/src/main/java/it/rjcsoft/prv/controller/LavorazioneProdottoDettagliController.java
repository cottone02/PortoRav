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

import it.rjcsoft.prv.dto.lavorazioneprodottodettagli.LavorazioneDTO;
import it.rjcsoft.prv.dto.lavorazioneprodottodettagli.LavorazioneETipoLavorazioneDTO;
import it.rjcsoft.prv.dto.lavorazioneprodottodettagli.LavorazioneProdottoDettagliFullDTO;
import it.rjcsoft.prv.dto.lavorazioneprodottodettagli.LavorazioneProdottoDettagliInsertFullDTO;
import it.rjcsoft.prv.dto.lavorazioneprodottodettagli.TipoLavorazioneDTO;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.exceptions.InternalError;
import it.rjcsoft.prv.model.LavorazioneProdottoDettagli;
import it.rjcsoft.prv.repository.ILavorazioneProdottoDettagliRepository;
import it.rjcsoft.prv.service.ILavorazioneProdottoDettagliService;
import it.rjcsoft.prv.service.ILavorazioneService;
import it.rjcsoft.prv.service.ITipoLavorazioneService;
import it.rjcsoft.prv.utils.PrvComparatorUtils;
import it.rjcsoft.prv.utils.PrvUtils;

@RestController
@RequestMapping("/lavorazioneProdottoDettagli")
public class LavorazioneProdottoDettagliController extends BaseController {
	
	@Autowired
	private ITipoLavorazioneService tipoLavorazioneService;
	
	@Autowired
	private ILavorazioneService lavorazioneService;
	
	@Autowired
	private ILavorazioneProdottoDettagliService lavorazioneProdottoDettagliService;
	
	@Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_READER, ROLE_WRITER_EXT, ROLE_READER_EXT })
	@GetMapping("/tipoLavorazione")
	public ResponseEntity<Object> getAllTipoLavorazione() {
		MDC.put(PRV_TID, PrvUtils.generateTID());
		List<TipoLavorazioneDTO> tipoLavorazioneList = tipoLavorazioneService.findAllTipoLavorazione();
		if (tipoLavorazioneList.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			PrvComparatorUtils.sortTipoLavorazione(tipoLavorazioneList);
			return ResponseEntity.ok(tipoLavorazioneList);
		}
	}
	
	@Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_READER, ROLE_WRITER_EXT, ROLE_READER_EXT })
	@GetMapping("/lavorazione")
	public ResponseEntity<Object> getAllLavorazione() {
		MDC.put(PRV_TID, PrvUtils.generateTID());
		List<LavorazioneDTO> lavorazioneList = lavorazioneService.findAllLavorazione();
		if (lavorazioneList.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			PrvComparatorUtils.sortLavorazione(lavorazioneList);
			return ResponseEntity.ok(lavorazioneList);
		}
	}
	
	@Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_READER, ROLE_WRITER_EXT, ROLE_READER_EXT })
	@GetMapping("")
	public ResponseEntity<Page<LavorazioneProdottoDettagliFullDTO>> getAll(
			@QuerydslPredicate(root = LavorazioneProdottoDettagli.class, bindings = ILavorazioneProdottoDettagliRepository.class) Predicate predicate,
			@PageableDefault(sort = { "id" }) Pageable pageable) {
		MDC.put(PRV_TID, PrvUtils.generateTID());
		log.debug("START - predicate={}, pageable={}", predicate, pageable);
		Page<LavorazioneProdottoDettagliFullDTO> page = lavorazioneProdottoDettagliService.findAll(predicate, pageable);
		if (page.isEmpty()) {
			log.info("END - no content");
			return ResponseEntity.noContent().build();
		} else {
			log.info("END - 200, page={}", page);
			return ResponseEntity.ok(page);
		}

	}
	
	@Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_READER, ROLE_WRITER_EXT, ROLE_READER_EXT })
	@GetMapping("/lavorazioneETipoLavorazione")
	public ResponseEntity<Object> getAllLavorazioneETipoLavorazione() {
		MDC.put(PRV_TID, PrvUtils.generateTID());
		LavorazioneETipoLavorazioneDTO lavorazioneETipoList = lavorazioneProdottoDettagliService.findAllLavorazioneETipoLavorazione();
		if (lavorazioneETipoList.getListaLavorazioni().isEmpty() && lavorazioneETipoList.getListaTipoLavorazioni().isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(lavorazioneETipoList);
		}
	}
	
	
	@Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_WRITER_EXT })
	@PostMapping("")
	public ResponseEntity<Object> newLavorazioneProdottoDettagli(@RequestBody LavorazioneProdottoDettagliInsertFullDTO newLavorazioneProdottoDettagliInsertFullDTO) {
		MDC.put(PRV_TID, PrvUtils.generateTID());
		
		log.debug("START - newLavorazioneProdottoDettagliInsertFullDTO={}", newLavorazioneProdottoDettagliInsertFullDTO);

		LavorazioneProdottoDettagliInsertFullDTO saved = null;

		try {
			saved = lavorazioneProdottoDettagliService.save(newLavorazioneProdottoDettagliInsertFullDTO);
		} catch (InternalError e) {
			log.error("END - Internal error, message={}", e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		if (saved != null) {
			log.info("END - saved {}", saved);
			return new ResponseEntity<>(saved, HttpStatus.CREATED);
		} else {
			log.warn("END - not saved!");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_WRITER_EXT })
	@PostMapping("/saveLavorazione")
	public ResponseEntity<Object> newLavorazione(@RequestBody LavorazioneDTO newLavorazioneDTO) {
		
		MDC.put(PRV_TID, PrvUtils.generateTID());
		
		log.debug("START - newLavorazioneDTO={}", newLavorazioneDTO);

		LavorazioneDTO saved = null;

		try {
			saved = lavorazioneProdottoDettagliService.saveLavorazione(newLavorazioneDTO);
		} catch (InternalError e) {
			log.error("END - Internal error, message={}", e.getMessage(), e);
			return null;
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
	public ResponseEntity<Object> deleteLocById(@PathVariable int id) {
		MDC.put(PRV_TID, PrvUtils.generateTID());
		log.debug("START - delete cumuloLoc={}", id);
		try {
			lavorazioneProdottoDettagliService.deleteById(id);
		} catch (BaseEx e) {
			log.error("END - DELETE Internal error, message={}", e.getMessage(), e);
			return e.getResponseEntity();
		}
		return ResponseEntity.ok().build();
	}
	
	
	@Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_WRITER_EXT })
	@PutMapping("/update")
	public ResponseEntity<Object> update(@RequestBody LavorazioneProdottoDettagliInsertFullDTO lavorazioneProdottoDettagliInsertFullDTO) {
		MDC.put(PRV_TID, PrvUtils.generateTID());
		log.debug("START - DTO={}", lavorazioneProdottoDettagliInsertFullDTO);
		
		try {
			lavorazioneProdottoDettagliService.update(lavorazioneProdottoDettagliInsertFullDTO);
		} catch (BaseEx e) {
			log.error("END - UPDATE Internal error, message={}", e.getMessage(), e);
			return e.getResponseEntity();
		}
		return ResponseEntity.ok().build();
	}
	
	@Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_READER, ROLE_WRITER_EXT, ROLE_READER_EXT })
    @GetMapping("/lavorazione/{prodottoDettaglioId}")
    public ResponseEntity<Object> getAvailables(@PathVariable Integer prodottoDettaglioId) {
        MDC.put(PRV_TID, PrvUtils.generateTID());

        List<LavorazioneDTO> returnList = lavorazioneProdottoDettagliService
                .findAvailableLavorazioniInLavorazioneProdottoDettagli(prodottoDettaglioId);
        if (returnList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(returnList);
        }

    }

}
