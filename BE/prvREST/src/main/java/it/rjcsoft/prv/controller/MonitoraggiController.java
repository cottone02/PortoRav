package it.rjcsoft.prv.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.querydsl.core.types.Predicate;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;
import org.jboss.logging.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import it.rjcsoft.prv.config.PrvRestConfig;
import it.rjcsoft.prv.dto.monitoraggi.MonitoraggiFullDTO;
import it.rjcsoft.prv.model.Monitoraggi;
import it.rjcsoft.prv.repository.IMonitoraggiRepository;
import it.rjcsoft.prv.service.IAuthenticationFacadeService;
import it.rjcsoft.prv.service.IMonitoraggiService;
import it.rjcsoft.prv.utils.PrvConverterUtils;
import it.rjcsoft.prv.utils.PrvFileUtils;
import it.rjcsoft.prv.utils.PrvUtils;

@RestController
@RequestMapping("/monitoraggi")
public class MonitoraggiController extends BaseController {

    @Autowired
    private IMonitoraggiService monitoringService;

    @Autowired
    private IAuthenticationFacadeService authenticationFacadeService;

    @Autowired
    private PrvRestConfig prvRestConfig;

    @Autowired
    private IMonitoraggiRepository monitoraggioRepository;

    @Secured({ROLE_ADMIN, ROLE_WRITER})
    @PostMapping("/uploadMonitoraggio")
    public ResponseEntity<Object> uploadMonitoring(@RequestParam("file") MultipartFile[] files,
            @RequestParam Map<String, Object> parameters) {

        MDC.put(PRV_TID, PrvUtils.generateTID());
        log.info("Uploading monitoringDTO={}, user={}", parameters,
                authenticationFacadeService.getAuthentication().getPrincipal());

        DateTimeConverter dtConverter = new DateConverter();
        dtConverter.setPattern("yyyy-MM-dd'T'HH:mm");
        ConvertUtils.register(dtConverter, Date.class);

        MonitoraggiFullDTO monitoraggio = PrvConverterUtils.convert(parameters, MonitoraggiFullDTO.class);

        List<String> filePath = new ArrayList<>(); //
        List<String> fileName = new ArrayList<>();
        boolean error = false;
        boolean salvati = false;

        Integer idSaved = null;

        for (MultipartFile file : files) {
            try {
                File fileSalvato = PrvFileUtils.saveFile(file,
                        prvRestConfig.getMonitoringBasePath() + "" + monitoraggio.getCompanyId());
                fileName.add(fileSalvato.getName());
                filePath.add(fileSalvato.getAbsolutePath());
            } catch (Exception e) {
                error = true;
                break;
            }
        }
        // Elimino i file creati in caso di errori
        if (error) {
            filePath.forEach(pathString -> {
                Path path = Paths.get(pathString);
                try {
                    Files.delete(path);
                } catch (IOException e) {
                    log.error("FILE path={} NOT DELETED, e={}", pathString, e.getMessage());
                }
            });
        } // se non ho errori registro i file salvati
        else {
            try {
                idSaved = monitoringService.nuovoMonitoraggio(monitoraggio);
                salvati = monitoringService.registerFile(idSaved, fileName);
            } catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }
        }
        if (salvati) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }

        return ResponseEntity.badRequest().build();
    }

    @Secured({ROLE_ADMIN, ROLE_WRITER, ROLE_READER})
    @GetMapping("/getAll")
    public ResponseEntity<Page<MonitoraggiFullDTO>> getAll(
            @QuerydslPredicate(root = Monitoraggi.class, bindings = IMonitoraggiRepository.class) Predicate predicate,
            @PageableDefault(sort = {"id"}) Pageable pageable) {
        MDC.put(PRV_TID, PrvUtils.generateTID());
        log.info("Getting monitoring to user={}", authenticationFacadeService.getAuthentication().getPrincipal());
        log.debug("START - predicate={}, pageable={}", predicate, pageable);

        Page<MonitoraggiFullDTO> page = monitoringService.getAll(predicate, pageable);
        if (page.isEmpty()) {
            log.info("END - no content");
            return ResponseEntity.noContent().build();
        } else {
            log.info("END - 200, page{}", page);
            return ResponseEntity.ok(page);
        }
    }

    @Secured({ROLE_ADMIN, ROLE_WRITER})
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteMonitoring(@PathVariable int id) {
        MDC.put(PRV_TID, PrvUtils.generateTID());

        log.info("START - Delete monitoring={}, user={}", id,
                authenticationFacadeService.getAuthentication().getPrincipal());
        ResponseEntity<Object> response = null;
        try {
            response = monitoringService.deleteById(id);
            log.info("END - response={}", response); // Qui non ci arriva
            return response;
        } catch (Exception e) {
            log.error("ERRORE");
            return response;
        }
    }

    @Secured({ROLE_ADMIN, ROLE_WRITER, ROLE_READER})
    @GetMapping(path = "/download/{id}")
    public ResponseEntity<Resource> download(@PathVariable int id) throws IOException {

        MDC.put(PRV_TID, PrvUtils.generateTID());
        log.info("START - GET monitoring={} by user={}", id,
                authenticationFacadeService.getAuthentication().getPrincipal());
        List<String> fileName = monitoringService.getFileNamesById(id);
        File zip = null;
        Monitoraggi mon = monitoraggioRepository.findById(id).get();
        if (!fileName.isEmpty()) {
            zip = PrvFileUtils.zipFile(prvRestConfig.getMonitoringBasePath() + "/" + mon.getCompanyId(),
                    prvRestConfig.getMonitoringBasePath() + "" + id + ".zip", fileName);
        }

        if (zip == null || !zip.exists()) {
            log.warn("ZipFile NOT CREATED because zip={}", zip);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        log.info("Download file path={}", zip.getAbsolutePath());
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=%s", zip.getName()));
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(zip.toPath()));
        long length = zip.length();

        try {
            Files.delete(zip.toPath());
        } catch (IOException e) {
            log.warn("FILE path={} NOT DELETED, e={}", zip.getAbsolutePath(), e.getMessage());
        }

        log.info("END - Download document={}", zip.getAbsolutePath());

        return ResponseEntity.ok().headers(headers).contentLength(length).contentType(MediaType.MULTIPART_FORM_DATA)
                .body(resource);
    }

    @Secured({ROLE_ADMIN, ROLE_WRITER, ROLE_READER})
    @GetMapping(path = "/download/file/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable int id, @RequestParam String fileName)
            throws IOException {
        MDC.put(PRV_TID, PrvUtils.generateTID());
        log.info("START - GET monitoringId={}, fileName={}, user={}", id, fileName,
                authenticationFacadeService.getAuthentication().getPrincipal());
        File file = monitoringService.getFileByNameAndId(id, fileName);
        if (file == null || !file.exists()) {
            log.warn("File {} NOT FOUND", fileName);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.info("Download file path={}", file.getAbsolutePath());
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=%s", file.getName()));
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(file.toPath()));
        long length = file.length();
        log.info("END - Download monitoring={}", file.getAbsolutePath());
        return ResponseEntity.ok().headers(headers).contentLength(length).contentType(MediaType.MULTIPART_FORM_DATA)
                .body(resource);
    }

    @Secured({ROLE_ADMIN, ROLE_WRITER, ROLE_READER})
    @GetMapping(path = "/fileNames/{id}")
    public ResponseEntity<Object> getFileNames(@PathVariable int id) {

        MDC.put(PRV_TID, PrvUtils.generateTID());

        log.info("START - GET fileNames id={}, user={}", id,
                authenticationFacadeService.getAuthentication().getPrincipal());

        List<String> fileNames = monitoringService.getFileNamesById(id);

        if (fileNames.isEmpty()) {
            log.warn("END - NOT FOUND");
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            log.info("END - fileNames={}", fileNames);
            return new ResponseEntity<>(fileNames, HttpStatus.OK);
        }

    }

    // Per le prossime parti devo implementare l'invio delle mail!!
    // Che implementerò successivamente!
    @Secured({ROLE_ADMIN, ROLE_WRITER})
    @DeleteMapping(path = "/deleteFile/{id}/{fileName}")
    public ResponseEntity<Object> deleteFile(@PathVariable int id, @PathVariable String fileName) {

        MDC.put(PRV_TID, PrvUtils.generateTID());

        log.info("START - DELETE fileNames={}, id={}, user={}", fileName, id,
                authenticationFacadeService.getAuthentication().getPrincipal());

        boolean isDeleted = monitoringService.deleteFileByNameId(fileName, id);
        log.info("END - response={}", isDeleted);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Secured({ROLE_ADMIN, ROLE_WRITER})
    @PutMapping("/updateDocInfo")
    public ResponseEntity<Object> updateMonitoringInfo(@RequestBody MonitoraggiFullDTO monitoringDTO) {

        MDC.put(PRV_TID, PrvUtils.generateTID());

        log.info("START - Update monitoring={}, user={}", monitoringDTO,
                authenticationFacadeService.getAuthentication().getPrincipal());

        ResponseEntity<Object> response = monitoringService.updateMonitoraggio(monitoringDTO);

        // inserire invio mail
        log.info("END Response {}", response);

        return response;

    }

    @Secured({ROLE_ADMIN, ROLE_WRITER})
    @PutMapping("/addFiles")
    public ResponseEntity<Object> updateMonitoringFiles(@RequestParam("file") MultipartFile[] files,
            @RequestParam("id") int id) {

        MDC.put(PRV_TID, PrvUtils.generateTID());
        log.info("Updating monitoringDTO={} by user={}", id,
                authenticationFacadeService.getAuthentication().getPrincipal());

        List<String> filePath = new ArrayList<>(); //
        List<String> fileName = new ArrayList<>();
        // mi arriva id monitoring ma mi serve id company
        Integer company = monitoringService.companyByMonitoringId(id);

        boolean error = false;
        boolean salvati;

        for (MultipartFile file : files) {
            try {
                File fileSalvato = PrvFileUtils.saveFile(file,
                        prvRestConfig.getMonitoringBasePath() + "" + company);
                fileName.add(fileSalvato.getName());
                filePath.add(fileSalvato.getAbsolutePath());
            } catch (IOException e) {
                error = true;
                break;
            }
        } // Elimino i file creati in caso di errori
        if (error) {
            filePath.forEach(pathString -> {
                Path path = Paths.get(pathString);
                try {
                    Files.delete(path);
                } catch (IOException e) {
                    log.error("FILE path={} NOT DELETED, e={}", pathString, e.getMessage());
                }
            });
        } // se non ho errori registro i file salvati
        salvati = monitoringService.registerFile(id, fileName);
        log.info("END RESPONSE {}", salvati);

        if (salvati) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @Secured({ROLE_ADMIN, ROLE_WRITER, ROLE_READER, ROLE_WRITER_EXT, ROLE_READER_EXT, ROLE_GUEST})
    @GetMapping(path = "/getTotal")
    public ResponseEntity<Object> getTotalNumber() {
        MDC.put(PRV_TID, PrvUtils.generateTID());

        return ResponseEntity.ok(monitoringService.getTotalNumber());
    }

    @GetMapping("/getMonitoringByIds")
    public ResponseEntity<Object> getMonitoringById(@RequestParam int[] ids, @RequestParam int companyId) {
        log.info("START - getMonitoringByIds companyId={}", companyId);
        List<MonitoraggiFullDTO> monitoraggi = new ArrayList<>();
        String auth = authenticationFacadeService.getAuthentication().getAuthorities().toString();
        if (auth.equals("ROLE_WRITER_EXT") || auth.equals("ROLE_READER_EXT")) {
            monitoraggi = monitoringService.getMonitoringByIds(ids, companyId);
        } else {
            monitoraggi = monitoringService.getMonitoringByIds(ids, 0);
        }
        if (!monitoraggi.isEmpty()) {
            return new ResponseEntity<>(monitoraggi, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
