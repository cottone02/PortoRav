package it.rjcsoft.prv.service;

import java.util.List;

import javax.validation.constraints.Positive;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;

import it.rjcsoft.prv.dto.censimentoprotezioni.CensimentoProtezioneDTO;
import it.rjcsoft.prv.dto.censimentoprotezioni.CensimentoProtezioniFullDTO;
import it.rjcsoft.prv.dto.censimentoprotezioni.ProtezioneContenimentoDTO;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.exceptions.InternalError;

public interface ICensimentoProtezioniService {

	public Page<CensimentoProtezioniFullDTO> findAll(Predicate predicate, Pageable pageable);

	public CensimentoProtezioneDTO save(CensimentoProtezioneDTO newCensimentoProtezioneDTO,
			int censimentoAziendaId) throws InternalError;

	public void deleteById(int id) throws BaseEx;

	public void update(int id, int censimentoAziendaId, @Positive String note) throws BaseEx;

	public List<ProtezioneContenimentoDTO> findAllDotazioni();

}
