package it.rjcsoft.prv.service;

import java.util.List;

import it.rjcsoft.prv.dto.lavorazioneprodottodettagli.TipoLavorazioneDTO;


public interface ITipoLavorazioneService {

	public List<TipoLavorazioneDTO> findAllTipoLavorazione();

}
