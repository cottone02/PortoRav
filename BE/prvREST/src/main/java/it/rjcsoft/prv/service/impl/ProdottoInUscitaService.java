package it.rjcsoft.prv.service.impl;

import java.util.Optional;

import com.querydsl.core.types.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.rjcsoft.prv.dto.prodottoinuscita.ProdottoInUscitaDTO;
import it.rjcsoft.prv.dto.prodottoinuscita.ProdottoInUscitaUpdateDTO;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.exceptions.InternalError;
import it.rjcsoft.prv.exceptions.PojoNotFound;
import it.rjcsoft.prv.model.ProdottoInUscita;
import it.rjcsoft.prv.repository.IProdottoInUscitaRepository;
import it.rjcsoft.prv.service.BaseService;
import it.rjcsoft.prv.service.IProdottoInUscitaService;
import it.rjcsoft.prv.utils.PrvConverterUtils;

@Service
public class ProdottoInUscitaService extends BaseService implements IProdottoInUscitaService {

    @Autowired
    private IProdottoInUscitaRepository prodottoInUscitaRepository;

    @Override
    public Page<ProdottoInUscitaDTO> findAll(Predicate predicate, Pageable pageable) {
        log.debug("START - predicate={}, pageable={}", predicate, pageable);
        Page<ProdottoInUscita> prodottiInUscitaPage = prodottoInUscitaRepository.findAll(predicate, pageable);
        log.trace("prodottiInUscitaPage={}", prodottiInUscitaPage);
        Page<ProdottoInUscitaDTO> respPage = prodottiInUscitaPage.map(currProdottoInUscita -> {
            ProdottoInUscitaDTO dto = new ProdottoInUscitaDTO();
            if (PrvConverterUtils.copyProperties(dto, currProdottoInUscita)) {
                log.trace("copyProperties SUCCESS, dto={}", dto);
                return dto;
            }
            log.warn("copyProperties FAILED, currProdottoInUscita={}", currProdottoInUscita);
            return null;
        });
        log.trace("END - page={}", respPage);
        return respPage;
    }

    @Override
    public void update(ProdottoInUscitaUpdateDTO prodottoInUscitaUpdateDTO) throws BaseEx {
        log.debug("START - update {}", prodottoInUscitaUpdateDTO);
        Integer id = prodottoInUscitaUpdateDTO.getId();

        Optional<ProdottoInUscita> prodottoInUscitaOptional = prodottoInUscitaRepository.findById(id);
        if (!prodottoInUscitaOptional.isPresent()) {
            log.warn("ProdottoInUscita with id={} not found", id);
            throw new PojoNotFound("ProdottoInUscita with id " + id + " not found");
        }

        ProdottoInUscita prodottoInUscita = prodottoInUscitaOptional.get();
        log.trace("prodottoInUscita={}", prodottoInUscita);

        try {
            if (PrvConverterUtils.copyProperties(prodottoInUscita, prodottoInUscitaUpdateDTO)) {
                log.info("Model - prodottoInUscita={}", prodottoInUscita);
                
                prodottoInUscita = prodottoInUscitaRepository.save(prodottoInUscita);
                log.info("END - saved {}", prodottoInUscita);
                return;

            }
        } catch (Exception e) {
            log.error("ERROR saving {}, message={}", prodottoInUscita, e.getMessage(), e);
            throw new InternalError("Object not saved", prodottoInUscitaUpdateDTO);
        }
        log.warn("END - copyProperties FAILED, prodottoInUscitaUpdateDTO={}", prodottoInUscitaUpdateDTO);
    }
}
