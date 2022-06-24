package it.rjcsoft.prv.service;

import java.util.List;

import com.querydsl.core.types.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import it.rjcsoft.prv.dto.lavorazioneprodottodettagli.LavorazioneDTO;
import it.rjcsoft.prv.dto.lavorazioneprodottodettagli.LavorazioneETipoLavorazioneDTO;
import it.rjcsoft.prv.dto.lavorazioneprodottodettagli.LavorazioneProdottoDettagliFullDTO;
import it.rjcsoft.prv.dto.lavorazioneprodottodettagli.LavorazioneProdottoDettagliInsertFullDTO;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.exceptions.InternalError;

public interface ILavorazioneProdottoDettagliService {

	public Page<LavorazioneProdottoDettagliFullDTO> findAll(Predicate predicate, Pageable pageable);

	public LavorazioneETipoLavorazioneDTO findAllLavorazioneETipoLavorazione();

	public LavorazioneProdottoDettagliInsertFullDTO save(
			LavorazioneProdottoDettagliInsertFullDTO newLavorazioneProdottoDettagliInsertFullDTO) throws InternalError;

	public void deleteById(int id) throws BaseEx;

	public void update(LavorazioneProdottoDettagliInsertFullDTO lavorazioneProdottoDettagliInsertFullDTO) throws BaseEx;

	public LavorazioneDTO saveLavorazione(LavorazioneDTO newLavorazioneDTO) throws InternalError;

	public List<LavorazioneDTO> findAvailableLavorazioniInLavorazioneProdottoDettagli(Integer prodottoDettaglioId);

}
