package it.rjcsoft.prv.service.impl;

import com.querydsl.core.types.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.rjcsoft.prv.dto.cumuliaperto.CumuliApertoLocalizzazioneDTO;
import it.rjcsoft.prv.dto.cumuliaperto.CumuliApertoLocalizzazioneSaveDTO;
import it.rjcsoft.prv.dto.cumuliaperto.CumuliApertoLocalizzazioneUpdateDTO;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.exceptions.InternalError;
import it.rjcsoft.prv.exceptions.PojoNotFound;
import it.rjcsoft.prv.model.CumuliApertoLocalizzazione;
import it.rjcsoft.prv.repository.ICumuliApertoLocalizzazioneRepository;
import it.rjcsoft.prv.service.BaseService;
import it.rjcsoft.prv.service.ICumuliApertoLocalizzazioneService;
import it.rjcsoft.prv.utils.PrvConverterUtils;

@Service
public class CumuliApertoLocalizzazioneService extends BaseService implements ICumuliApertoLocalizzazioneService {

    @Autowired
    private ICumuliApertoLocalizzazioneRepository cumuliApertoLocalizzazioneRepository;

    @Override
    public Page<CumuliApertoLocalizzazioneDTO> findAll(Predicate predicate, Pageable pageable) {
        log.debug("START - predicate={}, pageable={}", predicate, pageable);
        Page<CumuliApertoLocalizzazione> cumuliApertoLocPage = cumuliApertoLocalizzazioneRepository.findAll(predicate,
                pageable);
        log.trace("cumuliApertoLocPage={}", cumuliApertoLocPage);
        Page<CumuliApertoLocalizzazioneDTO> respPage = cumuliApertoLocPage.map(currLocalizzazione -> {
            CumuliApertoLocalizzazioneDTO dto = new CumuliApertoLocalizzazioneDTO();
            if (PrvConverterUtils.copyProperties(dto, currLocalizzazione)) {
                
                dto.setProdottoDettaglioId(currLocalizzazione.getProdottoDettaglioId());
                log.trace("copyProperties SUCCESS, dto={}", dto);
                return dto;
            }
            log.warn("copyProperties FAILED, currLocalizzazione={}", currLocalizzazione);
            return null;
        });
        log.trace("END - page={}", respPage);
        return respPage;
    }

    @Override
    public CumuliApertoLocalizzazioneDTO save(CumuliApertoLocalizzazioneSaveDTO savedCumuliDTO) throws BaseEx {
        log.debug("START - save {}", savedCumuliDTO);

		CumuliApertoLocalizzazione cumuli = null;
		try {
			cumuli = new CumuliApertoLocalizzazione();
			if (PrvConverterUtils.copyProperties(cumuli, savedCumuliDTO)) {
                 
				log.info("Model - cumuli={}", cumuli);
				cumuli = cumuliApertoLocalizzazioneRepository.save(cumuli);
				log.info("END - saved {}", cumuli);
				return PrvConverterUtils.initCumuliApertoLocalizzazioneDTO(cumuli);

			}
		} catch (Exception e) {
			log.error("ERROR saving {}, message={}", cumuli, e.getMessage(), e);
			throw new InternalError("Object not saved", savedCumuliDTO);
		}
		log.warn("END - copyProperties FAILED, savedCumuliDTO={}", savedCumuliDTO);
		return null;
    }
    
    @Override
	 public void update(CumuliApertoLocalizzazioneUpdateDTO updatedCumuliDTO) throws BaseEx {
		 log.debug("START - update Cumulo id={}", updatedCumuliDTO.getId()); 
		 CumuliApertoLocalizzazione cumuloAperto =cumuliApertoLocalizzazioneRepository.findById(updatedCumuliDTO.getId())
        .orElseThrow(() -> new PojoNotFound("depositoStocaggio with id " + updatedCumuliDTO.getId() + " not found"));
		  
		 try {
			 if (PrvConverterUtils.copyPropertiesNotNull(cumuloAperto, updatedCumuliDTO)) {
			 
				 log.info("Model - depositoStoccaggio={}", cumuloAperto);
				 cumuliApertoLocalizzazioneRepository.save(cumuloAperto);
			 }
			 	 
		 } catch (Exception e) {
	         log.error("END - not updated {}, message={}", cumuloAperto.getId(), e.getMessage(), e);
	         throw new InternalError("cumuloAperto id=" + cumuloAperto.getId() + " non updated");
	     }
	 }

    @Override
    public void deleteById(int id) throws BaseEx {
        log.debug("START - delete censimentoAzienda id={}", id);

		cumuliApertoLocalizzazioneRepository.findById(id)
				.orElseThrow(() -> new PojoNotFound("CumuliApertoLocalizzazione with id " + id + " not found"));
		try {
			cumuliApertoLocalizzazioneRepository.deleteById(id);
			log.info("END - deleted {}", id);
		} catch (Exception e) {
			log.error("END - not deleted {}, message={}", id, e.getMessage(), e);
			throw new InternalError("cumulo id=" + id + " non deleted");
		}
    }

}
