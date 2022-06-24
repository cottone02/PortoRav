package it.rjcsoft.prv.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import com.querydsl.core.types.Predicate;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.rjcsoft.prv.dto.dettagliositodepositi.DettaglioSitoDepositiFullDTO;
import it.rjcsoft.prv.dto.dettagliositodepositi.DettaglioSitoDepositiSaveDTO;
import it.rjcsoft.prv.dto.dettagliositodepositi.DettaglioSitoDepositiUpdateDTO;
import it.rjcsoft.prv.dto.dettagliositodepositi.TipoDepositoStoccaggioDTO;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.exceptions.InternalError;
import it.rjcsoft.prv.exceptions.PojoNotFound;
import it.rjcsoft.prv.model.DettaglioSitoDepositi;
import it.rjcsoft.prv.model.TipoDepositoStoccaggio;
import it.rjcsoft.prv.repository.IDettaglioSitoDepositiRepository;
import it.rjcsoft.prv.repository.ITipoDepositoStoccaggioRepository;
import it.rjcsoft.prv.service.BaseService;
import it.rjcsoft.prv.service.IDettaglioSitoDepositiService;
import it.rjcsoft.prv.utils.PrvConverterUtils;

@Service
public class DettaglioSitoDepositiService extends BaseService implements IDettaglioSitoDepositiService {

    @Autowired
    private IDettaglioSitoDepositiRepository dettaglioSitoDepositiRepository;
    
    @Autowired
    private ITipoDepositoStoccaggioRepository tipoDepositoStoccaggioRepository;

    @Override
    public Page<DettaglioSitoDepositiFullDTO> findAll(Predicate predicate, Pageable pageable) {
        log.debug("START - predicate={}, pageable={}", predicate, pageable);
        Page<DettaglioSitoDepositi> prodottiPage = dettaglioSitoDepositiRepository.findAll(predicate, pageable);
        log.trace("prodottiPage={}", prodottiPage);
        Page<DettaglioSitoDepositiFullDTO> respPage = prodottiPage.map(currDettaglio -> {
            DettaglioSitoDepositiFullDTO dto = new DettaglioSitoDepositiFullDTO();
            if (PrvConverterUtils.copyPropertiesIgnoreField(dto, currDettaglio, "tipoDepositoStoccaggio")) {
                TipoDepositoStoccaggioDTO tipoDepositoStoccaggio = PrvConverterUtils
                        .initTipoDepositoStoccaggioDTO(currDettaglio.getTipoDepositoStoccaggio());
                dto.setTipoDepositoStoccaggio(tipoDepositoStoccaggio);
                if (dto != null) {
                    log.trace("copyProperties SUCCESS, dto={}", dto);
                    return dto;
                }
            }
            log.warn("copyProperties FAILED, currDettaglio={}", currDettaglio);
            return null;
        });
        log.trace("END - page={}", respPage);
        return respPage;
    }

    @Override
    public DettaglioSitoDepositiFullDTO save(@Valid DettaglioSitoDepositiSaveDTO newDettaglio) throws BaseEx {
        log.debug("START - save {}", newDettaglio);

        DettaglioSitoDepositi dettaglio = null;
        try {
            dettaglio = new DettaglioSitoDepositi();
            if (PrvConverterUtils.copyProperties(dettaglio, newDettaglio)) {
                log.info("Model - dettaglio={}", dettaglio);
                TipoDepositoStoccaggio tipoDepositoStoccaggio = new TipoDepositoStoccaggio();
                tipoDepositoStoccaggio.setId(newDettaglio.getTipoDepositoStoccaggioId());
                dettaglio.setTipoDepositoStoccaggio(tipoDepositoStoccaggio);
                if(dettaglio.getCapacita()==null)dettaglio.setCapacita(0.0);
                dettaglio = dettaglioSitoDepositiRepository.save(dettaglio);
                log.info("END - saved {}", dettaglio);
                DettaglioSitoDepositiFullDTO dto = new DettaglioSitoDepositiFullDTO();
                if (PrvConverterUtils.copyPropertiesIgnoreField(dto, dettaglio, "tipoDepositoStoccaggio")) {
                    TipoDepositoStoccaggioDTO tipoDepositoStoccaggioDTO = PrvConverterUtils
                            .initTipoDepositoStoccaggioDTO(dettaglio.getTipoDepositoStoccaggio());
                    dto.setTipoDepositoStoccaggio(tipoDepositoStoccaggioDTO);
                }
                return dto;

            }
        } catch (Exception e) {
            log.error("ERROR saving {}, message={}", dettaglio, e.getMessage(), e);
            throw new InternalError("Object not saved", newDettaglio);
        }
        log.warn("END - copyProperties FAILED, newDettaglio={}", newDettaglio);
        return null;
    }

    @Override
    public void deleteById(int id) throws BaseEx {
        log.debug("START - delete dettaglioSitoDeposito id={}", id);

        dettaglioSitoDepositiRepository.findById(id)
                .orElseThrow(() -> new PojoNotFound("dettaglioSitoDeposito with id " + id + " not found"));
        try {
            dettaglioSitoDepositiRepository.deleteById(id);
            log.info("END - deleted {}", id);
        } catch (Exception e) {
            log.error("END - not deleted {}, message={}", id, e.getMessage(), e);
            throw new InternalError("dettaglioSitoDeposito id=" + id + " non deleted");
        }

    }

    @Override
    public void update(DettaglioSitoDepositiUpdateDTO dettaglioUpdateDTO) throws BaseEx {
        log.debug("START - update {}", dettaglioUpdateDTO);
        Integer id = dettaglioUpdateDTO.getId();

        DettaglioSitoDepositi dettaglio = dettaglioSitoDepositiRepository.findById(id)
                .orElseThrow(() -> new PojoNotFound("DettaglioSitoDepositi with id " + id + " not found"));

        try {
            if (PrvConverterUtils.copyPropertiesIgnoreField(dettaglio, dettaglioUpdateDTO, "tipoDepositoStoccaggio")) {
                log.info("Model - dettaglio={}", dettaglio);
                if (dettaglioUpdateDTO.getTipoDepositoStoccaggioId() != null) {
                    TipoDepositoStoccaggio tipoDepositoStoccaggio = new TipoDepositoStoccaggio();
                    tipoDepositoStoccaggio.setId(dettaglioUpdateDTO.getTipoDepositoStoccaggioId());
                    dettaglio.setTipoDepositoStoccaggio(tipoDepositoStoccaggio);
                }
                dettaglio = dettaglioSitoDepositiRepository.save(dettaglio);
                log.info("END - saved {}", dettaglio);
                return;
            }
            log.warn("END - copyProperties FAILED, dettaglioUpdateDTO={}", dettaglioUpdateDTO);
        } catch (Exception e) {
            log.error("ERROR saving {}, message={}", dettaglio, e.getMessage(), e);
            throw new InternalError("Object not saved", dettaglioUpdateDTO);
        }
    }
    

	@Override
    public List<TipoDepositoStoccaggioDTO> findAllDepositoInStoccaggio() {
        try {
			log.debug("START - findAll");
			List<TipoDepositoStoccaggio> trasporti = tipoDepositoStoccaggioRepository.findAll();
			if (CollectionUtils.isEmpty(trasporti)) return Collections.emptyList();
			List<TipoDepositoStoccaggioDTO> dtoList = new ArrayList<>();
			trasporti.forEach(trasporto -> dtoList.add(PrvConverterUtils.initTipoTrasportoInDepositoDTO(trasporto)));
			return dtoList;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
		
    }

}
