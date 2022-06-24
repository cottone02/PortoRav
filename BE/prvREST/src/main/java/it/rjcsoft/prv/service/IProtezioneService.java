package it.rjcsoft.prv.service;

import it.rjcsoft.prv.dto.censimentoprotezioni.ProtezioneContenimentoDTO;
import it.rjcsoft.prv.exceptions.InternalError;

public interface IProtezioneService {

	public ProtezioneContenimentoDTO save(ProtezioneContenimentoDTO protezione) throws InternalError;

}
