package it.rjcsoft.prv.service;

import javax.validation.Valid;

import com.querydsl.core.types.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import it.rjcsoft.prv.dto.prodottoricevuto.ProdottoRicevutoDTO;
import it.rjcsoft.prv.dto.prodottoricevuto.ProdottoRicevutoUpdateDTO;
import it.rjcsoft.prv.exceptions.BaseEx;

public interface IProdottoRicevutoService {

    public Page<ProdottoRicevutoDTO> findAll(Predicate predicate, Pageable pageable);

    public void update(@Valid ProdottoRicevutoUpdateDTO prodottoRicevutoUpdateDTO) throws BaseEx;
    
}
