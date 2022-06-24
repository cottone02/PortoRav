package it.rjcsoft.prv.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.querydsl.core.types.Predicate;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.rjcsoft.prv.dto.lavorazioneprodotto.LavorazioneProdottoDTO;
import it.rjcsoft.prv.dto.lavorazioneprodotto.LavorazioneProdottoUpdateDTO;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.exceptions.InternalError;
import it.rjcsoft.prv.exceptions.PojoNotFound;
import it.rjcsoft.prv.model.LavorazioneProdotto;
import it.rjcsoft.prv.model.LavorazioneProdottoDettagli;
import it.rjcsoft.prv.repository.ILavorazioneProdottoRepository;
import it.rjcsoft.prv.service.BaseService;
import it.rjcsoft.prv.service.ILavorazioneProdottoService;
import it.rjcsoft.prv.utils.PrvConverterUtils;

@Service
public class LavorazioneProdottoService extends BaseService implements ILavorazioneProdottoService{
	
	@Autowired
    private ILavorazioneProdottoRepository lavorazioneProdottoRepository;
	
	@Override
	public Page<LavorazioneProdottoDTO> findAll(Predicate predicate, Pageable pageable) {
		log.debug("START - predicate={}, pageable={}", predicate, pageable);
        Page<LavorazioneProdotto> lavorazioneProdottoPage = lavorazioneProdottoRepository.findAll(predicate, pageable);
        log.trace("lavorazioneProdottoPage={}", lavorazioneProdottoPage);
        Page<LavorazioneProdottoDTO> respPage = lavorazioneProdottoPage.map(currLavorazioneProdotto -> {
           LavorazioneProdottoDTO dto = new LavorazioneProdottoDTO();
            if (PrvConverterUtils.copyProperties(dto, currLavorazioneProdotto)) {
				
            	List<LavorazioneProdottoDettagli> detailsList = null;
				dto.setnLavorazioni(0);
				if (CollectionUtils.isNotEmpty(currLavorazioneProdotto.getListalavorazioneProdottoDettagli())) {
					detailsList = currLavorazioneProdotto.getListalavorazioneProdottoDettagli().stream().limit(10).collect(Collectors.toList());
					dto.setnLavorazioni(currLavorazioneProdotto.getListalavorazioneProdottoDettagli().size());
				} 
            	if (CollectionUtils.isNotEmpty(detailsList)) {
            		dto.setLavorazioneProdottoDettagliFullDTO(PrvConverterUtils.initLavorazioneProdottoDettagliFullListDTO(detailsList));
					
            	}
                log.trace("copyProperties SUCCESS, dto={}", dto);
                return dto;
            }
            log.warn("copyProperties FAILED, currLavorazioneProdotto={}", currLavorazioneProdotto);
            return null;
        });
        log.trace("END - page={}", respPage);
        return respPage;
	}

	@Override
	public void update(@Valid LavorazioneProdottoUpdateDTO lavorazioneProdottoUpdateDTO) throws BaseEx {
		log.debug("START - update {}", lavorazioneProdottoUpdateDTO);
		Integer id = lavorazioneProdottoUpdateDTO.getId();
    
        Optional<LavorazioneProdotto> lavorazioneProdottoOptional = lavorazioneProdottoRepository.findById(id);
		if (!lavorazioneProdottoOptional.isPresent()) {
			log.warn("LavorazioneProdotto with id={} not found", id);
			throw new PojoNotFound("LavorazioneProdotto with id " + id + " not found");
		}

        LavorazioneProdotto lavorazioneProdotto = lavorazioneProdottoOptional.get();
        log.trace("lavorazioneProdotto={}", lavorazioneProdotto);
        
		try {
			if (PrvConverterUtils.copyPropertiesNotNull(lavorazioneProdotto, lavorazioneProdottoUpdateDTO)) {
				log.info("Model - lavorazioneProdotto={}", lavorazioneProdotto);
				lavorazioneProdotto = lavorazioneProdottoRepository.save(lavorazioneProdotto);
				log.info("END - saved {}", lavorazioneProdotto);
				return;
			}
		} catch (Exception e) {
			log.error("ERROR saving {}, message={}", lavorazioneProdotto, e.getMessage(), e);
			throw new InternalError("Object not saved", lavorazioneProdottoUpdateDTO);
		}
		log.warn("END - copyProperties FAILED, lavorazioneProdottoUpdateDTO={}", lavorazioneProdottoUpdateDTO);
		
	}
}