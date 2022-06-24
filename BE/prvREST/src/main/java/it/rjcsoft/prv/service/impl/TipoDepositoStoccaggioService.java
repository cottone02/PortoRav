package it.rjcsoft.prv.service.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import it.rjcsoft.prv.dto.dettagliositodepositi.TipoDepositoStoccaggioDTO;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.exceptions.InternalError;
import it.rjcsoft.prv.exceptions.PojoNotFound;
import it.rjcsoft.prv.model.TipoDepositoStoccaggio;
import it.rjcsoft.prv.repository.ITipoDepositoStoccaggioRepository;
import it.rjcsoft.prv.service.BaseService;
import it.rjcsoft.prv.service.ITipoDepositoStoccaggioService;
import it.rjcsoft.prv.utils.PrvConverterUtils;
import java.util.Set;
import org.springframework.util.CollectionUtils;

@Service
public class TipoDepositoStoccaggioService extends BaseService implements ITipoDepositoStoccaggioService {

    @Autowired
    private ITipoDepositoStoccaggioRepository tipoDepositoStoccaggioRepository;

    @Override
    public TipoDepositoStoccaggioDTO save(@Valid TipoDepositoStoccaggioDTO newDeposito) throws BaseEx {
        log.debug("START - save {}", newDeposito);
        TipoDepositoStoccaggio deposito = null;
        try {
            deposito = new TipoDepositoStoccaggio();
            if (PrvConverterUtils.copyProperties(deposito, newDeposito)) {
                log.info("Model - depositoStoccaggio={}", deposito);
                deposito = tipoDepositoStoccaggioRepository.save(deposito);
                log.info("END - saved {}", deposito);
                TipoDepositoStoccaggioDTO dto = new TipoDepositoStoccaggioDTO();
                PrvConverterUtils.copyProperties(dto, deposito);
                return dto;

            }
        } catch (Exception e) {
            log.error("ERROR saving {}, message={}", deposito, e.getMessage(), e);
            throw new InternalError("Object not saved", newDeposito);
        }
        log.warn("END - copyProperties FAILED, newDettaglio={}", newDeposito);
        return null;
    }

    @Override
    public void deleteById(int id) throws BaseEx {
        log.debug("START - delete DepositoStocaggio id={}", id);

        tipoDepositoStoccaggioRepository.findById(id)
                .orElseThrow(() -> new PojoNotFound("depositoStocaggio with id " + id + " not found"));
        try {
            tipoDepositoStoccaggioRepository.deleteById(id);
            log.info("END - deleted {}", id);
        } catch (Exception e) {
            log.error("END - not deleted {}, message={}", id, e.getMessage(), e);
            throw new InternalError("depositoStocaggio id=" + id + " non deleted");
        }

    }

    @Override
    public void update(TipoDepositoStoccaggioDTO newDeposito) throws BaseEx {
        log.debug("START - update DepositoStocaggio id={}", newDeposito.getId());

        tipoDepositoStoccaggioRepository.findById(newDeposito.getId())
                .orElseThrow(() -> new PojoNotFound("depositoStocaggio with id " + newDeposito.getId() + " not found"));
        TipoDepositoStoccaggio deposito = null;
        try {
            deposito = new TipoDepositoStoccaggio();
            log.info("Model - depositoStoccaggio={}", deposito);
            PrvConverterUtils.copyProperties(deposito, newDeposito);
            tipoDepositoStoccaggioRepository.save(deposito);

        } catch (Exception e) {
            log.error("END - not updated {}, message={}", newDeposito.getId(), e.getMessage(), e);
            throw new InternalError("depositoStocaggio id=" + newDeposito.getId() + " non updated");
        }
    }

    @Override
    public Page<TipoDepositoStoccaggioDTO> findAll(Predicate predicate, Pageable pageable) {
        log.debug("START - predicate={}, pageable={}", predicate, pageable);
        Page<TipoDepositoStoccaggio> prodottiPage = tipoDepositoStoccaggioRepository.findAll(predicate, pageable);
        log.trace("prodottiPage={}", prodottiPage);
        Set<Integer> undeletableDeposito = tipoDepositoStoccaggioRepository.isDepositoDeletable();
        Page<TipoDepositoStoccaggioDTO> respPage = prodottiPage.map(currDettaglio -> {
            TipoDepositoStoccaggioDTO dto = new TipoDepositoStoccaggioDTO();
            PrvConverterUtils.copyProperties(dto, currDettaglio);
            if (dto != null) {
                if(!CollectionUtils.isEmpty(undeletableDeposito))
                dto.setIsDeletable(!undeletableDeposito.contains(currDettaglio.getId()));
                else dto.setIsDeletable(Boolean.TRUE);
                log.trace("copyProperties SUCCESS, dto={}", dto);
            }
            log.warn("copyProperties FAILED, currDettaglio={}", currDettaglio);
            return dto;
        });
        log.trace("END - page={}", respPage);
        return respPage;
    }
}
