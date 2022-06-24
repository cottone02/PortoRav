package it.rjcsoft.prv.service;

import java.util.List;

import it.rjcsoft.prv.dto.stili.StiliDTO;
import it.rjcsoft.prv.model.Stili;

public interface IStiliService {

	public List<StiliDTO> getAllStiliById(Integer idGeotiff);

	public void save(Stili stile);

}
