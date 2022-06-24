package it.rjcsoft.prv.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.rjcsoft.prv.dto.lavorazioneprodottodettagli.LavorazioneDTO;
import it.rjcsoft.prv.exceptions.InternalError;
import it.rjcsoft.prv.model.Lavorazione;
import it.rjcsoft.prv.repository.ILavorazioneRepository;
import it.rjcsoft.prv.utils.PrvConverterUtils;

@Service
public class LavorazioneService extends BaseService implements ILavorazioneService {

	@Autowired
	private ILavorazioneRepository lavorazioneRepository;

	@Override
	public List<LavorazioneDTO> findAllLavorazione() {
		log.debug("START - getAll lavorazione");
		List<Lavorazione> lavorazioneList = lavorazioneRepository.findAll();
		List<LavorazioneDTO> dtoList = new ArrayList<>();
		lavorazioneList.forEach(lavorazione -> dtoList.add(PrvConverterUtils.initLavorazioneDTO(lavorazione)));
		return dtoList;
	}

	@Override
	public LavorazioneDTO save(LavorazioneDTO newLavorazioneDTO) throws InternalError {
		log.debug("START - save {}", newLavorazioneDTO);
		newLavorazioneDTO.setLavorazione(StringUtils.upperCase(newLavorazioneDTO.getLavorazione()));
		Lavorazione lavorazione = null;
		try {
			lavorazione = new Lavorazione();
			if (PrvConverterUtils.copyProperties(lavorazione, newLavorazioneDTO)) {
				log.info("Model - lavorazione={}", lavorazione);
				lavorazione = lavorazioneRepository.save(lavorazione);
				log.info("END - saved {}", lavorazione);
				return PrvConverterUtils.initLavorazioneDTO(lavorazione);

			}
		} catch (Exception e) {
			log.error("ERROR saving {}, message={}", lavorazione, e.getMessage(), e);
			throw new InternalError("Object not saved", newLavorazioneDTO);
		}
		log.warn("END - copyProperties FAILED, newLavorazioneDTO={}", newLavorazioneDTO);
		return null;
	}

}
