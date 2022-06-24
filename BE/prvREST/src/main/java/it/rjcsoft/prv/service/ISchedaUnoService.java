package it.rjcsoft.prv.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;

import it.rjcsoft.prv.dto.SchedaUnoInfoAziendaDto;
import it.rjcsoft.prv.exceptions.PojoNotFound;

public interface ISchedaUnoService {

	Page<SchedaUnoInfoAziendaDto> findAll(Predicate predicate, Pageable pageable);

	Optional<SchedaUnoInfoAziendaDto> findByNumScheda(Integer numScheda) throws PojoNotFound;

	boolean deleteByIdAndNumScheda(Integer id, Integer numScheda) throws PojoNotFound;

}
