package it.rjcsoft.prv.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;

import it.rjcsoft.prv.dto.CodiceIstatAttivitaDTO;
import it.rjcsoft.prv.dto.censimentoazienda.CensimentoAziendaDTO;
import it.rjcsoft.prv.dto.censimentoazienda.CensimentoAziendaFullDTO;
import it.rjcsoft.prv.dto.censimentoazienda.CensimentoUpdateDTO;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.exceptions.InternalError;
import it.rjcsoft.prv.exceptions.PojoNotFound;

public interface ICensimentoAziendaService {

	public Page<CensimentoAziendaFullDTO> findAll(Predicate predicate, Pageable pageable);

	public CensimentoAziendaDTO save(CensimentoAziendaDTO newCensimentoAziendaDTO) throws PojoNotFound, InternalError;

	public void deleteById(int id) throws PojoNotFound, InternalError;

	public List<CodiceIstatAttivitaDTO> findAllCodiciIstat();

	public CensimentoUpdateDTO update(CensimentoUpdateDTO censimentoUpdateDTO) throws BaseEx;

    public Object getTotalNumber();

}
