package it.rjcsoft.prv.service;

import javax.validation.Valid;

import com.querydsl.core.types.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import it.rjcsoft.prv.dto.cumuliaperto.CumuliApertoFullDTO;
import it.rjcsoft.prv.dto.cumuliaperto.CumuliApertoUpdateDTO;
import it.rjcsoft.prv.exceptions.BaseEx;

public interface ICumuliApertoService {

	public Page<CumuliApertoFullDTO> findAll(Predicate predicate, Pageable pageable);

	public void update(@Valid CumuliApertoUpdateDTO cumuliApertoUpdateDTO) throws BaseEx;

}
