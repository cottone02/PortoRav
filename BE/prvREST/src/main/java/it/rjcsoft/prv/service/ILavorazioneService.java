package it.rjcsoft.prv.service;

import java.util.List;

import it.rjcsoft.prv.dto.lavorazioneprodottodettagli.LavorazioneDTO;
import it.rjcsoft.prv.exceptions.InternalError;

public interface ILavorazioneService {

	public List<LavorazioneDTO> findAllLavorazione();

	public LavorazioneDTO save(LavorazioneDTO newLavorazioneDTO) throws InternalError;

}
