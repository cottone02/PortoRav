package it.rjcsoft.prv.service;

import javax.validation.Valid;

import com.querydsl.core.types.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import it.rjcsoft.prv.dto.prodottoinuscita.ProdottoInUscitaDTO;
import it.rjcsoft.prv.dto.prodottoinuscita.ProdottoInUscitaUpdateDTO;
import it.rjcsoft.prv.exceptions.BaseEx;

public interface IProdottoInUscitaService {

    public Page<ProdottoInUscitaDTO> findAll(Predicate predicate, Pageable pageable);

    public void update(@Valid ProdottoInUscitaUpdateDTO prodottoRicevutoUpdateDTO) throws BaseEx;

}
