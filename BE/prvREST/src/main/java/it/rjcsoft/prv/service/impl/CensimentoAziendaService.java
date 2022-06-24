package it.rjcsoft.prv.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.querydsl.core.types.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.rjcsoft.prv.dto.CodiceIstatAttivitaDTO;
import it.rjcsoft.prv.dto.censimentoazienda.CensimentoAziendaAdditionalInfo;
import it.rjcsoft.prv.dto.censimentoazienda.CensimentoAziendaDTO;
import it.rjcsoft.prv.dto.censimentoazienda.CensimentoAziendaFullDTO;
import it.rjcsoft.prv.dto.censimentoazienda.CensimentoUpdateDTO;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.exceptions.InternalError;
import it.rjcsoft.prv.exceptions.PojoNotFound;
import it.rjcsoft.prv.model.CensimentoAzienda;
import it.rjcsoft.prv.model.CodiceIstatAttivita;
import it.rjcsoft.prv.repository.ICensimentoAziendaAdditionInfoRepository;
import it.rjcsoft.prv.repository.ICensimentoAziendaRepository;
import it.rjcsoft.prv.repository.ICodiceIstatRepository;
import it.rjcsoft.prv.service.BaseService;
import it.rjcsoft.prv.service.ICensimentoAziendaService;
import it.rjcsoft.prv.utils.PrvConverterUtils;
import it.rjcsoft.prv.utils.PrvUtils;

@Service
public class CensimentoAziendaService extends BaseService implements ICensimentoAziendaService {
	
	@Autowired
	private ICensimentoAziendaAdditionInfoRepository censimentoAziendaAdditionalInfoRepo;
	
	@Autowired
	private ICensimentoAziendaRepository censimentoAziendaRepository;

	@Autowired
	private ICodiceIstatRepository codiceIstatRepository;

	@Override
	public Page<CensimentoAziendaFullDTO> findAll(Predicate predicate, Pageable pageable) {
		log.debug("START - predicate={}, pageable={}", predicate, pageable);
		Page<CensimentoAzienda> censimentiPage = censimentoAziendaRepository.findAll(predicate, pageable);
		log.trace("censimentiPage={}", censimentiPage);
		List<CodiceIstatAttivita> codiciIstat = codiceIstatRepository.findAll();
		Set<Integer> idCensimenti = new HashSet<>();
		censimentiPage.map(currCensimento -> idCensimenti.add(currCensimento.getId()));
		List<CensimentoAziendaAdditionalInfo> additionalInfos = censimentoAziendaAdditionalInfoRepo.findAllByIds((StringUtils.join(idCensimenti, ",")));
		Page<CensimentoAziendaFullDTO> respPage = censimentiPage.map(currCensimento -> {
			CensimentoAziendaAdditionalInfo additionalInfo = additionalInfos.stream()
					  .filter(info -> currCensimento.getId().equals(info.getCensimentoAziendaId()))
					  .findAny()
					  .orElse(new CensimentoAziendaAdditionalInfo());
			CensimentoAziendaFullDTO dto = new CensimentoAziendaFullDTO();
			if (additionalInfo.getComponentCounter() != null) {
				try {
					dto.setComponentCounter(additionalInfo.getComponentCounter());
				} catch (Exception e) {
					log.error(e.getMessage());
					dto.setComponentCounter(null);
				}
			}
			dto.setCanDelete(additionalInfo.getCanDelete());
			if (PrvConverterUtils.copyProperties(dto, currCensimento)) {
				CodiceIstatAttivitaDTO codiceIstatAttivitaDto = PrvUtils
						.findCodiceIstatAttivitaDto(currCensimento.getCodIstatAttivita(), codiciIstat);
				dto.setCodiceIstatAttivita(codiceIstatAttivitaDto);
				log.trace("copyProperties SUCCESS, dto={}", dto);
				return dto;
			}
			log.warn("copyProperties FAILED, currCensimento={}", currCensimento);
			return null;
		});
		log.trace("END - page={}", respPage);
		return respPage;
	}

	@Override
	public CensimentoAziendaDTO save(CensimentoAziendaDTO newCensimentoAziendaDTO) throws PojoNotFound, InternalError {
		log.debug("START - save {}", newCensimentoAziendaDTO);
		Integer id = newCensimentoAziendaDTO.getId();
		if (id != null && !censimentoAziendaRepository.existsById(id)) {
			log.warn("id={} not found", id);
			throw new PojoNotFound("CensimentoAzienda with id " + id + " not found");
		}
		CensimentoAzienda censimentoAzienda = null;
		try {
			censimentoAzienda = new CensimentoAzienda();
			if (PrvConverterUtils.copyProperties(censimentoAzienda, newCensimentoAziendaDTO)) {
				
				censimentoAzienda.setCodIstatAttivita(newCensimentoAziendaDTO.getCodiceIstatAttivita());	
				log.info("Model - censimentoAzienda={}", censimentoAzienda);
				censimentoAzienda.setUpdated(new Timestamp(System.currentTimeMillis()));
				censimentoAzienda = censimentoAziendaRepository.save(censimentoAzienda);
				log.info("END - saved {}", censimentoAzienda);
				return PrvConverterUtils.initCensimentoAziendaDTO(censimentoAzienda);

			}
		} catch (Exception e) {
			log.error("ERROR saving {}, message={}", censimentoAzienda, e.getMessage(), e);
			throw new InternalError("Object not saved", newCensimentoAziendaDTO);
		}
		log.warn("END - copyProperties FAILED, newCensimentoAziendaDTO={}", newCensimentoAziendaDTO);
		return null;

	}

	@Override
	public void deleteById(int id) throws PojoNotFound, InternalError {
		log.debug("START - delete censimentoAzienda id={}", id);

		censimentoAziendaRepository.findById(id)
				.orElseThrow(() -> new PojoNotFound("CensimentoAzienda with id " + id + " not found"));
		try {
			censimentoAziendaRepository.deleteById(id);
			log.info("END - deleted {}", id);
		} catch (Exception e) {
			log.error("END - not deleted {}, message={}", id, e.getMessage(), e);
			throw new InternalError("censimentoAzienda id=" + id + " non deleted");
		}
	}

	@Override
	public List<CodiceIstatAttivitaDTO> findAllCodiciIstat() {
		log.debug("START - getAll codiceIstat");
		List<CodiceIstatAttivita> codici = codiceIstatRepository.findAll();
		List<CodiceIstatAttivitaDTO> dtoList = new ArrayList<>();
		codici.forEach(codice -> dtoList.add(PrvConverterUtils.initCodiceIstatDTO(codice)));
		return dtoList;
	}

	@Override
	@Transactional(rollbackFor = { BaseEx.class })
	public CensimentoUpdateDTO update(CensimentoUpdateDTO censimentoUpdateDTO) throws BaseEx {
		log.debug("START - update {}", censimentoUpdateDTO);
		Integer id = censimentoUpdateDTO.getId();

		CensimentoAzienda censimentoAzienda = censimentoAziendaRepository.findById(id)
				.orElseThrow(() -> new PojoNotFound("CensimentoAzienda with id " + id + " not found"));

		try {
			if (PrvConverterUtils.copyPropertiesNotNull(censimentoAzienda, censimentoUpdateDTO)) {
				log.info("Model - censimentoAzienda={}", censimentoAzienda);
				censimentoAzienda.setUpdated(new Timestamp(System.currentTimeMillis()));
				censimentoAzienda = censimentoAziendaRepository.save(censimentoAzienda);
				log.info("END - saved {}", censimentoAzienda);
				return PrvConverterUtils.initCensimentoUpdateDTO(censimentoAzienda);

			}
		} catch (Exception e) {
			log.error("ERROR saving {}, message={}", censimentoAzienda, e.getMessage(), e);
			throw new InternalError("Object not saved", censimentoUpdateDTO);
		}
		log.warn("END - copyProperties FAILED, censimentoUpdateDTO={}", censimentoUpdateDTO);
		return null;

	}

	@Override
	public Object getTotalNumber() {
        log.info("START - getting total number of censimentiAzienda");

        return censimentoAziendaRepository.count();
	}

}
