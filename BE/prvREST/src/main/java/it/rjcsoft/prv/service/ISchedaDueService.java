package it.rjcsoft.prv.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import it.rjcsoft.prv.dto.SchedaDueDotazioniAziendaDto;
import it.rjcsoft.prv.model.SchedaDueDotazioniAzienda;

public interface ISchedaDueService {

	public Page<SchedaDueDotazioniAziendaDto> findAll(Specification<SchedaDueDotazioniAzienda> spec,
			Pageable pageable);
	
}
