package it.rjcsoft.prv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.rjcsoft.prv.dto.RoleDto;
import it.rjcsoft.prv.service.IRoleService;

@RestController
@RequestMapping("/roles")
public class RoleController {

	@Autowired
	private IRoleService roleService;

	@GetMapping("")
	public ResponseEntity<Page<RoleDto>> getAll(@PageableDefault Pageable pageable) {
		return ResponseEntity.ok(roleService.findAll(pageable));
	}

}
