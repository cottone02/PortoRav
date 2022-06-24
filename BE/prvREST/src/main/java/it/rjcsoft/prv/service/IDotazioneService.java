package it.rjcsoft.prv.service;

import it.rjcsoft.prv.dto.censimentodotazioniazienda.DotazioneDTO;
import it.rjcsoft.prv.exceptions.InternalError;

public interface IDotazioneService {

	public DotazioneDTO save(DotazioneDTO dot) throws InternalError;

}
