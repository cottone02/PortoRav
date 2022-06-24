package it.rjcsoft.prv.service;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;

import it.rjcsoft.prv.dto.dettagliositodepositi.TipoDepositoStoccaggioDTO;
import it.rjcsoft.prv.exceptions.BaseEx;


public interface ITipoDepositoStoccaggioService {

	public TipoDepositoStoccaggioDTO save(@Valid TipoDepositoStoccaggioDTO newDeposito) throws BaseEx;

	public void deleteById(int id) throws BaseEx;

	public void update(TipoDepositoStoccaggioDTO newDeposito) throws BaseEx;

	public Page<TipoDepositoStoccaggioDTO> findAll(Predicate predicate, Pageable pageable);
	
}
