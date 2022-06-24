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

import it.rjcsoft.prv.dto.dettagliositodepositi.DettaglioSitoDepositiFullDTO;
import it.rjcsoft.prv.dto.dettagliositodepositi.DettaglioSitoDepositiSaveDTO;
import it.rjcsoft.prv.dto.dettagliositodepositi.DettaglioSitoDepositiUpdateDTO;
import it.rjcsoft.prv.dto.dettagliositodepositi.TipoDepositoStoccaggioDTO;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.model.DettaglioSitoDepositi;
import it.rjcsoft.prv.repository.IDettaglioSitoDepositiRepository;
import it.rjcsoft.prv.service.IDettaglioSitoDepositiService;
import it.rjcsoft.prv.utils.PrvUtils;

@RestController
@RequestMapping("/dettaglioSitoDepositi")
public class DettaglioSitoDepositiController extends BaseController {

    @Autowired
    private IDettaglioSitoDepositiService dettaglioSitoDepositiService;

    @Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_READER, ROLE_WRITER_EXT,ROLE_READER_EXT })
    @GetMapping("")
    public ResponseEntity<Page<DettaglioSitoDepositiFullDTO>> getAll(
            @QuerydslPredicate(root = DettaglioSitoDepositi.class, bindings = IDettaglioSitoDepositiRepository.class) Predicate predicate,
            @PageableDefault(sort = { "id" }) Pageable pageable) {
        MDC.put(PRV_TID, PrvUtils.generateTID());
        log.debug("START - predicate={}, pageable={}", predicate, pageable);
        Page<DettaglioSitoDepositiFullDTO> page = dettaglioSitoDepositiService.findAll(predicate, pageable);
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
    public ResponseEntity<Object> newDettaglio(@Valid @RequestBody DettaglioSitoDepositiSaveDTO newDettaglio) {
        MDC.put(PRV_TID, PrvUtils.generateTID());
        log.debug("START - newDettaglio={}", newDettaglio);

        DettaglioSitoDepositiFullDTO dto = new DettaglioSitoDepositiFullDTO();

        try {
            dto = dettaglioSitoDepositiService.save(newDettaglio);
        } catch (BaseEx e) {
            log.error("END - Internal error, message={}", e.getMessage(), e);
            return e.getResponseEntity();
        }

        if (dto == null) {
            log.warn("END - not saved!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } else {
            log.info("END - saved {}", dto);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        }
    }

    @Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_WRITER_EXT })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteDettaglio(@PathVariable int id) {
        MDC.put(PRV_TID, PrvUtils.generateTID());
        log.debug("START - delete dettaglio={}", id);
        try {
            dettaglioSitoDepositiService.deleteById(id);
        } catch (BaseEx e) {
            log.error("END - DELETE Internal error, message={}", e.getMessage(), e);
            return e.getResponseEntity();
        }
        return ResponseEntity.ok().build();
    }

    @Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_WRITER_EXT })
    @PutMapping("/update")
    public ResponseEntity<Object> updateDettaglio(@RequestBody DettaglioSitoDepositiUpdateDTO dettaglioUpdateDTO) {
        MDC.put(PRV_TID, PrvUtils.generateTID());
        log.debug("START - update dettaglioUpdateDTO={}", dettaglioUpdateDTO);

        try {
            dettaglioSitoDepositiService.update(dettaglioUpdateDTO);
        } catch (BaseEx e) {
            log.error("END - UPDATE Internal error, message={}", e.getMessage(), e);
            return e.getResponseEntity();
        }
        return ResponseEntity.ok().build();
    }
    
    @Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_READER, ROLE_WRITER_EXT, ROLE_READER_EXT })
    @GetMapping("/tipodepositoinstoccaggio")
    public ResponseEntity<Object> getAvailables() {
        MDC.put(PRV_TID, PrvUtils.generateTID());

        try {
			List<TipoDepositoStoccaggioDTO> returnList = dettaglioSitoDepositiService.findAllDepositoInStoccaggio();
			if (returnList.isEmpty()) {
			    return ResponseEntity.noContent().build();
			} else {
			    return ResponseEntity.ok(returnList);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

    }
}
