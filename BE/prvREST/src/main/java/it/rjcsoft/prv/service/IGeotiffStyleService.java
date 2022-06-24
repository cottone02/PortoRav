package it.rjcsoft.prv.service;

import it.rjcsoft.prv.dto.geotiff.GeotiffAllDTO;
import it.rjcsoft.prv.dto.stili.CustomStiliDTO;

public interface IGeotiffStyleService {
	public void generateCustomStyle(GeotiffAllDTO gDto, CustomStiliDTO newCustomStyleDTO) throws Exception;

	public void generateStyle(GeotiffAllDTO dto, boolean useOpacity, boolean useSpectrum, String descrizione)
			throws Exception;

	public GeotiffAllDTO createStyleByGeotiffId(int id) throws Exception;

	public GeotiffAllDTO createCustomStyleByGeotiffId(int id, CustomStiliDTO newCustomStyleDTO) throws Exception;

}
