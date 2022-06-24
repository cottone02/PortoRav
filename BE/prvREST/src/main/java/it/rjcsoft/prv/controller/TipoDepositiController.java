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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.rjcsoft.prv.dto.dettagliositodepositi.TipoDepositoStoccaggioDTO;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.model.TipoDepositoStoccaggio;
import it.rjcsoft.prv.repository.ITipoDepositoStoccaggioRepository;
import it.rjcsoft.prv.service.ITipoDepositoStoccaggioService;
import it.rjcsoft.prv.utils.PrvUtils;

@RestController
@RequestMapping("/tipoDeposito")
public class TipoDepositiController extends BaseController {

    @Autowired
    private ITipoDepositoStoccaggioService tipoDepositoStocaggioService;

    @GetMapping("")
    public ResponseEntity<Page<TipoDepositoStoccaggioDTO>> getAll(
            @QuerydslPredicate(root = TipoDepositoStoccaggio.class, bindings = ITipoDepositoStoccaggioRepository.class) Predicate predicate,
            @PageableDefault(sort = {"id"}) Pageable pageable) {
        MDC.put(PRV_TID, PrvUtils.generateTID());
        log.debug("START - predicate={}, pageable={}", predicate, pageable);
        Page<TipoDepositoStoccaggioDTO> page = tipoDepositoStocaggioService.findAll(predicate, pageable);
        if (page.isEmpty()) {
            log.info("END - no content");
            return ResponseEntity.noContent().build();
        } else {
            log.info("END - 200, page={}", page);
            return ResponseEntity.ok(page);
        }

    }

    @PostMapping("")
    public ResponseEntity<Object> newDepositoStoccaggio(@Valid @RequestBody TipoDepositoStoccaggioDTO newDeposito) {
        MDC.put(PRV_TID, PrvUtils.generateTID());
        log.debug("START - newTipoStoccaggio={}", newDeposito);
        if (newDeposito.getId() != null) {
            log.warn("END - id NOT NULL, id={}", newDeposito.getId());
            return ResponseEntity.badRequest().build();
        }
        TipoDepositoStoccaggioDTO dto = null;
        try {
            dto = tipoDepositoStocaggioService.save(newDeposito);
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteDepositoStoccaggio(@PathVariable int id) {
        MDC.put(PRV_TID, PrvUtils.generateTID());
        log.debug("START - delete dettaglio={}", id);
        try {
            tipoDepositoStocaggioService.deleteById(id);
        } catch (BaseEx e) {
            log.error("END - DELETE Internal error, message={}", e.getMessage(), e);
            return e.getResponseEntity();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateDepositoStoccaggio(@RequestBody TipoDepositoStoccaggioDTO newDeposito) {
        MDC.put(PRV_TID, PrvUtils.generateTID());
        log.debug("START - update TipoDepositoStoccaggioDTO={}", newDeposito);
        try {
            tipoDepositoStocaggioService.update(newDeposito);
        } catch (BaseEx e) {
            log.error("END - UPDATE Internal error, message={}", e.getMessage(), e);
            return e.getResponseEntity();
        }
        return ResponseEntity.ok().build();
    }

}
