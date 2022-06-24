package it.rjcsoft.prv.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.rjcsoft.prv.dto.censimentodotazioniazienda.DotazioneDTO;
import it.rjcsoft.prv.exceptions.InternalError;
import it.rjcsoft.prv.model.Dotazione;
import it.rjcsoft.prv.repository.IDotazioneRepository;
import it.rjcsoft.prv.service.BaseService;
import it.rjcsoft.prv.service.IDotazioneService;
import it.rjcsoft.prv.utils.PrvConverterUtils;

@Service
public class DotazioneService extends BaseService implements IDotazioneService {

	@Autowired
	private IDotazioneRepository dotazioneRepository;

	@Override
	public DotazioneDTO save(DotazioneDTO newDotazioneDTO) throws InternalError {
		log.debug("START - save {}", newDotazioneDTO);
		newDotazioneDTO.setAttrezzatura(StringUtils.upperCase(newDotazioneDTO.getAttrezzatura()));
		Dotazione dotazione = null;
		try {
			dotazione = new Dotazione();
			if (PrvConverterUtils.copyProperties(dotazione, newDotazioneDTO)) {
				log.info("Model - dotazione={}", dotazione);
				dotazione = dotazioneRepository.save(dotazione);
				log.info("END - saved {}", dotazione);
				return PrvConverterUtils.initDotazioneDTO(dotazione);

			}
		} catch (Exception e) {
			log.error("ERROR saving {}, message={}", dotazione, e.getMessage(), e);
			throw new InternalError("Object not saved", newDotazioneDTO);
		}
		log.warn("END - copyProperties FAILED, newCensimentoAziendaDTO={}", newDotazioneDTO);
		return null;
	}

}
