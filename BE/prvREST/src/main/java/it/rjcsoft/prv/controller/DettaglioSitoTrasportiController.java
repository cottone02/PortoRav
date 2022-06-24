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

import it.rjcsoft.prv.dto.dettagliositotrasporti.DettaglioSitoTrasportiFullDTO;
import it.rjcsoft.prv.dto.dettagliositotrasporti.DettaglioSitoTrasportiSaveDTO;
import it.rjcsoft.prv.dto.dettagliositotrasporti.DettaglioSitoTrasportiUpdateDTO;
import it.rjcsoft.prv.dto.dettagliositotrasporti.TipoTrasportoInDepositoDTO;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.model.DettaglioSitoTrasporti;
import it.rjcsoft.prv.repository.IDettaglioSitoTrasportiRepository;
import it.rjcsoft.prv.service.IDettaglioSitoTrasportiService;
import it.rjcsoft.prv.utils.PrvUtils;

@RestController
@RequestMapping("/dettaglioSitoTrasporti")
public class DettaglioSitoTrasportiController extends BaseController {

    @Autowired
    private IDettaglioSitoTrasportiService dettaglioSitoTrasportiService;

    @Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_READER, ROLE_WRITER_EXT,ROLE_READER_EXT })
    @GetMapping("")
    public ResponseEntity<Page<DettaglioSitoTrasportiFullDTO>> getAll(
            @QuerydslPredicate(root = DettaglioSitoTrasporti.class, bindings = IDettaglioSitoTrasportiRepository.class) Predicate predicate,
            @PageableDefault(sort = { "id" }) Pageable pageable) {
        MDC.put(PRV_TID, PrvUtils.generateTID());
        log.debug("START - predicate={}, pageable={}", predicate, pageable);
        Page<DettaglioSitoTrasportiFullDTO> page = dettaglioSitoTrasportiService.findAll(predicate, pageable);
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
    public ResponseEntity<Object> newDettaglio(@Valid @RequestBody DettaglioSitoTrasportiSaveDTO newDettaglio) {
        MDC.put(PRV_TID, PrvUtils.generateTID());
        log.debug("START - newDettaglio={}", newDettaglio);

        DettaglioSitoTrasportiFullDTO dto = new DettaglioSitoTrasportiFullDTO();

        try {
            dto = dettaglioSitoTrasportiService.save(newDettaglio);
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
            dettaglioSitoTrasportiService.deleteById(id);
        } catch (BaseEx e) {
            log.error("END - DELETE Internal error, message={}", e.getMessage(), e);
            return e.getResponseEntity();
        }
        return ResponseEntity.ok().build();
    }

    @Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_WRITER_EXT })
    @PutMapping("/update")
    public ResponseEntity<Object> updateDettaglio(@RequestBody DettaglioSitoTrasportiUpdateDTO dettaglioUpdateDTO) {
        MDC.put(PRV_TID, PrvUtils.generateTID());
        log.debug("START - update dettaglioUpdateDTO={}", dettaglioUpdateDTO);

        try {
            dettaglioSitoTrasportiService.update(dettaglioUpdateDTO);
        } catch (BaseEx e) {
            log.error("END - UPDATE Internal error, message={}", e.getMessage(), e);
            return e.getResponseEntity();
        }
        return ResponseEntity.ok().build();
    }

    @Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_READER, ROLE_WRITER_EXT, ROLE_READER_EXT })
    @GetMapping("/tipotrasportoindeposito/{prodottoDettaglioId}")
    public ResponseEntity<Object> getAvailables(@PathVariable Integer prodottoDettaglioId) {
        MDC.put(PRV_TID, PrvUtils.generateTID());

        List<TipoTrasportoInDepositoDTO> returnList = dettaglioSitoTrasportiService
                .findAvailableTrasportiInDeposito(prodottoDettaglioId);
        if (returnList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(returnList);
        }

    }

}
