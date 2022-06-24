package it.rjcsoft.prv.controller;

import java.util.List;
import java.util.Optional;

import com.querydsl.core.types.Predicate;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.MDC;
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

import it.rjcsoft.prv.dto.azienda.AziendaFullDTO;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.model.Azienda;
import it.rjcsoft.prv.model.Utente;
import it.rjcsoft.prv.repository.IAziendaRepository;
import it.rjcsoft.prv.service.IAziendaService;
import it.rjcsoft.prv.utils.PrvConverterUtils;
import it.rjcsoft.prv.utils.PrvUtils;

@RestController
@RequestMapping("/azienda")
public class AziendaController extends BaseController {

    @Autowired
    private IAziendaService aziendaService;

    @Autowired
    private IAziendaRepository aziendaRepository;

    //@Secured({ ROLE_ADMIN })
    @GetMapping("")
    public ResponseEntity<Page<AziendaFullDTO>> getAll(
            @QuerydslPredicate(root = Azienda.class, bindings = IAziendaRepository.class) Predicate predicate,
            @PageableDefault(sort = { "id" }) Pageable pageable) {
        MDC.put(PRV_TID, PrvUtils.generateTID());
        log.debug("START - predicate={}, pageable={}", predicate, pageable);

        Page<AziendaFullDTO> page = aziendaService.getAll(predicate, pageable);
        if (page.isEmpty()) {
            log.info("END - no content");
            return ResponseEntity.noContent().build();
        } else {
            log.info("END - 200, page={}", page);
            return ResponseEntity.ok(page);
        }
    }

    @Secured({ ROLE_ADMIN })
    @PostMapping
    @RequestMapping("/newAzienda")
    public ResponseEntity<Object> createAzienda(@RequestBody AziendaFullDTO aziendaDTO) {
        MDC.put(PRV_TID, PrvUtils.generateTID());

        log.info("CREAZIONE AZIENDA={}", aziendaDTO);

        AziendaFullDTO saved = null;
        try {
            saved = aziendaService.createAzienda(aziendaDTO);
        } catch (BaseEx e) {
            log.error("END - Internal Error, message {}", e.getMessage());
            return e.getResponseEntity();
        }
        if (saved != null) {
            log.info("END - azienda creata {}", saved);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } else {
            log.info("END - azienda not saved {}", saved);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Secured({ ROLE_ADMIN })
    @PutMapping("/updateAzienda")
    public ResponseEntity<Object> updateAzienda(@RequestBody AziendaFullDTO aziendaUpd) {
        MDC.put(PRV_TID, PrvUtils.generateTID());

        log.info("UPDATE Azienda={}", aziendaUpd);

        boolean saved = false;
        try {
            saved = aziendaService.updateAzienda(aziendaUpd);
            log.info("CREATED azienda {}", aziendaUpd);
            if (saved)
                return new ResponseEntity<>(aziendaUpd, HttpStatus.OK);
            else
                return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();

        } catch (Exception e) {
            log.error("END - not updated");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Secured({ ROLE_ADMIN })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteAzienda(@PathVariable int id) {
        MDC.put(PRV_TID, PrvUtils.generateTID());

        log.info("DELETE Azienda={}", id);
        try {
            aziendaService.deleteAzienda(id);
            log.info("DELETED");
        } catch (BaseEx e) {
            log.error("END - not deleted, message = {}", e.getMessage());
            return e.getResponseEntity();
        }
        return ResponseEntity.ok().build();
    }

    @Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_READER, ROLE_WRITER_EXT, ROLE_READER_EXT, ROLE_GUEST })
    @GetMapping("/getUtentiById/{id}")
    public ResponseEntity<Object> getUtentiByIdAzienda(@PathVariable int id) {
        MDC.put(PRV_TID, PrvUtils.generateTID());

        List<Utente> utenti = null;
        try {
            utenti = aziendaService.getUtentiByIdAzienda(id);
        } catch (BaseEx ex) {
            log.error("ERROR azienda con ID = {} not found", id);
        }
        if (CollectionUtils.isEmpty(utenti))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        else
            return new ResponseEntity<>(utenti, HttpStatus.OK);
    }

    @GetMapping("/getAziendaById/{id}")
    public ResponseEntity<Object> getAziendaById(@PathVariable int id) {
        MDC.put(PRV_TID, PrvUtils.generateTID());
        Azienda finalAzienda = new Azienda();
        try {
            Optional<Azienda> azienda = aziendaRepository.findById(id);
            log.info("Azienda con id = {} Trovata!", id);
            if (azienda.isPresent()) {
                PrvConverterUtils.copyProperties(finalAzienda, azienda.get());
                log.info("Proprietà Copiate!");
            }
            return new ResponseEntity<>(finalAzienda, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Errore azienda con id ={} non trovata", id);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
