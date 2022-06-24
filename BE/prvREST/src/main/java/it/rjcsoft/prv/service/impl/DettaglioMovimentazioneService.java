package it.rjcsoft.prv.service.impl;

import com.querydsl.core.types.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.rjcsoft.prv.dto.movimentazione.DettaglioMovimentazioneDTO;
import it.rjcsoft.prv.dto.movimentazione.DettaglioMovimentazioneSaveDTO;
import it.rjcsoft.prv.dto.movimentazione.DettaglioMovimentazioneUpdateDTO;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.exceptions.InternalError;
import it.rjcsoft.prv.exceptions.PojoNotFound;
import it.rjcsoft.prv.model.DettaglioMovimentazione;
import it.rjcsoft.prv.repository.IDettaglioMovimentazioneRepository;
import it.rjcsoft.prv.service.BaseService;
import it.rjcsoft.prv.service.IDettaglioMovimentazioneService;
import it.rjcsoft.prv.utils.PrvConverterUtils;

@Service
public class DettaglioMovimentazioneService extends BaseService implements IDettaglioMovimentazioneService {

    @Autowired
    private IDettaglioMovimentazioneRepository dettaglioMovimentazioneRepository;

    @Override
    public Page<DettaglioMovimentazioneDTO> findAll(Predicate predicate, Pageable pageable) {
        log.debug("START - predicate={}, pageable={}", predicate, pageable);
        Page<DettaglioMovimentazione> dettaglioMovimentazionePage = dettaglioMovimentazioneRepository.findAll(predicate,
                pageable);
        log.trace("dettaglioMovimentazionePage={}", dettaglioMovimentazionePage);
        Page<DettaglioMovimentazioneDTO> respPage = dettaglioMovimentazionePage.map(currMovimentazione -> {
            DettaglioMovimentazioneDTO dto = new DettaglioMovimentazioneDTO();
            if (PrvConverterUtils.copyProperties(dto, currMovimentazione)) {
                
                dto.setProdottoDettaglioId(currMovimentazione.getProdottoDettaglioId());
                log.trace("copyProperties SUCCESS, dto={}", dto);
                return dto;
            }
            log.warn("copyProperties FAILED, currLocalizzazione={}", currMovimentazione);
            return null;
        });
        log.trace("END - page={}", respPage);
        return respPage;
    }

    @Override
    public DettaglioMovimentazioneDTO save(DettaglioMovimentazioneSaveDTO savedMovimentazioneDTO) throws BaseEx {
        log.debug("START - save {}", savedMovimentazioneDTO);

		DettaglioMovimentazione dettaglio = null;
		try {
			dettaglio = new DettaglioMovimentazione();
			if (PrvConverterUtils.copyProperties(dettaglio, savedMovimentazioneDTO)) {
                 
				log.info("Model - dettaglioMovimentazione={}", dettaglio);
				dettaglio = dettaglioMovimentazioneRepository.save(dettaglio);
				log.info("END - saved {}", dettaglio);
				return PrvConverterUtils.initDettagliMovimentazioneDTO(dettaglio);

			}
		} catch (Exception e) {
			log.error("ERROR saving {}, message={}", dettaglio, e.getMessage(), e);
			throw new InternalError("Object not saved", savedMovimentazioneDTO);
		}
		log.warn("END - copyProperties FAILED, savedMovimentazioneDTO={}", savedMovimentazioneDTO);
		return null;
    }
    
    @Override
	 public void update(DettaglioMovimentazioneUpdateDTO updateMovimentazioneDTO) throws BaseEx {
		 log.debug("START - update dettaglioMovimentazione id={}", updateMovimentazioneDTO.getId()); 
		 DettaglioMovimentazione dettaglio =dettaglioMovimentazioneRepository.findById(updateMovimentazioneDTO.getId())
        .orElseThrow(() -> new PojoNotFound("dettaglioMovimentazione with id " + updateMovimentazioneDTO.getId() + " not found"));
		  
		 try {
			 if (PrvConverterUtils.copyPropertiesNotNull(dettaglio, updateMovimentazioneDTO)) {
			 
				 log.info("Model - dettaglioMovimentazione={}", dettaglio);
				 dettaglioMovimentazioneRepository.save(dettaglio);
			 }
			 	 
		 } catch (Exception e) {
	         log.error("END - not updated {}, message={}", dettaglio.getId(), e.getMessage(), e);
	         throw new InternalError("dettaglioMovimentazione id=" + dettaglio.getId() + " non updated");
	     }
	 }

    @Override
    public void deleteById(int id) throws BaseEx {
        log.debug("START - delete dettaglioMovimentazione id={}", id);

		dettaglioMovimentazioneRepository.findById(id)
				.orElseThrow(() -> new PojoNotFound("DettaglioMovimentazione with id " + id + " not found"));
		try {
			dettaglioMovimentazioneRepository.deleteById(id);
			log.info("END - deleted {}", id);
		} catch (Exception e) {
			log.error("END - not deleted {}, message={}", id, e.getMessage(), e);
			throw new InternalError("dettaglioMovimentazione id=" + id + " non deleted");
		}
    }

}
