package it.rjcsoft.prv.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.querydsl.core.types.Predicate;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.rjcsoft.prv.dto.lavorazioneprodottodettagli.LavorazioneDTO;
import it.rjcsoft.prv.dto.lavorazioneprodottodettagli.LavorazioneETipoLavorazioneDTO;
import it.rjcsoft.prv.dto.lavorazioneprodottodettagli.LavorazioneProdottoDettagliFullDTO;
import it.rjcsoft.prv.dto.lavorazioneprodottodettagli.LavorazioneProdottoDettagliInsertFullDTO;
import it.rjcsoft.prv.dto.lavorazioneprodottodettagli.TipoLavorazioneDTO;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.exceptions.InternalError;
import it.rjcsoft.prv.exceptions.PojoNotFound;
import it.rjcsoft.prv.model.Lavorazione;
import it.rjcsoft.prv.model.LavorazioneProdottoDettagli;
import it.rjcsoft.prv.model.TipoLavorazione;
import it.rjcsoft.prv.repository.ILavorazioneProdottoDettagliRepository;
import it.rjcsoft.prv.repository.ILavorazioneRepository;
import it.rjcsoft.prv.repository.ILavorazioniInLavorazioneProdottoDettagliRepository;
import it.rjcsoft.prv.repository.ITipoLavorazioneRepository;
import it.rjcsoft.prv.service.BaseService;
import it.rjcsoft.prv.service.ILavorazioneProdottoDettagliService;
import it.rjcsoft.prv.service.ILavorazioneService;
import it.rjcsoft.prv.utils.PrvConverterUtils;

@Service
public class LavorazioneProdottoDettagliService extends BaseService implements ILavorazioneProdottoDettagliService {
	
	@Autowired
	private ILavorazioneService lavorazioneService;
	
	@Autowired
	private ILavorazioneProdottoDettagliRepository lavorazioneProdottoDettagliRepository;
	
	@Autowired
	private ILavorazioneRepository lavorazioneRepository;

	@Autowired
	private ITipoLavorazioneRepository tipoLavorazioneRepository;
	
	@Autowired
	private ILavorazioniInLavorazioneProdottoDettagliRepository lavorazioniInLavorazioneProdottoDettagliRepository;

	

	@Override
	public Page<LavorazioneProdottoDettagliFullDTO> findAll(Predicate predicate, Pageable pageable) {
		log.debug("START - predicate={}, pageable={}", predicate, pageable);
		Page<LavorazioneProdottoDettagli> lavorazioneProdottoDettagliPage = lavorazioneProdottoDettagliRepository.findAll(predicate,
				pageable);
		log.trace("lavorazioneProdottoDettagliPage={}", lavorazioneProdottoDettagliPage);
		Page<LavorazioneProdottoDettagliFullDTO> respPage = lavorazioneProdottoDettagliPage.map(currLavorazioneProdottoDettagli -> {
			LavorazioneProdottoDettagliFullDTO dto = new LavorazioneProdottoDettagliFullDTO();
			if (PrvConverterUtils.copyProperties(dto, currLavorazioneProdottoDettagli)) {
				dto.setProdottoDettagliId(currLavorazioneProdottoDettagli.getProdottoDettaglioId());
				dto.setTipoLavorazione(currLavorazioneProdottoDettagli.getTipoLavorazione().getTipo());
				Lavorazione lavorazione = currLavorazioneProdottoDettagli.getLavorazione();
				if(lavorazione != null) {
					dto.setLavorazioneDTO(PrvConverterUtils.initLavorazioneDTO(lavorazione));
				}
				
				log.trace("copyProperties SUCCESS, dto={}", dto);
				return dto;
			}
			log.warn("copyProperties FAILED, currLavorazioneProdottoDettagli={}", currLavorazioneProdottoDettagli);
			return null;
		});
		log.trace("END - page={}", respPage);
		return respPage;
	}

	@Override
	public LavorazioneETipoLavorazioneDTO findAllLavorazioneETipoLavorazione() {
		
		log.debug("START - getAll lavorazione e tipoLavorazione");
		
		List<Lavorazione> lavorazioneList = lavorazioneRepository.findAll();
		List<TipoLavorazione> tipoLavorazioneList = tipoLavorazioneRepository.findAll();
		
		List<LavorazioneDTO> lavorazioneDTOList = new ArrayList<>();
	    List<TipoLavorazioneDTO> tipoLavorazioneDTOList = new ArrayList<>();
		
	    LavorazioneETipoLavorazioneDTO dto = new LavorazioneETipoLavorazioneDTO();
	    
		lavorazioneList.forEach(lavorazione -> lavorazioneDTOList.add(PrvConverterUtils.initLavorazioneDTO(lavorazione)));
		lavorazioneList.forEach(lavorazione -> dto.setListaLavorazioni(lavorazioneDTOList));
		tipoLavorazioneList.forEach(tipoLavorazione -> tipoLavorazioneDTOList.add(PrvConverterUtils.initTipoLavorazioneDTO(tipoLavorazione)));
		tipoLavorazioneList.forEach(tipoLavorazione -> dto.setListaTipoLavorazioni(tipoLavorazioneDTOList));
		
		return dto;
		
	}
	
	@Override
	@Transactional(rollbackFor = { InternalError.class })
	public LavorazioneProdottoDettagliInsertFullDTO save(LavorazioneProdottoDettagliInsertFullDTO newLavorazioneProdottoDettagliInsertFullDTO
			) throws InternalError {
		log.debug("START - save {}", newLavorazioneProdottoDettagliInsertFullDTO);

		LavorazioneProdottoDettagli lavorazioneProdottoDettagli = null;
		

		try {
			
			LavorazioneDTO lavorazioneDTO = newLavorazioneProdottoDettagliInsertFullDTO.getLavorazioneDTO();
			lavorazioneDTO = insertLavorazioneIfNotExists(newLavorazioneProdottoDettagliInsertFullDTO.getLavorazioneDTO());
			
			lavorazioneProdottoDettagli = new LavorazioneProdottoDettagli();
			lavorazioneProdottoDettagli.setLavorazione(PrvConverterUtils.initLavorazione(lavorazioneDTO));
			lavorazioneProdottoDettagli.setTipoLavorazione(PrvConverterUtils.initTipoLavorazione(newLavorazioneProdottoDettagliInsertFullDTO.getTipoLavorazioneDTO()));
			lavorazioneProdottoDettagli.setProdottoDettaglioId(newLavorazioneProdottoDettagliInsertFullDTO.getProdottoDettagliId());
			lavorazioneProdottoDettagli.setGgAnno(newLavorazioneProdottoDettagliInsertFullDTO.getGgAnno());
			lavorazioneProdottoDettagli.setOreGg(newLavorazioneProdottoDettagliInsertFullDTO.getOreGg());
			
			log.trace("Model - lavorazioneProdottoDettagli={}", lavorazioneProdottoDettagli);
			lavorazioneProdottoDettagli = lavorazioneProdottoDettagliRepository.save(lavorazioneProdottoDettagli);
			log.info("END - saved {}", lavorazioneProdottoDettagli);
			return PrvConverterUtils.initLavorazioneProdottoDettagliInsertFullDTO(lavorazioneProdottoDettagli);

		} catch (Exception e) {
			log.error("ERROR saving {}, message={}", lavorazioneProdottoDettagli, e.getMessage(), e);
			throw new InternalError("Object not saved", newLavorazioneProdottoDettagliInsertFullDTO);
		}
		
	}
	
	@Override
	@Transactional(rollbackFor = { InternalError.class })
	public LavorazioneDTO saveLavorazione(LavorazioneDTO newLavorazioneDTO) throws InternalError {
		log.debug("START - save {}", newLavorazioneDTO);
		newLavorazioneDTO.setLavorazione(StringUtils.upperCase(newLavorazioneDTO.getLavorazione()));

		Lavorazione lavorazione = null;
		
		try {
			
			lavorazione = new Lavorazione();
			lavorazione = PrvConverterUtils.initLavorazione(newLavorazioneDTO);
			
			if (newLavorazioneDTO.getId() == null) {
				log.info("lavorazioneId IS NULL, insert new Lavorazione");
				lavorazione = lavorazioneRepository.save(lavorazione);
			}
			
			return PrvConverterUtils.initLavorazioneDTO(lavorazione);

		} catch (Exception e) {
			log.error("ERROR saving {}, message={}", lavorazione, e.getMessage(), e);
			throw new InternalError("Object not saved", newLavorazioneDTO);
		}
		
	}
	

	@Override
	@Transactional(rollbackFor = { BaseEx.class })
	public void deleteById(int id) throws BaseEx {
        log.debug("START - delete lavorazioneProdottoDettagli id={}", id);

		lavorazioneProdottoDettagliRepository.findById(id)
				.orElseThrow(() -> new PojoNotFound("lavorazioneProdottoDettagli with id " + id + " not found"));
		try {
			lavorazioneProdottoDettagliRepository.deleteById(id);
			log.info("END - deleted {}", id);
		} catch (Exception e) {
			log.error("END - not deleted {}, message={}", id, e.getMessage(), e);
			throw new InternalError("modulo id=" + id + " non deleted");
		}
    }

	@Override
	public void update(LavorazioneProdottoDettagliInsertFullDTO lavorazioneProdottoDettagliInsertFullDTO) throws BaseEx{
	
		log.debug("START - lavorazioneProdottoDettagliInsertFullDTO", lavorazioneProdottoDettagliInsertFullDTO);

		Optional<LavorazioneProdottoDettagli> lavorazioneProdottoDettagliOptional = lavorazioneProdottoDettagliRepository
				.findById(lavorazioneProdottoDettagliInsertFullDTO.getId());
		if (lavorazioneProdottoDettagliOptional.isEmpty()) {
			log.warn("lavorazioneProdottoDettagli with id={} not found", lavorazioneProdottoDettagliInsertFullDTO.getId());
			throw new PojoNotFound("lavorazioneProdottoDettagli with id " + lavorazioneProdottoDettagliInsertFullDTO.getId() + " not found");
		}

		LavorazioneProdottoDettagli lavorazioneProdottoDettagli = null;

		try {
			
			LavorazioneDTO lavorazioneDTO = lavorazioneProdottoDettagliInsertFullDTO.getLavorazioneDTO();
			lavorazioneDTO = insertLavorazioneIfNotExists(lavorazioneProdottoDettagliInsertFullDTO.getLavorazioneDTO());
			
			lavorazioneProdottoDettagli = new LavorazioneProdottoDettagli();
			lavorazioneProdottoDettagli.setLavorazione(PrvConverterUtils.initLavorazione(lavorazioneDTO));
			lavorazioneProdottoDettagli.setTipoLavorazione(PrvConverterUtils.initTipoLavorazione(lavorazioneProdottoDettagliInsertFullDTO.getTipoLavorazioneDTO()));
			lavorazioneProdottoDettagli.setProdottoDettaglioId(lavorazioneProdottoDettagliInsertFullDTO.getProdottoDettagliId());
			lavorazioneProdottoDettagli.setId(lavorazioneProdottoDettagliInsertFullDTO.getId());
			lavorazioneProdottoDettagli.setGgAnno(lavorazioneProdottoDettagliInsertFullDTO.getGgAnno());
			lavorazioneProdottoDettagli.setOreGg(lavorazioneProdottoDettagliInsertFullDTO.getOreGg());
			
			
			log.trace("Model - lavorazioneProdottoDettagli={}", lavorazioneProdottoDettagli);
			lavorazioneProdottoDettagli = lavorazioneProdottoDettagliRepository.save(lavorazioneProdottoDettagli);
			log.info("END - saved {}", lavorazioneProdottoDettagli);
			

		} catch (Exception e) {
			log.error("ERROR saving {}, message={}", lavorazioneProdottoDettagli, e.getMessage(), e);
		}
		
	}	
	
	LavorazioneDTO insertLavorazioneIfNotExists(LavorazioneDTO lavorazioneDTO) throws InternalError {
		
		try {
			if (lavorazioneDTO != null) {
				if(lavorazioneDTO.getId() == 0) {
					lavorazioneDTO = lavorazioneService.save(lavorazioneDTO);
				}
				
			}
			return lavorazioneDTO;

		} catch (Exception e) {
			log.error("ERROR saving {}, message={}", lavorazioneDTO, e.getMessage(), e);
			throw new InternalError("Object not saved", lavorazioneDTO);
		}
		
	}

	@Override
	public List<LavorazioneDTO> findAvailableLavorazioniInLavorazioneProdottoDettagli(Integer prodottoDettaglioId) {
		log.debug("START - getAvailable lavorazione");
        List<Lavorazione> lavorazioni = lavorazioniInLavorazioneProdottoDettagliRepository.findAvailableLavorazioneInLavorazioneProdottoDettagli(prodottoDettaglioId);
        if (CollectionUtils.isEmpty(lavorazioni)) return Collections.emptyList();
        List<LavorazioneDTO> dtoList = new ArrayList<>();
        lavorazioni.forEach(lavorazione -> dtoList.add(PrvConverterUtils.initLavorazioneDTO(lavorazione)));
        return dtoList;
	}
	

}
