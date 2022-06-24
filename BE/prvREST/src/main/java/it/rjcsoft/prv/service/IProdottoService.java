package it.rjcsoft.prv.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

import com.querydsl.core.types.Predicate;

import it.rjcsoft.prv.dto.censimentoprodotti.ProdottoDTO;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.exceptions.InternalError;
import it.rjcsoft.prv.exceptions.PojoNotFound;

public interface IProdottoService {

	public Page<ProdottoDTO> findAll(Predicate predicate, Pageable pageable);

	public ProdottoDTO save(ProdottoDTO prodotto) throws InternalError;

	public void deleteById(int id) throws PojoNotFound, InternalError;

	public ProdottoDTO update(Integer id, String polverosita) throws BaseEx;

    public List<String> findAllPolverosita();

}
