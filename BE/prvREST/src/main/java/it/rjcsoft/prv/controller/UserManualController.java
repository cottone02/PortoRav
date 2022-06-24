package it.rjcsoft.prv.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.jboss.logging.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.rjcsoft.prv.service.IUserManualService;
import it.rjcsoft.prv.utils.PrvUtils;

@Configuration
@RestController
@RequestMapping("/usermanual")
public class UserManualController extends BaseController {

	@Autowired
	private IUserManualService userManualService;

	@GetMapping(path = "/")
	public ResponseEntity<Resource> getFile() throws IOException {
		MDC.put(PRV_TID, PrvUtils.generateTID());

		File manual = userManualService.getManual();
		if (manual == null) {
			log.warn("Manual NOT FOUND");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		log.info("Download file path={}", manual.getAbsolutePath());
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=%s", manual.getName()));
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");

		ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(manual.toPath()));
		long length = manual.length();

		log.info("END - Download manual={}", manual.getAbsolutePath());

		return ResponseEntity.ok().headers(headers).contentLength(length).contentType(MediaType.MULTIPART_FORM_DATA)
				.body(resource);
	}
}
