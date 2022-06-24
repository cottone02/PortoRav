package it.rjcsoft.prv.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.rjcsoft.prv.dto.email.EmailDTO;
import it.rjcsoft.prv.service.impl.HtmlMailService;

@RestController
@RequestMapping("/htmlMail")
public class HtmlMailController extends BaseController {

	@Autowired
	private HtmlMailService htmlMailService;

	@Secured({ ROLE_ADMIN })
	@PostMapping
	public ResponseEntity<Object> sendMails(@Valid @RequestBody EmailDTO emailDto) {
		
		try {
			if (!htmlMailService.customEmail(emailDto)) {
				return new ResponseEntity<>("Errore nella richiesta", HttpStatus.BAD_REQUEST);
			}
			return ResponseEntity.ok().build();

		} catch (Exception e) {
			
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
