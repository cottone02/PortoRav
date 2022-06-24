package it.rjcsoft.prv.service;

import javax.validation.Valid;

import com.querydsl.core.types.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import it.rjcsoft.prv.dto.movimentazione.MovimentazioneFullDTO;
import it.rjcsoft.prv.dto.movimentazione.MovimentazioneUpdateDTO;
import it.rjcsoft.prv.exceptions.BaseEx;

public interface IMovimentazioneService {

	public Page<MovimentazioneFullDTO> findAll(Predicate predicate, Pageable pageable);

	public void update(@Valid MovimentazioneUpdateDTO movimentazioneUpdateDTO) throws BaseEx;

}
