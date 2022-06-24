package it.rjcsoft.prv.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;

import it.rjcsoft.prv.dto.censimentodotazioniazienda.CensimentoDotazioniAziendaDTO;
import it.rjcsoft.prv.dto.censimentodotazioniazienda.CensimentoDotazioniDTO;
import it.rjcsoft.prv.dto.censimentodotazioniazienda.CensimentoDotazioniFullDTO;
import it.rjcsoft.prv.dto.censimentodotazioniazienda.DotazioneDTO;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.exceptions.InternalError;

public interface ICensimentoDotazioniService {

	public CensimentoDotazioniDTO save(@Valid CensimentoDotazioniAziendaDTO newCensimentoDotazioniAziendaDTO,
			int censimentoAziendaId) throws InternalError;

	public List<DotazioneDTO> findAllDotazioni();

	public Page<CensimentoDotazioniFullDTO> findAll(Predicate predicate, Pageable pageable);

	public void deleteById(int id) throws BaseEx;

	public void update(int id, int censimentoAziendaId, @Positive int quantita) throws BaseEx;

}
