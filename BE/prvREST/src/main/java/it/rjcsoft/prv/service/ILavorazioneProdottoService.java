package it.rjcsoft.prv.service;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;

import it.rjcsoft.prv.dto.lavorazioneprodotto.LavorazioneProdottoDTO;
import it.rjcsoft.prv.dto.lavorazioneprodotto.LavorazioneProdottoUpdateDTO;
import it.rjcsoft.prv.exceptions.BaseEx;

public interface ILavorazioneProdottoService {

	public Page<LavorazioneProdottoDTO> findAll(Predicate predicate, Pageable pageable);

    public void update(@Valid LavorazioneProdottoUpdateDTO lavorazioneProdottoUpdateDTO) throws BaseEx;

}
