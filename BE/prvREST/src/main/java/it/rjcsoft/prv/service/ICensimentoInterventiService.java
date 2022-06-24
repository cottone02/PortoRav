package it.rjcsoft.prv.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;

import it.rjcsoft.prv.dto.censimentointerventi.CensimentoInterventIMitigazioneDTO;
import it.rjcsoft.prv.dto.censimentointerventi.CensimentoInterventiDTO;
import it.rjcsoft.prv.dto.censimentointerventi.CensimentoInterventiFullDTO;
import it.rjcsoft.prv.dto.censimentointerventi.CensimentoInterventiUpdateDTO;
import it.rjcsoft.prv.dto.censimentointerventi.InterventoDTO;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.exceptions.InternalError;

public interface ICensimentoInterventiService {

	public CensimentoInterventiDTO save(@Valid CensimentoInterventIMitigazioneDTO newCensimentoInterventIMitigazioneDTO,
			int censimentoAziendaId) throws InternalError;

	public List<InterventoDTO> findAllInterventi();

	public Page<CensimentoInterventiFullDTO> findAll(Predicate predicate, Pageable pageable);

	public void deleteById(int id) throws BaseEx;

	public void update(CensimentoInterventiUpdateDTO updateDTO) throws BaseEx;

}
