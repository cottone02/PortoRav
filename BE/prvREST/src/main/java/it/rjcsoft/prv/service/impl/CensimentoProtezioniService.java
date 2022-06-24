package it.rjcsoft.prv.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;

import it.rjcsoft.prv.dto.censimentoprotezioni.CensimentoProtezioneDTO;
import it.rjcsoft.prv.dto.censimentoprotezioni.CensimentoProtezioniFullDTO;
import it.rjcsoft.prv.dto.censimentoprotezioni.ProtezioneContenimentoDTO;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.exceptions.InternalError;
import it.rjcsoft.prv.exceptions.PojoNotFound;
import it.rjcsoft.prv.model.CensimentoProtezioniContenimento;
import it.rjcsoft.prv.model.ProtezioneContenimentoEmissioni;
import it.rjcsoft.prv.repository.ICensimentoProtezioniRepository;
import it.rjcsoft.prv.repository.IProtezioneRepository;
import it.rjcsoft.prv.service.BaseService;
import it.rjcsoft.prv.service.ICensimentoProtezioniService;
import it.rjcsoft.prv.service.IProtezioneService;
import it.rjcsoft.prv.utils.PrvConverterUtils;

@Service
public class CensimentoProtezioniService extends BaseService implements ICensimentoProtezioniService {

	@Autowired
	private ICensimentoProtezioniRepository censimentoProtezioniRepository;

	@Autowired
	private IProtezioneService protezioneService;

	@Autowired
	private IProtezioneRepository protezioneRepository;

	@Override
	public Page<CensimentoProtezioniFullDTO> findAll(Predicate predicate, Pageable pageable) {
		log.debug("START - predicate={}, pageable={}", predicate, pageable);
		Page<CensimentoProtezioniContenimento> censimentiProtezioniPage = censimentoProtezioniRepository
				.findAll(predicate, pageable);
		log.trace("censimentiProtezioniPage={}", censimentiProtezioniPage);
		Page<CensimentoProtezioniFullDTO> respPage = censimentiProtezioniPage.map(currCensimentoProtezioni -> {
			CensimentoProtezioniFullDTO dto = new CensimentoProtezioniFullDTO();
			if (PrvConverterUtils.copyProperties(dto, currCensimentoProtezioni)) {
				// ProtezioneContenimentoEmissioni protezione = PrvUtils
				// .findProtezioneDTO(currCensimentoProtezioni.getContenimento(), protezioni);
				ProtezioneContenimentoEmissioni protezione = currCensimentoProtezioni.getContenimento();
				dto.setProtezione(PrvConverterUtils.initProtezioneDTO(protezione));
				log.trace("copyProperties SUCCESS, dto={}", dto);
				return dto;
			}
			log.warn("copyProperties FAILED, currCensimentoDotazioni={}", currCensimentoProtezioni);
			return null;
		});
		log.trace("END - page={}", respPage);
		return respPage;
	}

	@Override
	@Transactional(rollbackFor = { InternalError.class })
	public CensimentoProtezioneDTO save(@Valid CensimentoProtezioneDTO newCensimentoProtezioneDTO,
			int censimentoAziendaId) throws InternalError {
		log.debug("START - save {}", newCensimentoProtezioneDTO);

		CensimentoProtezioniContenimento censimentoProtezioniContenimento = null;

		try {
			ProtezioneContenimentoDTO protezioneDTO = newCensimentoProtezioneDTO.getProtezione();
			if (protezioneDTO.getId() == null) {
				log.info("protezioneId IS NULL, insert new ProtezioneContenimento");
				protezioneDTO = protezioneService.save(protezioneDTO);
			}
			censimentoProtezioniContenimento = new CensimentoProtezioniContenimento();
			// if (PrvConverterUtils.copyProperties(censimentoProtezioniContenimento,
			// newCensimentoProtezioneDTO)) {
			BeanUtils.copyProperties(newCensimentoProtezioneDTO, censimentoProtezioniContenimento, "contenimento");
			censimentoProtezioniContenimento.setCensimentoAziendaId(censimentoAziendaId);
			censimentoProtezioniContenimento.setContenimento(PrvConverterUtils.initProtezione(protezioneDTO));
			log.trace("Model - censimentoProtezioniContenimento={}", censimentoProtezioniContenimento);
			censimentoProtezioniContenimento = censimentoProtezioniRepository.save(censimentoProtezioniContenimento);
			log.info("END - saved {}", censimentoProtezioniContenimento);
			return PrvConverterUtils.initCensimentoProtezioniDTO(censimentoProtezioniContenimento);

			// }
		} catch (Exception e) {
			log.error("ERROR saving {}, message={}", censimentoProtezioniContenimento, e.getMessage(), e);
			throw new InternalError("Object not saved", censimentoProtezioniContenimento);
//			return null;
		}
//		log.warn("END - copyProperties FAILED, newCensimentoAziendaDTO={}", newCensimentoProtezioneDTO);
	}

	@Override
	public void deleteById(int id) throws BaseEx {
		log.debug("START - delete censimentoProtezioniId={}", id);

		censimentoProtezioniRepository.findById(id)
				.orElseThrow(() -> new PojoNotFound("CensimentoProtezioni with id " + id + " not found"));
		try {
			censimentoProtezioniRepository.deleteById(id);
			log.info("END - deleted {}", id);
		} catch (Exception e) {
			log.error("END - not deleted {}, message={}", id, e.getMessage(), e);
			throw new InternalError("CensimentoProtezioni id=" + id + " non deleted");
		}
	}

	@Override
	@Transactional(rollbackFor = { BaseEx.class })
	public void update(int id, int censimentoAziendaId, @Positive String note) throws BaseEx {
		log.debug("START - update id={}, censimentoAziendaId={}, note={}", id, censimentoAziendaId, note);

		Optional<CensimentoProtezioniContenimento> censimentoProtezioniOptional = censimentoProtezioniRepository
				.findByIdAndCensimentoAziendaId(id, censimentoAziendaId);
		if (censimentoProtezioniOptional.isEmpty()) {
			log.warn("censimentoProtezioni with id={}, censimentoAziendaId={} not found", id, censimentoAziendaId);
			throw new PojoNotFound("censimentoProtezioni with id " + id + " not found");
		}

		CensimentoProtezioniContenimento censimentoProtezioniContenimento = censimentoProtezioniOptional.get();

		try {
			censimentoProtezioniContenimento.setNote(note);
			log.info("Model - censimentoProtezioniContenimento={}", censimentoProtezioniContenimento);
			censimentoProtezioniRepository.save(censimentoProtezioniContenimento);
			log.info("END - saved {}", censimentoProtezioniContenimento);

		} catch (Exception e) {
			log.error("ERROR saving {}, message={}", censimentoProtezioniContenimento, e.getMessage(), e);
			throw new InternalError(
					String.format("censimentoDotazioni not saved id=%d, censimentoAziendaId=%d, note=%s", id,
							censimentoAziendaId, note));
		}

	}

	@Override
	public List<ProtezioneContenimentoDTO> findAllDotazioni() {
		log.debug("START - getAll protezioni");
		List<ProtezioneContenimentoEmissioni> protezioni = protezioneRepository.findAll();
		List<ProtezioneContenimentoDTO> dtoList = new ArrayList<>();
		protezioni.forEach(protezione -> dtoList.add(PrvConverterUtils.initProtezioneDTO(protezione)));
		return dtoList;
	}

}
