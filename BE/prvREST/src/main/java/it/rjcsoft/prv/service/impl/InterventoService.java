package it.rjcsoft.prv.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.rjcsoft.prv.dto.censimentointerventi.InterventoDTO;
import it.rjcsoft.prv.exceptions.InternalError;
import it.rjcsoft.prv.model.InterventoMitigazionePolveri;
import it.rjcsoft.prv.repository.IInterventoRepository;
import it.rjcsoft.prv.service.BaseService;
import it.rjcsoft.prv.service.IInterventoService;
import it.rjcsoft.prv.utils.PrvConverterUtils;

@Service
public class InterventoService extends BaseService implements IInterventoService {

	@Autowired
	private IInterventoRepository interventoRepository;

	@Override
	public InterventoDTO save(InterventoDTO newInterventoDTO) throws InternalError {
		log.debug("START - save {}", newInterventoDTO);
		newInterventoDTO.setTipologia(StringUtils.upperCase(newInterventoDTO.getTipologia()));
		InterventoMitigazionePolveri intervento = null;
		try {
			intervento = new InterventoMitigazionePolveri();
			if (PrvConverterUtils.copyProperties(intervento, newInterventoDTO)) {
				log.info("Model - intervento={}", intervento);
				intervento = interventoRepository.save(intervento);
				log.info("END - saved {}", intervento);
				return PrvConverterUtils.initInterventoDTO(intervento);

			}
		} catch (Exception e) {
			log.error("ERROR saving {}, message={}", intervento, e.getMessage(), e);
			throw new InternalError("Object not saved", newInterventoDTO);
		}
		log.warn("END - copyProperties FAILED, newInterventoDTO={}", newInterventoDTO);
		return null;
	}

}
