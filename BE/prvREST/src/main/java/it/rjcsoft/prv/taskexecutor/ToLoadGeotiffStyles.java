package it.rjcsoft.prv.taskexecutor;

import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.rjcsoft.prv.dto.geotiff.GeotiffAllDTO;
import it.rjcsoft.prv.service.IGeotiffStyleService;

public class ToLoadGeotiffStyles implements Runnable {

	protected final Logger log = LoggerFactory.getLogger(getClass());
	private GeotiffAllDTO dto;
	private IGeotiffStyleService geotiffStyleService;
	private final Object TID;

	public ToLoadGeotiffStyles(GeotiffAllDTO dto, IGeotiffStyleService service, Object TID) {
		this.dto = dto;
		this.geotiffStyleService = service;
		this.TID = TID;
	}

	public GeotiffAllDTO getDto() {
		return dto;
	}

	public void setDto(GeotiffAllDTO dto) {
		this.dto = dto;
	}

	@Override
	public void run() {
		MDC.put("PRV_TID", TID);
		if (dto != null) {

			log.info("START - Generazione stili geotiff. dto={}", dto);
			try {
				geotiffStyleService.generateStyle(this.dto, false, false, "");
				geotiffStyleService.generateStyle(this.dto, true, false, " CON TRASPARENZA");
				geotiffStyleService.generateStyle(this.dto, true, true, " CON SPECTRUM E TRASPARENZA");
				geotiffStyleService.generateStyle(this.dto, false, true, " CON SPECTRUM");
				log.info("END - Generazione stili geotiff completata, dto={}", this.dto);
			} catch (Exception e) {

				log.error("ERROR saving {}, message={}", e.getMessage(), e);
			}
		} else {
			log.warn("END - Generazione stili geotiff non riuscita => {}", this.dto);
		}

	}

}
