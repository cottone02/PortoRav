package it.rjcsoft.prv.service;

import javax.validation.Valid;

import com.querydsl.core.types.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import it.rjcsoft.prv.dto.prodottointransito.ProdottoInTransitoFullDTO;
import it.rjcsoft.prv.dto.prodottointransito.ProdottoInTransitoUpdateDTO;
import it.rjcsoft.prv.exceptions.BaseEx;

public interface IProdottoInTransitoService {

    public Page<ProdottoInTransitoFullDTO> findAll(Predicate predicate, Pageable pageable);

    public void update(@Valid ProdottoInTransitoUpdateDTO prodottoInTransitoUpdateDTO) throws BaseEx;
    
}
