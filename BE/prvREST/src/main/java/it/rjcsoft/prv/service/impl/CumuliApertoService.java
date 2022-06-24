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

import it.rjcsoft.prv.dto.cumuliaperto.CumuliApertoFullDTO;
import it.rjcsoft.prv.dto.cumuliaperto.CumuliApertoUpdateDTO;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.exceptions.InternalError;
import it.rjcsoft.prv.exceptions.PojoNotFound;
import it.rjcsoft.prv.model.CumuliAperto;
import it.rjcsoft.prv.model.CumuliApertoLocalizzazione;
import it.rjcsoft.prv.repository.ICumuliApertoRepository;
import it.rjcsoft.prv.service.BaseService;
import it.rjcsoft.prv.service.ICumuliApertoService;
import it.rjcsoft.prv.utils.PrvConverterUtils;

@Service
public class CumuliApertoService extends BaseService implements ICumuliApertoService{
	
	@Autowired
    private ICumuliApertoRepository cumuliApertoRepository;
	
	@Override
	public Page<CumuliApertoFullDTO> findAll(Predicate predicate, Pageable pageable) {
		log.debug("START - predicate={}, pageable={}", predicate, pageable);
        Page<CumuliAperto> cumuliApertoPage = cumuliApertoRepository.findAll(predicate, pageable);
        log.trace("cumuliApertoPage={}", cumuliApertoPage);
        Page<CumuliApertoFullDTO> respPage = cumuliApertoPage.map(currCumuliAperto -> {
        	CumuliApertoFullDTO dto = new CumuliApertoFullDTO();
        	dto.setnCumuliAperto(0);
            if (PrvConverterUtils.copyProperties(dto, currCumuliAperto)) {
            	dto.setnCumuliAperto(currCumuliAperto.getListaCumuliApertoLocalizzazione().size());
            	List<CumuliApertoLocalizzazione> detailsList = null;
            	
            	if (CollectionUtils.isNotEmpty(currCumuliAperto.getListaCumuliApertoLocalizzazione())) {
					detailsList = currCumuliAperto.getListaCumuliApertoLocalizzazione().stream().limit(10).collect(Collectors.toList());
					dto.setnCumuliAperto(currCumuliAperto.getListaCumuliApertoLocalizzazione().size());
				} 
            	if (CollectionUtils.isNotEmpty(detailsList)) {
            		dto.setListaCumuliApertoLocalizzazione(PrvConverterUtils.initCumuliApertoLocalizzazioneDTO(detailsList));
            	}	
                log.trace("copyProperties SUCCESS, dto={}", dto);
                return dto;
            }
            log.warn("copyProperties FAILED, currCumuliAperto={}", currCumuliAperto);
            return null;
        });
        log.trace("END - page={}", respPage);
        return respPage;
	}

	@Override
	public void update(@Valid CumuliApertoUpdateDTO cumuliApertoUpdateDTO) throws BaseEx {
		log.debug("START - update {}", cumuliApertoUpdateDTO);
		Integer id = cumuliApertoUpdateDTO.getId();
    
        Optional<CumuliAperto> cumuliApertoOptional = cumuliApertoRepository.findById(id);
		if (!cumuliApertoOptional.isPresent()) {
			log.warn("CumuloAperto with id={} not found", id);
			throw new PojoNotFound("CumuloAperto with id " + id + " not found");
		}

        CumuliAperto cumuliAperto = cumuliApertoOptional.get();
        log.trace("cumuliAperto={}", cumuliAperto);
        
		try {
			if (PrvConverterUtils.copyPropertiesNotNull(cumuliAperto, cumuliApertoUpdateDTO)) {
				log.info("Model - prodottoDettagli={}", cumuliAperto);
				cumuliAperto = cumuliApertoRepository.save(cumuliAperto);
				log.info("END - saved {}", cumuliAperto);
				return;
			}
		} catch (Exception e) {
			log.error("ERROR saving {}, message={}", cumuliAperto, e.getMessage(), e);
			throw new InternalError("Object not saved", cumuliApertoUpdateDTO);
		}
		log.warn("END - copyProperties FAILED, cumuliApertoUpdateDTO={}", cumuliApertoUpdateDTO);
		
	}

}
