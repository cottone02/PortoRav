package it.rjcsoft.prv.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Positive;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;

import it.rjcsoft.prv.dto.censimentodotazioniazienda.CensimentoDotazioniAziendaDTO;
import it.rjcsoft.prv.dto.censimentodotazioniazienda.CensimentoDotazioniDTO;
import it.rjcsoft.prv.dto.censimentodotazioniazienda.CensimentoDotazioniFullDTO;
import it.rjcsoft.prv.dto.censimentodotazioniazienda.DotazioneDTO;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.exceptions.InternalError;
import it.rjcsoft.prv.exceptions.PojoNotFound;
import it.rjcsoft.prv.model.CensimentoDotazioniAzienda;
import it.rjcsoft.prv.model.Dotazione;
import it.rjcsoft.prv.repository.ICensimentoDotazioniRepository;
import it.rjcsoft.prv.repository.IDotazioneRepository;
import it.rjcsoft.prv.service.BaseService;
import it.rjcsoft.prv.service.ICensimentoDotazioniService;
import it.rjcsoft.prv.service.IDotazioneService;
import it.rjcsoft.prv.utils.PrvConverterUtils;

@Service
public class CensimentoDotazioniService extends BaseService implements ICensimentoDotazioniService {

	@Autowired
	private ICensimentoDotazioniRepository censimentoDotazioniRepository;

	@Autowired
	private IDotazioneService dotazioneService;

	@Autowired
	private IDotazioneRepository dotazioneRepository;

	@Override
	public Page<CensimentoDotazioniFullDTO> findAll(Predicate predicate, Pageable pageable) {
		log.debug("START - predicate={}, pageable={}", predicate, pageable);
		Page<CensimentoDotazioniAzienda> censimentiDotazioniPage = censimentoDotazioniRepository.findAll(predicate,
				pageable);
		log.trace("censimentiDotazioniPage={}", censimentiDotazioniPage);
		Page<CensimentoDotazioniFullDTO> respPage = censimentiDotazioniPage.map(currCensimentoDotazioni -> {
			CensimentoDotazioniFullDTO dto = new CensimentoDotazioniFullDTO();
			if (PrvConverterUtils.copyProperties(dto, currCensimentoDotazioni)) {
				Dotazione dotazione = currCensimentoDotazioni.getDotazione();
				dto.setDotazioneDTO(PrvConverterUtils.initDotazioneDTO(dotazione));
				log.trace("copyProperties SUCCESS, dto={}", dto);
				return dto;
			}
			log.warn("copyProperties FAILED, currCensimentoDotazioni={}", currCensimentoDotazioni);
			return null;
		});
		log.trace("END - page={}", respPage);
		return respPage;
	}

	@Override
	@Transactional(rollbackFor = { InternalError.class })
	public CensimentoDotazioniDTO save(CensimentoDotazioniAziendaDTO newCensimentoDotazioniAziendaDTO,
			int censimentoAziendaId) throws InternalError {
		log.debug("START - save {}", newCensimentoDotazioniAziendaDTO);

		CensimentoDotazioniAzienda censimentoDotazioniAzienda = null;

		try {
			DotazioneDTO dotazioneDTO = newCensimentoDotazioniAziendaDTO.getDotazione();
			if (dotazioneDTO.getId() == null) {
				log.info("dotazioneId IS NULL, insert new Dotazione");
				dotazioneDTO = dotazioneService.save(dotazioneDTO);
			}
			censimentoDotazioniAzienda = new CensimentoDotazioniAzienda();
			BeanUtils.copyProperties(newCensimentoDotazioniAziendaDTO, censimentoDotazioniAzienda, "dotazione");
			censimentoDotazioniAzienda.setCensimentoAziendaId(censimentoAziendaId);
			censimentoDotazioniAzienda.setDotazione(PrvConverterUtils.initDotazione(dotazioneDTO));
			log.trace("Model - censimentoAzienda={}", censimentoDotazioniAzienda);
			censimentoDotazioniAzienda = censimentoDotazioniRepository.save(censimentoDotazioniAzienda);
			log.info("END - saved {}", censimentoDotazioniAzienda);
			return PrvConverterUtils.initCensimentoDotazioniDTO(censimentoDotazioniAzienda);

		} catch (Exception e) {
			log.error("ERROR saving {}, message={}", censimentoDotazioniAzienda, e.getMessage(), e);
			throw new InternalError("Object not saved", newCensimentoDotazioniAziendaDTO);
		}
	}

	@Override
	public void deleteById(int id) throws BaseEx {
		log.debug("START - delete censimentoDotazioniId={}", id);

		censimentoDotazioniRepository.findById(id)
				.orElseThrow(() -> new PojoNotFound("CensimentoDotazioni with id " + id + " not found"));
		try {
			censimentoDotazioniRepository.deleteById(id);
			log.info("END - deleted {}", id);
		} catch (Exception e) {
			log.error("END - not deleted {}, message={}", id, e.getMessage(), e);
			throw new InternalError("censimentoDotazioni id=" + id + " non deleted");
		}
	}

	@Override
	@Transactional(rollbackFor = { BaseEx.class })
	public void update(int id, int censimentoAziendaId, @Positive int quantita) throws BaseEx {
		log.debug("START - update id={}, censimentoAziendaId={}, quantita={}", id, censimentoAziendaId, quantita);

		Optional<CensimentoDotazioniAzienda> censimentoDotazioniOptional = censimentoDotazioniRepository
				.findByIdAndCensimentoAziendaId(id, censimentoAziendaId);
		if (censimentoDotazioniOptional.isEmpty()) {
			log.warn("censimentoDotazioni with id={}, censimentoAziendaId={} not found", id, censimentoAziendaId);
			throw new PojoNotFound("censimentoDotazioni with id " + id + " not found");
		}

		CensimentoDotazioniAzienda censimentoDotazioni = censimentoDotazioniOptional.get();

		try {
			censimentoDotazioni.setQuantita(quantita);
			log.info("Model - censimentoDotazioni={}", censimentoDotazioni);
			censimentoDotazioniRepository.save(censimentoDotazioni);
			log.info("END - saved {}", censimentoDotazioni);

		} catch (Exception e) {
			log.error("ERROR saving {}, message={}", censimentoDotazioni, e.getMessage(), e);
			throw new InternalError(
					String.format("censimentoDotazioni not saved id=%d, censimentoAziendaId=%d, quantita=%d", id,
							censimentoAziendaId, quantita));
		}

	}

	@Override
	public List<DotazioneDTO> findAllDotazioni() {
		log.debug("START - getAll dotazioni");
		List<Dotazione> dotazioni = dotazioneRepository.findAll();
		List<DotazioneDTO> dtoList = new ArrayList<>();
		dotazioni.forEach(dotazione -> dtoList.add(PrvConverterUtils.initDotazioneDTO(dotazione)));
		return dtoList;
	}

}
