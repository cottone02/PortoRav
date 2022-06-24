package it.rjcsoft.prv.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import it.rjcsoft.prv.dto.StatusDto;

public interface IStatusService {

	Page<StatusDto> findAll(Pageable pageable);
}
