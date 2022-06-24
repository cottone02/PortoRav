package it.rjcsoft.prv.service;

import java.util.List;

import com.querydsl.core.types.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import it.rjcsoft.prv.dto.censimentoprodotti.CensimentoProdottiDTO;
import it.rjcsoft.prv.dto.censimentoprodotti.CensimentoProdottiFullDTO;
import it.rjcsoft.prv.dto.censimentoprodotti.CensimentoProdottiUpdateDTO;
import it.rjcsoft.prv.dto.censimentoprodotti.ClasseGranulometricaDTO;
import it.rjcsoft.prv.exceptions.BaseEx;

public interface ICensimentoProdottiService {

	public CensimentoProdottiDTO save(Integer prodottoId,
			int censimentoAziendaId) throws BaseEx;

	public List<ClasseGranulometricaDTO> findAllClassiGranulometriche();

	public Page<CensimentoProdottiFullDTO> findAll(Predicate predicate, Pageable pageable);

	public void deleteById(Integer id) throws BaseEx;

	public void update(CensimentoProdottiUpdateDTO updateDTO) throws BaseEx;

}
