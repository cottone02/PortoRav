package it.rjcsoft.prv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import it.rjcsoft.prv.dto.SchedaDueDotazioniAziendaDto;
import it.rjcsoft.prv.model.SchedaDueDotazioniAzienda;
import it.rjcsoft.prv.repository.ISchedaDueRepository;
import it.rjcsoft.prv.service.BaseService;
import it.rjcsoft.prv.service.ISchedaDueService;
import it.rjcsoft.prv.utils.PrvConverterUtils;

@Service
public class SchedaDueService extends BaseService implements ISchedaDueService {

	@Autowired
	private ISchedaDueRepository schedaDueRepository;

	@Override
	public Page<SchedaDueDotazioniAziendaDto> findAll(Specification<SchedaDueDotazioniAzienda> spec,
			Pageable pageable) {
		Page<SchedaDueDotazioniAzienda> schedePage = schedaDueRepository.findAll(spec, pageable);
		return schedePage.map(currScheda -> {
			SchedaDueDotazioniAziendaDto dto = new SchedaDueDotazioniAziendaDto();
			if (PrvConverterUtils.copyProperties(dto, currScheda)) {
				return dto;
			}
			return null;
		});
	}

}
