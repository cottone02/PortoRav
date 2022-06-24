package it.rjcsoft.prv.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.querydsl.core.types.Predicate;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.rjcsoft.prv.dto.censimentointerventi.CensimentoInterventIMitigazioneDTO;
import it.rjcsoft.prv.dto.censimentointerventi.CensimentoInterventiDTO;
import it.rjcsoft.prv.dto.censimentointerventi.CensimentoInterventiFullDTO;
import it.rjcsoft.prv.dto.censimentointerventi.CensimentoInterventiUpdateDTO;
import it.rjcsoft.prv.dto.censimentointerventi.InterventoDTO;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.exceptions.InternalError;
import it.rjcsoft.prv.exceptions.PojoNotFound;
import it.rjcsoft.prv.model.CensimentoInterventiMitigazione;
import it.rjcsoft.prv.model.InterventoMitigazionePolveri;
import it.rjcsoft.prv.repository.ICensimentoInterventiRepository;
import it.rjcsoft.prv.repository.IInterventoRepository;
import it.rjcsoft.prv.service.BaseService;
import it.rjcsoft.prv.service.ICensimentoInterventiService;
import it.rjcsoft.prv.service.IInterventoService;
import it.rjcsoft.prv.utils.PrvConverterUtils;

@Service
public class CensimentoInterventiService extends BaseService implements ICensimentoInterventiService {

    @Autowired
    private ICensimentoInterventiRepository censimentoInterventiRepository;

    @Autowired
    private IInterventoService interventoService;

    @Autowired
    private IInterventoRepository interventoRepository;

    @Override
    public Page<CensimentoInterventiFullDTO> findAll(Predicate predicate, Pageable pageable) {
        log.debug("START - predicate={}, pageable={}", predicate, pageable);
        Page<CensimentoInterventiMitigazione> censimentiInterventiPage = censimentoInterventiRepository
                .findAll(predicate, pageable);
        log.trace("censimentiInterventiPage={}", censimentiInterventiPage);
        Page<CensimentoInterventiFullDTO> respPage = censimentiInterventiPage.map(currCensimentoInterventi -> {
            CensimentoInterventiFullDTO dto = new CensimentoInterventiFullDTO();
            if (PrvConverterUtils.copyProperties(dto, currCensimentoInterventi)) {
                InterventoMitigazionePolveri intervento = currCensimentoInterventi.getIntervento();
                dto.setInterventoDTO(PrvConverterUtils.initInterventoDTO(intervento));
                log.trace("copyProperties SUCCESS, dto={}", dto);
                return dto;
            }
            log.warn("copyProperties FAILED, currCensimentoInterventi={}", currCensimentoInterventi);
            return null;
        });
        log.trace("END - page={}", respPage);
        return respPage;
    }

    @Override
    @Transactional(rollbackFor = {InternalError.class})
    public CensimentoInterventiDTO save(@Valid CensimentoInterventIMitigazioneDTO newCensimentoInterventIMitigazioneDTO,
            int censimentoAziendaId) throws InternalError {
        log.debug("START - save {}", newCensimentoInterventIMitigazioneDTO);

        CensimentoInterventiMitigazione censimentoInterventiMitigazione = null;

        try {
            InterventoDTO interventoDTO = newCensimentoInterventIMitigazioneDTO.getIntervento();
            if (interventoDTO.getId() == null) {
                log.info("interventoId IS NULL, insert new Intervento");
                interventoDTO = interventoService.save(interventoDTO);
            }
            censimentoInterventiMitigazione = new CensimentoInterventiMitigazione();
            // if (PrvConverterUtils.copyProperties(censimentoInterventiMitigazione,
            // newCensimentoInterventIMitigazioneDTO)) {
            BeanUtils.copyProperties(newCensimentoInterventIMitigazioneDTO, censimentoInterventiMitigazione,
                    "intervento");
            censimentoInterventiMitigazione.setCensimentoAziendaId(censimentoAziendaId);
            censimentoInterventiMitigazione.setIntervento(PrvConverterUtils.initIntervento(interventoDTO));
            log.trace("Model - censimentoInterventiMitigazione={}", censimentoInterventiMitigazione);
            censimentoInterventiMitigazione = censimentoInterventiRepository.save(censimentoInterventiMitigazione);
            log.info("END - saved {}", censimentoInterventiMitigazione);
            return PrvConverterUtils.initCensimentoInterventiDTO(censimentoInterventiMitigazione);

            // }
        } catch (Exception e) {
            log.error("ERROR saving {}, message={}", censimentoInterventiMitigazione, e.getMessage(), e);
            throw new InternalError("Object not saved", newCensimentoInterventIMitigazioneDTO);
        }
        // log.warn("END - copyProperties FAILED,
        // newCensimentoInterventIMitigazioneDTO={}",
        // newCensimentoInterventIMitigazioneDTO);
        // return null;
    }

    @Override
    public void deleteById(int id) throws BaseEx {
        log.debug("START - delete censimentoDotazioniId={}", id);

        censimentoInterventiRepository.findById(id)
                .orElseThrow(() -> new PojoNotFound("CensimentoInterventi with id " + id + " not found"));
        try {
            censimentoInterventiRepository.deleteById(id);
            log.info("END - deleted {}", id);
        } catch (Exception e) {
            log.error("END - not deleted {}, message={}", id, e.getMessage(), e);
            throw new InternalError("CensimentoInterventi id=" + id + " non deleted");
        }
    }

    @Override
    @Transactional(rollbackFor = {BaseEx.class})
    public void update(CensimentoInterventiUpdateDTO updateDTO) throws BaseEx {
        log.debug("START - updateDTO={}", updateDTO);
        int censimentoAziendaId = updateDTO.getCensimentoAziendaId();
        int id = updateDTO.getId();
        Optional<CensimentoInterventiMitigazione> censimentoInterventiOptional = censimentoInterventiRepository
                .findByIdAndCensimentoAziendaId(id, censimentoAziendaId);
        if (censimentoInterventiOptional.isEmpty()) {
            log.warn("censimentoInterventi with id={}, censimentoAziendaId={} not found", id, censimentoAziendaId);
            throw new PojoNotFound("censimentoInterventi with id " + id + " not found");
        }

        CensimentoInterventiMitigazione censimentoInterventi = censimentoInterventiOptional.get();

        try {
            PrvConverterUtils.copyPropertiesNotNull(censimentoInterventi, updateDTO);
            log.info("Model - censimentoInterventi={}", censimentoInterventi);
            censimentoInterventiRepository.save(censimentoInterventi);
            log.info("END - saved {}", censimentoInterventi);

        } catch (Exception e) {
            log.error("ERROR saving {}, message={}", censimentoInterventi, e.getMessage(), e);
            throw new InternalError(
                    String.format("censimentoInterventi not saved id=%d, censimentoAziendaId=%d, updateDTO=%s", id,
                            censimentoAziendaId, updateDTO.toString()));
        }

    }

    @Override
    public List<InterventoDTO> findAllInterventi() {
        log.debug("START - getAll interventi");
        List<InterventoMitigazionePolveri> interventi = interventoRepository.findAll();
        List<InterventoDTO> dtoList = new ArrayList<>();
        interventi.forEach(intervento -> dtoList.add(PrvConverterUtils.initInterventoDTO(intervento)));
        return dtoList;
    }

}
