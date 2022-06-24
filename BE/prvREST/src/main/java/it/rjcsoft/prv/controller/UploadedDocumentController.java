package it.rjcsoft.prv.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
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

import com.querydsl.core.types.Predicate;

import it.rjcsoft.prv.dto.UploadedDocumentDTO;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.exceptions.PojoNotFound;
import it.rjcsoft.prv.model.UploadedDocument;
import it.rjcsoft.prv.model.Utente;
import it.rjcsoft.prv.repository.IUploadedDocumentRepository;
import it.rjcsoft.prv.repository.IUtenteRepository;
import it.rjcsoft.prv.service.IAuthenticationFacadeService;
import it.rjcsoft.prv.service.IUploadedDocumentService;
import it.rjcsoft.prv.utils.PrvUtils;
import org.springframework.security.core.context.SecurityContextHolder;

//@Configuration
@RestController
@RequestMapping("/uploadedDocuments")
public class UploadedDocumentController extends BaseController {

    private static final String END_RESPONSE = "END - response={}";

    @Autowired
    private IUploadedDocumentService uploadedDocumentService;

    @Autowired
    private IUtenteRepository utenteRepository;

    @Autowired
    private IAuthenticationFacadeService authenticationFacadeService;

    @Secured({ROLE_ADMIN, ROLE_WRITER, ROLE_READER, ROLE_WRITER_EXT, ROLE_READER_EXT, ROLE_GUEST})
    @GetMapping("")
    public ResponseEntity<Page<UploadedDocumentDTO>> getAll(
            @QuerydslPredicate(root = UploadedDocument.class, bindings = IUploadedDocumentRepository.class) Predicate predicate,
            @PageableDefault(sort = {"id"}) Pageable pageable) throws BaseEx {

        String auth = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()[0].toString();

        if (auth.equals("ROLE_WRITER_EXT") || auth.equals("ROLE_READER_EXT") || auth.equals("ROLE_GUEST")) {
            Utente utente = new Utente();
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            utente = utenteRepository.findByUsername(username)
                    .orElseThrow(() -> new PojoNotFound("Utente con username " + username + "non trovato!"));

            if (!predicate.toString().contains("uploadedDocument.companyId = " + utente.getCompanyId())) {
                return ResponseEntity.noContent().build();
            }
        }

        MDC.put(PRV_TID, PrvUtils.generateTID());
        log.debug("START - predicate={}, pageable={}", predicate, pageable);
        Page<UploadedDocumentDTO> page = uploadedDocumentService.findAll(predicate, pageable);
        if (page.isEmpty()) {
            log.info("END - no content");
            return ResponseEntity.noContent().build();
        } else {
            log.info("END - 200, page={}", page);
            return ResponseEntity.ok(page);
        }
    }

    @Secured({ROLE_ADMIN, ROLE_WRITER, ROLE_WRITER_EXT})
    @DeleteMapping("/deleteFile/{id}/{name}")
    public ResponseEntity<Object> deleteUploadedDocumentFileByIdDocumentAndName(@PathVariable int id, @PathVariable String name) {
        MDC.put(PRV_TID, PrvUtils.generateTID());
        log.debug("START - delete uploadedDocumentFile={}", id);
        try {
            uploadedDocumentService.deleteByIdDocumentAndName(id, name);
        } catch (BaseEx e) {
            log.error("END - DELETE Internal error, message={}", e.getMessage(), e);
            return e.getResponseEntity();
        }
        return ResponseEntity.ok().build();
    }

    @Secured({ROLE_ADMIN, ROLE_WRITER, ROLE_WRITER_EXT})
    @DeleteMapping("/deleteDocument/{id}")
    public ResponseEntity<Object> deleteUploadedDocument(@PathVariable int id) {
        MDC.put(PRV_TID, PrvUtils.generateTID());
        log.debug("START - delete uploadedDocumentFile={}", id);
        try {
            uploadedDocumentService.deleteUploadedDocument(id);
        } catch (BaseEx e) {
            log.error("END - DELETE Internal error, message={}", e.getMessage(), e);
            return e.getResponseEntity();
        }
        return ResponseEntity.ok().build();
    }

    @Secured({ROLE_ADMIN, ROLE_WRITER, ROLE_READER, ROLE_WRITER_EXT, ROLE_READER_EXT})
    @GetMapping("/fileNames/{id}")
    public ResponseEntity<Object> getFileNamesById(@PathVariable int id) {
        MDC.put(PRV_TID, PrvUtils.generateTID());

        log.info("START - GET fileNames id={} by user={}", id);

        List<String> fileNames = uploadedDocumentService.getFileNamesById(id);

        if (fileNames.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(fileNames, HttpStatus.OK);
        }

    }

    @Secured({ROLE_ADMIN, ROLE_WRITER, ROLE_WRITER_EXT})
    @PostMapping("/uploadDocuments")
    public ResponseEntity<Object> uploadDocument(@RequestParam("file") MultipartFile[] files,
            @RequestParam Map<String, Object> parameters) {
        MDC.put(PRV_TID, PrvUtils.generateTID());

        UploadedDocumentDTO uploadedDocumentDTO = new UploadedDocumentDTO();

        for (Entry<String, Object> entry : parameters.entrySet()) {
            try {
                BeanUtils.setProperty(uploadedDocumentDTO, entry.getKey(), entry.getValue());
            } catch (IllegalAccessException | InvocationTargetException e) {
                log.warn("COPY PROPERTIES FAILED, entry={}, e={}", entry, e.getMessage());
                return new ResponseEntity<>("PARAMETERS ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }

        log.info("Uploading uploadedDocumentDTO={} by user={}", uploadedDocumentDTO,
                authenticationFacadeService.getAuthentication().getPrincipal());

        List<String> fileNames = new ArrayList<>();

        Integer company = uploadedDocumentDTO.getCompanyId();

        boolean error = false;

        for (MultipartFile file : files) {
            String fileName = uploadedDocumentService.saveFile(file, company);
            if (fileName != null) {
                fileNames.add(fileName);
            } else {
                error = true;
                break;
            }
        }

        if (error) {
            fileNames.forEach(pathString -> {
                Path path = Paths.get(pathString);
                try {
                    Files.delete(path);
                } catch (IOException e) {
                    log.warn("FILE path={} NOT DELETED, e={}", pathString, e.getMessage());
                }
            });
        }

        UploadedDocument uploadedDocument = uploadedDocumentService.initNewDocument(uploadedDocumentDTO);

        if (uploadedDocument.getId() == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        boolean response = uploadedDocumentService.registerFile(uploadedDocument.getId(), fileNames);

        if (!response) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }

    @Secured({ROLE_ADMIN, ROLE_WRITER, ROLE_READER, ROLE_WRITER_EXT, ROLE_READER_EXT})
    @GetMapping("/download/zip/{id}")
    public ResponseEntity<Resource> download(@PathVariable int id) throws IOException {
        MDC.put(PRV_TID, PrvUtils.generateTID());
        log.info("START - GET documentId={}, user={}", id);

        File zipFile = uploadedDocumentService.getZipFile(id);
        if (zipFile == null || !zipFile.exists()) {
            log.warn("ZipFile NOT CREATED");
            //return new ResponseEntity<>(HttpStatus.CREATED);
        }
        log.info("Download file path={}", zipFile.getAbsolutePath());
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=%s", zipFile.getName()));
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(zipFile.toPath()));
        long length = zipFile.length();

        try {
            Files.delete(zipFile.toPath());
        } catch (IOException e) {
            log.warn("FILE path={} NOT DELETED, e={}", zipFile.getAbsolutePath(), e.getMessage());
        }

        log.info("END - Download document={}", zipFile.getAbsolutePath());

        return ResponseEntity.ok().headers(headers).contentLength(length).contentType(MediaType.MULTIPART_FORM_DATA)
                .body(resource);

    }

    @Secured({ROLE_ADMIN, ROLE_WRITER, ROLE_READER, ROLE_WRITER_EXT, ROLE_READER_EXT})
    @GetMapping(path = "/download/file/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable int id, @RequestParam String fileName)
            throws IOException {
        MDC.put(PRV_TID, PrvUtils.generateTID());
        log.info("START - GET documentId={}, fileName={}, user={}", id, fileName,
                authenticationFacadeService.getAuthentication().getPrincipal());
        if (StringUtils.isBlank(fileName)) {
            log.warn("fileName {} NOT FOUND", fileName);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        File file = uploadedDocumentService.getByIdAndFileName(id, fileName);
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
        log.info("END - Download document={}", file.getAbsolutePath());
        return ResponseEntity.ok().headers(headers).contentLength(length).contentType(MediaType.MULTIPART_FORM_DATA)
                .body(resource);

    }

    @Secured({ROLE_ADMIN, ROLE_WRITER, ROLE_WRITER_EXT})
    @PutMapping("/addFiles")
    public ResponseEntity<Object> updateDocumentFiles(@RequestParam("file") MultipartFile[] files,
            @RequestParam("id") int id) {
        MDC.put(PRV_TID, PrvUtils.generateTID());

        log.info("Updating id={}, user={}", id, authenticationFacadeService.getAuthentication().getPrincipal());

        List<String> fileNames = new ArrayList<>();

        Integer company = uploadedDocumentService.getCompanyByDocumentId(id);

        boolean error = false;

        for (MultipartFile file : files) {
            String fileName = uploadedDocumentService.saveFile(file, company);
            if (fileName != null) {
                fileNames.add(fileName);
            } else {
                error = true;
                break;
            }
        }

        if (error) {
            fileNames.forEach(pathString -> {
                Path path = Paths.get(pathString);
                try {
                    Files.delete(path);
                } catch (IOException e) {
                    log.warn("FILE path={} NOT DELETED, e={}", pathString, e.getMessage());
                }
            });
        }

        boolean response = uploadedDocumentService.registerFile(id, fileNames);

        if (!response) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }

    @Secured({ROLE_ADMIN, ROLE_WRITER, ROLE_WRITER_EXT})
    @PutMapping("/updateDocInfo")
    public ResponseEntity<Object> updateMonitoringInfo(@RequestBody UploadedDocumentDTO uploadedDocumentDTO) {
        MDC.put(PRV_TID, PrvUtils.generateTID());

        log.info("START - Update uploadedDocumentDTO={} by user={}", uploadedDocumentDTO,
                authenticationFacadeService.getAuthentication().getPrincipal());

        ResponseEntity<Object> response;
        try {
            response = uploadedDocumentService.updateDoc(uploadedDocumentDTO);
        } catch (BaseEx e) {
            e.printStackTrace();
            response = null;
        }

        log.info(END_RESPONSE, response);

        return response;

    }

    /*@Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_WRITER_EXT })
	@PostMapping("/uploadDocuments")
	public ResponseEntity<Object> uploadDocument(@RequestParam("file") MultipartFile[] files,
			@RequestParam Map<String, Object> parameters) {
		MDC.put(PRV_TID, PrvUtils.generateTID());

		UploadedDocumentDTO uploadedDocumentDTO = new UploadedDocumentDTO();

		for (Entry<String, Object> entry : parameters.entrySet()) {
			try {
				BeanUtils.setProperty(uploadedDocumentDTO, entry.getKey(), entry.getValue());
			} catch (IllegalAccessException | InvocationTargetException e) {
				log.warn("COPY PROPERTIES FAILED, entry={}, e={}", entry, e.getMessage());
				return new ResponseEntity<>("PARAMETERS ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
			}

		}

		log.info("Uploading uploadedDocumentDTO={} by user={}", uploadedDocumentDTO,
				authenticationFacadeService.getAuthentication().getPrincipal());

		List<String> fileNames = new ArrayList<>();

		Integer company = uploadedDocumentDTO.getCompanyId();

		if (company == null) {
			return new ResponseEntity<>("NO COMPANY ASSOCIATED", HttpStatus.FORBIDDEN);
		}

		boolean error = false;

		for (MultipartFile file : files) {
			String fileName = uploadedDocumentService.saveFile(file, company);
			if (fileName != null) {
				fileNames.add(fileName);
			} else {
				error = true;
				break;
			}
		}

		if (error) {
			fileNames.forEach(pathString -> {
				Path path = Paths.get(pathString);
				try {
					Files.delete(path);
				} catch (IOException e) {
					log.warn("FILE path={} NOT DELETED, e={}", pathString, e.getMessage());
				}
			});
		}

		Integer id = uploadedDocumentService.initNewDocument(uploadedDocumentDTO);

		if (id == null) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		boolean response = uploadedDocumentService.registerFile(id, fileNames);

		if (!response) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<>(HttpStatus.OK);
		}

	}*/
 /*@Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_READER, ROLE_WRITER_EXT, ROLE_READER_EXT })
	@GetMapping(path = "/download/zip/{id}")
	public ResponseEntity<Resource> download(@PathVariable int id) throws IOException {
		MDC.put(PRV_TID, PrvUtils.generateTID());
		log.info("START - GET documentId={}, user={}", id,
				authenticationFacadeService.getAuthentication().getPrincipal());

		File zipFile = uploadedDocumentService.getZipFile(id);
		if (zipFile == null || !zipFile.exists()) {
			log.warn("ZipFile NOT CREATED");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		log.info("Download file path={}", zipFile.getAbsolutePath());
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=%s", zipFile.getName()));
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");

		ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(zipFile.toPath()));
		long length = zipFile.length();

		try {
			Files.delete(zipFile.toPath());
		} catch (IOException e) {
			log.warn("FILE path={} NOT DELETED, e={}", zipFile.getAbsolutePath(), e.getMessage());
		}

		log.info("END - Download document={}", zipFile.getAbsolutePath());

		return ResponseEntity.ok().headers(headers).contentLength(length).contentType(MediaType.MULTIPART_FORM_DATA)
				.body(resource);

	}*/

 /*@Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_READER, ROLE_WRITER_EXT, ROLE_READER_EXT })
	@GetMapping(path = "/download/file/{id}")
	public ResponseEntity<Resource> downloadFile(@PathVariable int id, @RequestParam String fileName)
			throws IOException {
		MDC.put(PRV_TID, PrvUtils.generateTID());
		log.info("START - GET documentId={}, fileName={}, user={}", id, fileName,
				authenticationFacadeService.getAuthentication().getPrincipal());
		if (StringUtils.isBlank(fileName)) {
			log.warn("fileName {} NOT FOUND", fileName);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		File file = uploadedDocumentService.getByIdAndFileName(id, fileName);
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
		log.info("END - Download document={}", file.getAbsolutePath());
		return ResponseEntity.ok().headers(headers).contentLength(length).contentType(MediaType.MULTIPART_FORM_DATA)
				.body(resource);

	}*/

 /*@Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_READER, ROLE_WRITER_EXT, ROLE_READER_EXT })
	@GetMapping(path = "/fileNames/{id}")
	public ResponseEntity<Object> getFileNames(@PathVariable int id) {
		MDC.put(PRV_TID, PrvUtils.generateTID());

		log.info("START - GET fileNames id={} by user={}", id,
				authenticationFacadeService.getAuthentication().getPrincipal());

		List<String> fileNames = uploadedDocumentService.getFileNamesById(id);

		if (fileNames.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(fileNames, HttpStatus.OK);
		}

	}

	

	/*@Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_WRITER_EXT })
	@PutMapping("/updateDocInfo")
	public ResponseEntity<Object> updateMonitoringInfo(@RequestBody UploadedDocumentDTO uploadedDocumentDTO) {
		MDC.put(PRV_TID, PrvUtils.generateTID());

		log.info("START - Update uploadedDocumentDTO={} by user={}", uploadedDocumentDTO,
				authenticationFacadeService.getAuthentication().getPrincipal());

		ResponseEntity<Object> response = uploadedDocumentService.updateDoc(uploadedDocumentDTO);

		log.info(END_RESPONSE, response);

		return response;

	}

	@Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_WRITER_EXT })
	@PutMapping("/addFiles")
	public ResponseEntity<Object> updateDocumentFiles(@RequestParam("file") MultipartFile[] files,
			@RequestParam("id") int id) {
		MDC.put(PRV_TID, PrvUtils.generateTID());

		log.info("Updating id={}, user={}", id, authenticationFacadeService.getAuthentication().getPrincipal());

		List<String> fileNames = new ArrayList<>();

		Integer company = uploadedDocumentService.getCompanyByDocumentId(id);

		boolean error = false;

		for (MultipartFile file : files) {
			String fileName = uploadedDocumentService.saveFile(file, company);
			if (fileName != null) {
				fileNames.add(fileName);
			} else {
				error = true;
				break;
			}
		}

		if (error) {
			fileNames.forEach(pathString -> {
				Path path = Paths.get(pathString);
				try {
					Files.delete(path);
				} catch (IOException e) {
					log.warn("FILE path={} NOT DELETED, e={}", pathString, e.getMessage());
				}
			});
		}

		boolean response = uploadedDocumentService.registerFile(id, fileNames);

		if (!response) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<>(HttpStatus.OK);
		}

	}*/
    //VERSIONE JDBC
    /*@Secured({ROLE_ADMIN, ROLE_WRITER, ROLE_READER, ROLE_WRITER_EXT, ROLE_READER_EXT})
	@GetMapping("/getDocumentsByPage")
	public ResponseEntity<Object> getDocumentsByPage(@RequestParam int pageSize, @RequestParam int pageIndex,
			@RequestParam Map<String, Object> parameters) {

		MDC.put(PRV_TID, PrvUtils.generateTID());
		log.info("parameters {}", StringUtils.join(parameters));
		UploadedDocumentFilterDTO filter = PrvConverterUtils.convert(parameters, UploadedDocumentFilterDTO.class);
		boolean isAdmin = PrvRolesUtils.isAdmin(authenticationFacadeService.getAuthentication()) || PrvRolesUtils.isWriteInt(authenticationFacadeService.getAuthentication()) || PrvRolesUtils.isReaderInt(authenticationFacadeService.getAuthentication());
		log.info("Getting all documents isAdmin={}, filter={}", isAdmin, filter);
		if (filter == null) {
			return new ResponseEntity<>("PARAMETERS ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		log.info("FILTER {}", filter);

		UploadedDocumentListDTO uploadedDocumentListDTO = uploadedDocumentService.getDocumentsByPageIndex(pageSize,
				pageIndex, filter, isAdmin);

		int responseCode = uploadedDocumentListDTO.getCode();

		log.info("getMonitoringByPage responseCode={}", responseCode);

		if (responseCode == HttpStatus.OK.value()) {
			return new ResponseEntity<>(uploadedDocumentListDTO, HttpStatus.OK);
		} else {
			String description = uploadedDocumentListDTO.getDescription();
			HttpStatus status = HttpStatus.valueOf(responseCode);
			return new ResponseEntity<>(description, status);
		}
	}*/
 /*@Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_WRITER_EXT })
	@DeleteMapping(path = "/deleteFile/{id}/{fileName}")
	public ResponseEntity<Object> deleteFile(@PathVariable int id, @PathVariable String fileName) {
		MDC.put(PRV_TID, PrvUtils.generateTID());

		log.info("START - DELETE fileNames={}, id={} by user={}", fileName, id,
				authenticationFacadeService.getAuthentication().getPrincipal());

		boolean isDeleted = uploadedDocumentService.deleteFileByNameId(fileName, id);

		if (isDeleted) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}

	}*/
 /*@Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_WRITER_EXT })
	@DeleteMapping("/deleteDocument/{id}")
	public ResponseEntity<Object> deleteDocument(@PathVariable int id) {
		MDC.put(PRV_TID, PrvUtils.generateTID());
		log.info("START - Delete documentId={}, user={}", id,
				authenticationFacadeService.getAuthentication().getPrincipal());
		ResponseEntity<Object> response = null;
		if (PrvRolesUtils.isWriteExt(authenticationFacadeService.getAuthentication())) { // inutile
			response = uploadedDocumentService.deleteById(id);
		}
		log.info("END - response={}", response);
		return response;

	}*/
}
