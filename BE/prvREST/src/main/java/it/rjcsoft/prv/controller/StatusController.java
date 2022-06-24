package it.rjcsoft.prv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.rjcsoft.prv.dto.StatusDto;
import it.rjcsoft.prv.service.IStatusService;

@RestController
@RequestMapping("/status")
public class StatusController {

	@Autowired
	private IStatusService statusService;

	@GetMapping("")
	public ResponseEntity<Page<StatusDto>> getAll(@PageableDefault Pageable pageable) {
		return ResponseEntity.ok(statusService.findAll(pageable));
	}

}
