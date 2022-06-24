package it.rjcsoft.prv.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import it.rjcsoft.prv.dto.RoleDto;

public interface IRoleService {

	Page<RoleDto> findAll(Pageable pageable);

}
