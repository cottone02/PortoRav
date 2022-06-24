package it.rjcsoft.prv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.rjcsoft.prv.dto.RoleDto;
import it.rjcsoft.prv.model.Role;
import it.rjcsoft.prv.repository.IRoleRepository;
import it.rjcsoft.prv.service.BaseService;
import it.rjcsoft.prv.service.IRoleService;
import it.rjcsoft.prv.utils.PrvConverterUtils;

@Service
public class RoleService extends BaseService implements IRoleService {

	@Autowired
	private IRoleRepository roleRepository;

	@Override
	public Page<RoleDto> findAll(Pageable pageable) {
		Page<Role> rolesPage = roleRepository.findAll(pageable);
		return rolesPage.map(currRole -> {
			RoleDto dto = new RoleDto();
			if (PrvConverterUtils.copyProperties(dto, currRole)) {
				return dto;
			}
			return null;
		});
	}

}
