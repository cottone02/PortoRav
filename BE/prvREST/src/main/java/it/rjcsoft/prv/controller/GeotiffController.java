package it.rjcsoft.prv.controller;

import java.io.File;
import java.io.IOException;

import com.querydsl.core.types.Predicate;

import org.jboss.logging.MDC;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import it.rjcsoft.prv.dto.geotiff.GeotiffAllDTO;
import it.rjcsoft.prv.model.Geotiff;
import it.rjcsoft.prv.repository.IGeotiffRepository;
import it.rjcsoft.prv.service.IGeotiffService;
import it.rjcsoft.prv.utils.GeoTiffUtils;
import it.rjcsoft.prv.utils.PrvUtils;

@RestController
@RequestMapping("/geotiff")
public class GeotiffController extends BaseController {

	@Autowired
	IGeotiffService geotiffService;

	@Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_READER, ROLE_WRITER_EXT, ROLE_READER_EXT })
	@GetMapping("/png/{id}")
	public ResponseEntity<Object> getPng(@PathVariable Integer id) throws IOException {
		try {
			File image = geotiffService.getFileById(id);
			byte[] byteArray = GeoTiffUtils.fromFileToByteArray(image);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_PNG);
			headers.setContentLength(byteArray.length);

			return ResponseEntity.ok().headers(headers).body(byteArray);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.notFound().build();
		}

	}

	@Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_READER, ROLE_WRITER_EXT, ROLE_READER_EXT })
	@GetMapping("")
	public ResponseEntity<Page<GeotiffAllDTO>> getAll(
			@QuerydslPredicate(root = Geotiff.class, bindings = IGeotiffRepository.class) Predicate predicate,
			@PageableDefault(sort = { "id" }) Pageable pageable) {
		MDC.put(PRV_TID, PrvUtils.generateTID());
		log.debug("START - predicate={}, pageable={}", predicate, pageable);
		Page<GeotiffAllDTO> page = geotiffService.findAll(predicate, pageable, true, getLoggedUserId());
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
	public ResponseEntity<Object> newGeotiff(@RequestParam String uom, @RequestParam("file") MultipartFile file) {
		MDC.put(PRV_TID, PrvUtils.generateTID());
		log.debug("START - geotiff={}, uom={}", file.getOriginalFilename(), uom);
		try {
			geotiffService.save(file, uom, getLoggedUserId());
		} catch (Exception e) {
			log.error("END - not saved! geotiff={}, uom={}", file.getOriginalFilename(), uom);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_WRITER_EXT })
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteGeotiff(@PathVariable Integer id) throws IOException {
		try {
			if (geotiffService.deleteById(id)) {
				return ResponseEntity.ok().build();
			} else {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

}
