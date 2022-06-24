package it.rjcsoft.prv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.rjcsoft.prv.dto.censimentoprotezioni.ProtezioneContenimentoDTO;
import it.rjcsoft.prv.exceptions.InternalError;
import it.rjcsoft.prv.model.ProtezioneContenimentoEmissioni;
import it.rjcsoft.prv.repository.IProtezioneRepository;
import it.rjcsoft.prv.service.BaseService;
import it.rjcsoft.prv.service.IProtezioneService;
import it.rjcsoft.prv.utils.PrvConverterUtils;

@Service
public class ProtezioneService extends BaseService implements IProtezioneService {

	@Autowired
	private IProtezioneRepository protezioneRepository;

	@Override
	public ProtezioneContenimentoDTO save(ProtezioneContenimentoDTO protezioneDTO) throws InternalError {
		log.debug("START - save {}", protezioneDTO);

		ProtezioneContenimentoEmissioni protezione = null;
		try {
			protezione = new ProtezioneContenimentoEmissioni();
			if (PrvConverterUtils.copyProperties(protezione, protezioneDTO)) {
				log.info("Model - protezione={}", protezione);
				protezione = protezioneRepository.save(protezione);
				log.info("END - saved {}", protezione);
				return PrvConverterUtils.initProtezioneDTO(protezione);

			}
		} catch (Exception e) {
			log.error("ERROR saving {}, message={}", protezione, e.getMessage(), e);
			throw new InternalError("Object not saved", protezioneDTO);
		}
		log.warn("END - copyProperties FAILED, protezioneDTO={}", protezioneDTO);
		return null;
	}

}
