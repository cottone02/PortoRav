package it.rjcsoft.prv.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.validation.Valid;

import com.querydsl.core.types.Predicate;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import it.rjcsoft.prv.dto.modulistica.ModulisticaDTO;
import it.rjcsoft.prv.dto.modulistica.ModulisticaUpdateDTO;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.model.DocumentiForm;
import it.rjcsoft.prv.repository.IModulisticaRepository;
import it.rjcsoft.prv.service.IModulisticaService;
import it.rjcsoft.prv.utils.PrvUtils;

@RestController
@RequestMapping("/modulistica")
public class ModulisticaController extends BaseController {

	@Autowired
	private IModulisticaService modulisticaService;

	@Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_READER, ROLE_WRITER_EXT, ROLE_READER_EXT, ROLE_GUEST})
	@GetMapping("")
	public ResponseEntity<Page<ModulisticaDTO>> getAll(
			@QuerydslPredicate(root = DocumentiForm.class, bindings = IModulisticaRepository.class) Predicate predicate,
			@PageableDefault(sort = { "id" }) Pageable pageable) {
		MDC.put(PRV_TID, PrvUtils.generateTID());
		log.debug("START - predicate={}, pageable={}", predicate, pageable);
		Page<ModulisticaDTO> page = modulisticaService.findAll(predicate, pageable);
		if (page.isEmpty()) {
			log.info("END - no content");
			return ResponseEntity.noContent().build();
		} else {
			log.info("END - 200, page={}", page);
			return ResponseEntity.ok(page);
		}
	}

	@Secured({ ROLE_ADMIN }) 
	@PutMapping("/update")
	public ResponseEntity<Object> updateFile( ModulisticaUpdateDTO modulisticaUpdateDTO) {
		MDC.put(PRV_TID, PrvUtils.generateTID());
		log.debug("START - modulisticaUpdateDTO={}", modulisticaUpdateDTO);

		try {
			modulisticaService.updateFile(modulisticaUpdateDTO);
		} catch (BaseEx e) {
			log.error("END - UPDATE Internal error, message={}", e.getMessage(), e);
			return e.getResponseEntity();
		}
		return ResponseEntity.ok().build();
	}

	@Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_WRITER_EXT })
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteModulisticaById(@PathVariable int id) {
		MDC.put(PRV_TID, PrvUtils.generateTID());
		log.debug("START - delete modulistica={}", id);
		try {
			modulisticaService.deleteById(id);
		} catch (BaseEx e) {
			log.error("END - DELETE Internal error, message={}", e.getMessage(), e);
			return e.getResponseEntity();
		}
		return ResponseEntity.ok().build();
	}
	
	@Secured({ ROLE_ADMIN }) 
	@PostMapping("")
	public ResponseEntity<Object> addDocument (
			@Valid ModulisticaDTO modulisticaDTO,
			@RequestParam("file") MultipartFile file) {
		MDC.put(PRV_TID, PrvUtils.generateTID());
		log.debug("START - addDocument={}", modulisticaDTO);
		if (modulisticaDTO.getId() != null) {
			log.warn("END - id NOT NULL, id={}", modulisticaDTO.getId());
			return ResponseEntity.badRequest().build();
		}
		
		ModulisticaDTO modulistica = null;
		try {
			modulistica = modulisticaService.save(modulisticaDTO, file);
		} catch (BaseEx e) {
			log.error("END - Internal error, message={}", e.getMessage(), e);
			return e.getResponseEntity();
		}

		if (modulistica == null) {
			log.warn("END - not saved!");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} else {
			log.info("END - saved {}", modulistica);
			return new ResponseEntity<>(modulistica, HttpStatus.CREATED);
		}
	}
	
	@Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_READER, ROLE_WRITER_EXT, ROLE_READER_EXT, ROLE_GUEST })
	@GetMapping("/download/{id}")
	public ResponseEntity<Resource> download(@PathVariable int id) throws IOException {

		MDC.put(PRV_TID, PrvUtils.generateTID());
		
		log.info("START - GET document={}", id);
				
		String pathDoc = modulisticaService.getPathFileById(id);
		
		log.info("Download file path={}", pathDoc);
		
		if (pathDoc == null) {
			log.warn("NOT FOUND");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		}
		File file = new File(pathDoc);
		if (!file.exists()) {
			log.warn("File path={} NOT FOUND", pathDoc);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Path path = Paths.get(pathDoc);
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=%s", file.getName()));
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");

		ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
		
		log.info("END - Download document={}", file.getAbsolutePath());
		
		return ResponseEntity.ok().headers(headers).contentLength(file.length())
				.contentType(MediaType.MULTIPART_FORM_DATA).body(resource);

	}

}
