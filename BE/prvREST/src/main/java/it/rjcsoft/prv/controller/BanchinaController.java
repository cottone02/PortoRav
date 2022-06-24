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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.rjcsoft.prv.dto.banchina.BanchinaDTO;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.model.Banchina;
import it.rjcsoft.prv.repository.IBanchinaRepository;
import it.rjcsoft.prv.service.IAuthenticationFacadeService;
import it.rjcsoft.prv.service.IBanchinaService;
import it.rjcsoft.prv.utils.PrvUtils;

@RestController
@RequestMapping("/banchina")
public class BanchinaController extends BaseController {

    @Autowired
    private IBanchinaService banchinaService;

    @Autowired
    private IAuthenticationFacadeService authenticationFacadeService;

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAll() {
        log.info("START - banchina -> getAll");
        MDC.put(PRV_TID, PrvUtils.generateTID());
        List<BanchinaDTO> banchina = banchinaService.getAllBanchine();
        if (banchina.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(banchina, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Object> getAllByPage(
            @QuerydslPredicate(root = Banchina.class, bindings = IBanchinaRepository.class) Predicate predicate,
            @PageableDefault(sort = { "id" }) Pageable pageable) {
        MDC.put(PRV_TID, PrvUtils.generateTID());
        log.info("Get Banchine by page to user={}", authenticationFacadeService.getAuthentication().getPrincipal());

        Page<BanchinaDTO> page = banchinaService.getAllByPage(predicate, pageable);
        if (page.isEmpty()) {
            log.info("END - no content");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            log.info("END - 200,page={}", page);
            return new ResponseEntity<>(page, HttpStatus.OK);
        }
    }

    @PostMapping("/new")
    public ResponseEntity<Object> newBanchina(@RequestBody BanchinaDTO banchinaDto) {
        MDC.put(PRV_TID, PrvUtils.generateTID());

        log.info("CREAZIONE BANCHINA={}", banchinaDto);

        BanchinaDTO saved = null;
        try {
            saved = banchinaService.save(banchinaDto);
        } catch (BaseEx e) {
            log.error("END - InternalError, message={}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (saved != null) {
            log.info("SAVED - banchina={}", saved);
            return new ResponseEntity<>(saved, HttpStatus.OK);
        } else
            log.error("ERROR - banchina not saved={}", saved);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateBanchina(@RequestBody BanchinaDTO banchinaUpd) {
        MDC.put(PRV_TID, PrvUtils.generateTID());

        log.info("UPDATE Banchina={}", banchinaUpd);

        boolean saved = false;
        try {
            saved = banchinaService.update(banchinaUpd);
            log.info("CREATED banchina {}", banchinaUpd);
            if (saved)
                return new ResponseEntity<>(banchinaUpd, HttpStatus.OK);
            else
                return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();

        } catch (Exception e) {
            log.error("END - not updated");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteBanchina(@PathVariable int id) {
        MDC.put(PRV_TID, PrvUtils.generateTID());

        log.info("DELETE Banchina={}", id);
        try {
            banchinaService.delete(id);
            log.info("DELETED");
        } catch (BaseEx e) {
            log.error("END - not deleted, message = {}", e.getMessage());
            return e.getResponseEntity();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
