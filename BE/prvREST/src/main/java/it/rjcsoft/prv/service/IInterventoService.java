package it.rjcsoft.prv.service;

import it.rjcsoft.prv.dto.censimentointerventi.InterventoDTO;
import it.rjcsoft.prv.exceptions.InternalError;

public interface IInterventoService {

	public InterventoDTO save(InterventoDTO intervento) throws InternalError;

}
