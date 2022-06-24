package it.rjcsoft.prv.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.jboss.logging.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.service.IProdottiAllegatiService;
import it.rjcsoft.prv.utils.PrvUtils;

@RestController
@RequestMapping("/prodottiAllegati")
public class ProdottiAllegatiController extends BaseController {

    @Autowired
    private IProdottiAllegatiService prodottiAllegatiService;

    @Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_WRITER_EXT })
    @PostMapping("/{schedaId}/")
    public ResponseEntity<Object> saveCensimentoProdottoAllegato(@PathVariable Integer schedaId,
            @RequestParam("file") MultipartFile[] files) {
        MDC.put(PRV_TID, PrvUtils.generateTID());
        log.debug("START - id={}, nAllegati={}", schedaId, files.length);
        try {
            prodottiAllegatiService.save(schedaId, files);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("END - Internal error, message={}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_READER, ROLE_WRITER_EXT,ROLE_READER_EXT })
    @GetMapping("/{id}")
    public ResponseEntity<Object> downloadAllegato(@PathVariable Integer id) throws IOException {

        MDC.put(PRV_TID, PrvUtils.generateTID());
        log.debug("START - download file with id={}", id);
        File returnFile;
        try {
            returnFile = prodottiAllegatiService.downloadFileById(id);
            if (returnFile == null || !returnFile.exists()) {
                return ResponseEntity.noContent().build();
            } else {
                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_DISPOSITION,
                        String.format("attachment; filename=%s", returnFile.getName()));
                headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
                headers.add("Pragma", "no-cache");
                headers.add("Expires", "0");

                ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(returnFile.toPath()));
                long length = returnFile.length();
                return ResponseEntity.ok().headers(headers).contentLength(length)
                        .contentType(MediaType.MULTIPART_FORM_DATA).body(resource);
            }

        } catch (BaseEx e) {
            log.error("END - Internal error, message={}", e.getMessage(), e);
            return e.getResponseEntity();
        }
    }

    @Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_WRITER_EXT })
    @DeleteMapping("/deletefile/{id}/")
    public ResponseEntity<Object> deleteByAllegatoId(@PathVariable Integer id) {
        MDC.put(PRV_TID, PrvUtils.generateTID());
        log.debug("START - delete prodottoAllegato={}", id);
        try {
            prodottiAllegatiService.deleteById(id);
        } catch (BaseEx e) {
            log.error("END - DELETE Internal error, message={}", e.getMessage(), e);
            return e.getResponseEntity();
        }
        return ResponseEntity.ok().build();
    }

    @Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_WRITER_EXT })
    @DeleteMapping("/deleteallegati/{schedaId}/")
    public ResponseEntity<Object> deleteBySchedaId(@PathVariable Integer schedaId) {
        MDC.put(PRV_TID, PrvUtils.generateTID());
        log.debug("START - delete prodottoAllegato={}", schedaId);
        try {
            prodottiAllegatiService.deleteBySchedaId(schedaId);
        } catch (BaseEx e) {
            log.error("END - DELETE Internal error, message={}", e.getMessage(), e);
            return e.getResponseEntity();
        }
        return ResponseEntity.ok().build();
    }

}
