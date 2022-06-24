package it.rjcsoft.prv.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.querydsl.core.types.Predicate;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.rjcsoft.prv.dto.movimentazione.MovimentazioneFullDTO;
import it.rjcsoft.prv.dto.movimentazione.MovimentazioneUpdateDTO;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.exceptions.InternalError;
import it.rjcsoft.prv.exceptions.PojoNotFound;
import it.rjcsoft.prv.model.DettaglioMovimentazione;
import it.rjcsoft.prv.model.Movimentazione;
import it.rjcsoft.prv.repository.IMovimentazioneRepository;
import it.rjcsoft.prv.service.BaseService;
import it.rjcsoft.prv.service.IMovimentazioneService;
import it.rjcsoft.prv.utils.PrvConverterUtils;

@Service
public class MovimentazioneService extends BaseService implements IMovimentazioneService {

	@Autowired
	private IMovimentazioneRepository movimentazioneRepository;

	@Override
	public Page<MovimentazioneFullDTO> findAll(Predicate predicate, Pageable pageable) {
		log.debug("START - predicate={}, pageable={}", predicate, pageable);
		Page<Movimentazione> movimentazionePage = movimentazioneRepository.findAll(predicate, pageable);
		log.trace("movimentazionePage={}", movimentazionePage);
		Page<MovimentazioneFullDTO> respPage = movimentazionePage.map(currMovimentazione -> {
			MovimentazioneFullDTO dto = new MovimentazioneFullDTO();
			if (PrvConverterUtils.copyProperties(dto, currMovimentazione)) {

            	List<DettaglioMovimentazione> detailsList = null;
            	
            	if (CollectionUtils.isNotEmpty(currMovimentazione.getDettagliMovimentazione())) {
					detailsList = currMovimentazione.getDettagliMovimentazione().stream().limit(10).collect(Collectors.toList());
				} 
            	if (CollectionUtils.isNotEmpty(detailsList)) {
            		dto.setDettagliMovimentazione(PrvConverterUtils.initDettagliMovimentazioneDTO(detailsList));
            	}	

				log.trace("copyProperties SUCCESS, dto={}", dto);
				return dto;
			}
			log.warn("copyProperties FAILED, currCumuliAperto={}", currMovimentazione);
			return null;
		});
		log.trace("END - page={}", respPage);
		return respPage;
	}

	@Override
	public void update(@Valid MovimentazioneUpdateDTO movimentazioneUpdateDTO) throws BaseEx {
		log.debug("START - update {}", movimentazioneUpdateDTO);
		Integer id = movimentazioneUpdateDTO.getId();

		Optional<Movimentazione> movimentazioneOptional = movimentazioneRepository.findById(id);
		if (!movimentazioneOptional.isPresent()) {
			log.warn("Movimentazione with id={} not found", id);
			throw new PojoNotFound("Movimentazione with id " + id + " not found");
		}

		Movimentazione movimentazione = movimentazioneOptional.get();
		log.trace("movimentazione={}", movimentazione);

		try {
			if (PrvConverterUtils.copyPropertiesNotNull(movimentazione, movimentazioneUpdateDTO)) {
				log.info("Model - prodottoDettagli={}", movimentazione);
				movimentazione = movimentazioneRepository.save(movimentazione);
				log.info("END - saved {}", movimentazione);
				return;
			}
		} catch (Exception e) {
			log.error("ERROR saving {}, message={}", movimentazione, e.getMessage(), e);
			throw new InternalError("Object not saved", movimentazioneUpdateDTO);
		}
		log.warn("END - copyProperties FAILED, movimentazioneUpdateDTO={}", movimentazioneUpdateDTO);

	}

}
