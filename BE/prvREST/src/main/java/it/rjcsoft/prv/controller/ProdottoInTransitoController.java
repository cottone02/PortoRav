package it.rjcsoft.prv.controller;

import javax.validation.Valid;

import com.querydsl.core.types.Predicate;

import org.jboss.logging.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.rjcsoft.prv.dto.prodottointransito.ProdottoInTransitoFullDTO;
import it.rjcsoft.prv.dto.prodottointransito.ProdottoInTransitoUpdateDTO;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.model.ProdottoInTransito;
import it.rjcsoft.prv.repository.IProdottoInTransitoRepository;
import it.rjcsoft.prv.service.IProdottoInTransitoService;
import it.rjcsoft.prv.utils.PrvUtils;

@RestController
@RequestMapping("/prodottoInTransito")
public class ProdottoInTransitoController extends BaseController {

    @Autowired
    private IProdottoInTransitoService prodottoInTransitoService;

    @Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_READER, ROLE_WRITER_EXT,ROLE_READER_EXT })
    @GetMapping("")
    public ResponseEntity<Page<ProdottoInTransitoFullDTO>> getAll(
            @QuerydslPredicate(root = ProdottoInTransito.class, bindings = IProdottoInTransitoRepository.class) Predicate predicate,
            @PageableDefault(sort = { "id" }) Pageable pageable) {
        MDC.put(PRV_TID, PrvUtils.generateTID());
        log.debug("START - predicate={}, pageable={}", predicate, pageable);
        Page<ProdottoInTransitoFullDTO> page = prodottoInTransitoService.findAll(predicate, pageable);
        if (page.isEmpty()) {
            log.info("END - no content");
            return ResponseEntity.noContent().build();
        } else {
            log.info("END - 200, page={}", page);
            return ResponseEntity.ok(page);
        }
    }

    @Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_WRITER_EXT })
    @PutMapping("/update")
    public ResponseEntity<Object> update(@Valid @RequestBody ProdottoInTransitoUpdateDTO prodottoInTransitoUpdateDTO) {
        MDC.put(PRV_TID, PrvUtils.generateTID());
        log.debug("START - update prodottoInTransitoUpdateDTO={}", prodottoInTransitoUpdateDTO);

        try {
            prodottoInTransitoService.update(prodottoInTransitoUpdateDTO);
        } catch (BaseEx e) {
            log.error("END - UPDATE Internal error, message={}", e.getMessage(), e);
            return e.getResponseEntity();
        }
        return ResponseEntity.ok().build();
    }

}
