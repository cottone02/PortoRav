package it.rjcsoft.prv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.rjcsoft.prv.dto.StatusDto;
import it.rjcsoft.prv.model.Status;
import it.rjcsoft.prv.repository.IStatusRepository;
import it.rjcsoft.prv.service.BaseService;
import it.rjcsoft.prv.service.IStatusService;
import it.rjcsoft.prv.utils.PrvConverterUtils;

@Service
public class StatusService extends BaseService implements IStatusService {

	@Autowired
	private IStatusRepository statusRepository;

	@Override
	public Page<StatusDto> findAll(Pageable pageable) {
		Page<Status> statusPage = statusRepository.findAll(pageable);
		return statusPage.map(currStatus -> {
			StatusDto dto = new StatusDto();
			if (PrvConverterUtils.copyProperties(dto, currStatus)) {
				return dto;
			}
			return null;
		});
	}

}
