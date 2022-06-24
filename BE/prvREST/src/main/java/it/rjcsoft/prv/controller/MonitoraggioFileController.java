package it.rjcsoft.prv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.rjcsoft.prv.service.IMonitoraggiAriaService;
import it.rjcsoft.prv.service.IMonitoraggiMareeService;

@RestController
@RequestMapping("/monitoraggioController")
public class MonitoraggioFileController extends BaseController {

    @Autowired
    private IMonitoraggiAriaService monitoraggioAriaService;

    @Autowired
    private IMonitoraggiMareeService monitoraggioMareeService;

    @GetMapping("/aria/{idFile}")
    public ResponseEntity<Object> insertMonitoraggioAria(@PathVariable Integer idFile) {
        try {
            log.debug("START - idFile={}", idFile);
            if (monitoraggioAriaService.insertMonitoraggioAria(idFile)) {
                log.info("END - inserimento riuscito");
                return new ResponseEntity<>("inserimento riuscito", HttpStatus.OK);
            } else {
                log.info("END - Inserimento fallito");
                return new ResponseEntity<>("Inserimento fallito", HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            log.error("END - Internal error, message={}", e.getMessage());
            return new ResponseEntity<>("FORMATO FILE NON SUPPORTATO", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/maree/{idFile}")
    public ResponseEntity<Object> insertMonitoraggioMaree(@PathVariable Integer idFile) {
        try {
            log.debug("START - idFile={}", idFile);
            if (monitoraggioMareeService.insertMonitoraggioMaree(idFile)) {
            log.info("END - inserimento riuscito");
            return new ResponseEntity<>("inserimento riuscito", HttpStatus.OK);
        } else {
            log.info("END - Inserimento fallito");
            return new ResponseEntity<>("Inserimento fallito", HttpStatus.NO_CONTENT);
        }

        } catch (Exception e) {
            log.error("END - Internal error, message={}", e.getMessage());
            return new ResponseEntity<>("FORMATO FILE NON SUPPORTATO", HttpStatus.BAD_REQUEST);
        }
    }

}
