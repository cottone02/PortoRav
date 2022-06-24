package it.rjcsoft.prv.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.rjcsoft.prv.dto.lavorazioneprodottodettagli.TipoLavorazioneDTO;
import it.rjcsoft.prv.model.TipoLavorazione;
import it.rjcsoft.prv.repository.ITipoLavorazioneRepository;
import it.rjcsoft.prv.service.BaseService;
import it.rjcsoft.prv.service.ITipoLavorazioneService;
import it.rjcsoft.prv.utils.PrvConverterUtils;

@Service
public class TipoLavorazioneService extends BaseService implements ITipoLavorazioneService {
	
	@Autowired
    private ITipoLavorazioneRepository tipoLavorazioneRepository;

	@Override
	public List<TipoLavorazioneDTO> findAllTipoLavorazione() {
		log.debug("START - getAll tipoLavorazione");
        List<TipoLavorazione> tipoLavorazioneList = tipoLavorazioneRepository.findAll();
        List<TipoLavorazioneDTO> dtoList = new ArrayList<>();
		tipoLavorazioneList.forEach(tipoLavorazione -> dtoList.add(PrvConverterUtils.initTipoLavorazioneDTO(tipoLavorazione)));
		return dtoList;
	}
	
	
}
