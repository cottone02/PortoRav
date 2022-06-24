package it.rjcsoft.prv.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import it.rjcsoft.prv.dto.dettagliositotrasporti.DettaglioSitoTrasportiFullDTO;
import it.rjcsoft.prv.dto.dettagliositotrasporti.DettaglioSitoTrasportiSaveDTO;
import it.rjcsoft.prv.dto.dettagliositotrasporti.DettaglioSitoTrasportiUpdateDTO;
import it.rjcsoft.prv.dto.dettagliositotrasporti.TipoTrasportoInDepositoDTO;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.exceptions.InternalError;
import it.rjcsoft.prv.exceptions.PojoNotFound;
import it.rjcsoft.prv.model.DettaglioSitoTrasporti;
import it.rjcsoft.prv.model.TipoTrasportoInDeposito;
import it.rjcsoft.prv.repository.IDettaglioSitoTrasportiRepository;
import it.rjcsoft.prv.repository.ITipoTrasportoInDepositoRepository;
import it.rjcsoft.prv.service.BaseService;
import it.rjcsoft.prv.service.IDettaglioSitoTrasportiService;
import it.rjcsoft.prv.utils.PrvConverterUtils;

@Service
public class DettaglioSitoTrasportiService extends BaseService implements IDettaglioSitoTrasportiService {

    @Autowired
    private IDettaglioSitoTrasportiRepository dettaglioSitoTrasportiRepository;

    @Autowired
    private ITipoTrasportoInDepositoRepository tipoTrasportoInDepositoRepository;

    @Override
    public Page<DettaglioSitoTrasportiFullDTO> findAll(Predicate predicate, Pageable pageable) {
        log.debug("START - predicate={}, pageable={}", predicate, pageable);
        Page<DettaglioSitoTrasporti> prodottiPage = dettaglioSitoTrasportiRepository.findAll(predicate, pageable);
        log.trace("prodottiPage={}", prodottiPage);
        Page<DettaglioSitoTrasportiFullDTO> respPage = prodottiPage.map(currDettaglio -> {
            DettaglioSitoTrasportiFullDTO dto = new DettaglioSitoTrasportiFullDTO();
            if (PrvConverterUtils.copyPropertiesIgnoreField(dto, currDettaglio, "tipoTrasportoInDeposito")) {
                TipoTrasportoInDepositoDTO tipoTrasportoInDepositoDTO = PrvConverterUtils
                        .initTipoTrasportoInDepositoDTO(currDettaglio.getTrasportoInDeposito());
                dto.setTrasportoInDeposito(tipoTrasportoInDepositoDTO);
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
    public DettaglioSitoTrasportiFullDTO save(@Valid DettaglioSitoTrasportiSaveDTO newDettaglio) throws BaseEx {

        log.debug("START - save {}", newDettaglio);

        DettaglioSitoTrasporti dettaglio = null;
        try {
            dettaglio = new DettaglioSitoTrasporti();
            if (PrvConverterUtils.copyProperties(dettaglio, newDettaglio)) {
                log.info("Model - dettaglio={}", dettaglio);
                TipoTrasportoInDeposito tipoTrasportoInDeposito = new TipoTrasportoInDeposito();
                tipoTrasportoInDeposito.setId(newDettaglio.getTrasportoInDepositoId());
                dettaglio.setTrasportoInDeposito(tipoTrasportoInDeposito);
                dettaglio = dettaglioSitoTrasportiRepository.save(dettaglio);
                log.info("END - saved {}", dettaglio);
                DettaglioSitoTrasportiFullDTO dto = new DettaglioSitoTrasportiFullDTO();
                if (PrvConverterUtils.copyPropertiesIgnoreField(dto, dettaglio, "tipoTrasportoInDeposito")) {
                    TipoTrasportoInDepositoDTO tipoTrasportoInDepositoDTO = PrvConverterUtils
                            .initTipoTrasportoInDepositoDTO(dettaglio.getTrasportoInDeposito());
                    dto.setTrasportoInDeposito(tipoTrasportoInDepositoDTO);
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
        log.debug("START - delete dettaglioSitoTrasporti id={}", id);

		dettaglioSitoTrasportiRepository.findById(id)
				.orElseThrow(() -> new PojoNotFound("dettaglioSitoTrasporti with id " + id + " not found"));
		try {
			dettaglioSitoTrasportiRepository.deleteById(id);
			log.info("END - deleted {}", id);
		} catch (Exception e) {
			log.error("END - not deleted {}, message={}", id, e.getMessage(), e);
			throw new InternalError("dettaglioSitoTrasporti id=" + id + " non deleted");
		}
        
    }

    @Override
    public void update(DettaglioSitoTrasportiUpdateDTO dettaglioUpdateDTO) throws BaseEx {
        log.debug("START - update {}", dettaglioUpdateDTO);
		Integer id = dettaglioUpdateDTO.getId();

		DettaglioSitoTrasporti dettaglio = dettaglioSitoTrasportiRepository.findById(id)
				.orElseThrow(() -> new PojoNotFound("DettaglioSitoTrasporti with id " + id + " not found"));

		try {
			if (PrvConverterUtils.copyPropertiesIgnoreField(dettaglio, dettaglioUpdateDTO, "trasportoInDeposito")) {
				log.info("Model - dettaglio={}", dettaglio);
                if (dettaglioUpdateDTO.getTrasportoInDepositoId() != null) {
                    TipoTrasportoInDeposito tipoTrasportoInDeposito = new TipoTrasportoInDeposito();
                    tipoTrasportoInDeposito.setId(dettaglioUpdateDTO.getTrasportoInDepositoId());
                    dettaglio.setTrasportoInDeposito(tipoTrasportoInDeposito);
                }
				dettaglio = dettaglioSitoTrasportiRepository.save(dettaglio);
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
    public List<TipoTrasportoInDepositoDTO> findAvailableTrasportiInDeposito(Integer prodottoDettaglioId) {
        log.debug("START - getAvailable tipotrasportoindeposito");
        List<TipoTrasportoInDeposito> trasporti = tipoTrasportoInDepositoRepository.findAvailableTrasportiInDeposito(prodottoDettaglioId);
        if (CollectionUtils.isEmpty(trasporti)) return Collections.emptyList();
        List<TipoTrasportoInDepositoDTO> dtoList = new ArrayList<>();
        trasporti.forEach(trasporto -> dtoList.add(PrvConverterUtils.initTipoTrasportoInDepositoDTO(trasporto)));
        return dtoList;
    }

}
