package it.rjcsoft.prv.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.rjcsoft.prv.dto.stili.StiliDTO;
import it.rjcsoft.prv.model.Stili;
import it.rjcsoft.prv.repository.IStiliRepository;
import it.rjcsoft.prv.service.BaseService;
import it.rjcsoft.prv.service.IStiliService;
import it.rjcsoft.prv.utils.PrvConverterUtils;

@Service
public class StiliService extends BaseService implements IStiliService {

	@Autowired
	private IStiliRepository stiliRepository;

	@Override
	public List<StiliDTO> getAllStiliById(Integer idGeotiff) {
		log.info("START - idGeotiff={}", idGeotiff);
		List<Stili> stiliList = stiliRepository.findByIdGeotiff(idGeotiff);
		if (CollectionUtils.isEmpty(stiliList)) {
			log.error("Stile with idGeotiff={} not found", idGeotiff);
			return Collections.emptyList();
		}
		List<StiliDTO> returnList = new ArrayList<>();
		stiliList.forEach(model -> returnList.add(PrvConverterUtils.initStiliDTO(model)));
		log.debug("END - returnList={}", returnList);
		return returnList;
	}

	@Override
	public void save(Stili stile) {
		log.debug("START - save {}", stile);
		try {
			log.info("Model - stile={}", stile);
			stile = stiliRepository.save(stile);
			log.info("END - saved {}", stile);
		} catch (Exception e) {
			log.error("ERROR saving {}, message={}", stile, e.getMessage(), e);
			throw new InternalError("Stile" + stile + "not saved");
		}
	}

}
