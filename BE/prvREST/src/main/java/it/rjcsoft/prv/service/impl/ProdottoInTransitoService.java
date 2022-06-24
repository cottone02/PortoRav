package it.rjcsoft.prv.service.impl;

import java.util.Optional;

import com.querydsl.core.types.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.rjcsoft.prv.dto.prodottointransito.ProdottoInTransitoFullDTO;
import it.rjcsoft.prv.dto.prodottointransito.ProdottoInTransitoUpdateDTO;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.exceptions.InternalError;
import it.rjcsoft.prv.exceptions.PojoNotFound;
import it.rjcsoft.prv.model.ProdottoInTransito;
import it.rjcsoft.prv.repository.IProdottoInTransitoRepository;
import it.rjcsoft.prv.service.BaseService;
import it.rjcsoft.prv.service.IProdottoInTransitoService;
import it.rjcsoft.prv.utils.PrvConverterUtils;

@Service
public class ProdottoInTransitoService extends BaseService implements IProdottoInTransitoService {

    @Autowired
    private IProdottoInTransitoRepository prodottoInTransitoRepository;

    @Override
    public Page<ProdottoInTransitoFullDTO> findAll(Predicate predicate, Pageable pageable) {
        log.debug("START - predicate={}, pageable={}", predicate, pageable);
        Page<ProdottoInTransito> prodottoInTransitoPage = prodottoInTransitoRepository.findAll(predicate, pageable);
        log.trace("prodottoInTransitoPage={}", prodottoInTransitoPage);
        Page<ProdottoInTransitoFullDTO> respPage = prodottoInTransitoPage.map(currProdottoInTransito -> {
            ProdottoInTransitoFullDTO dto = new ProdottoInTransitoFullDTO();
            if (PrvConverterUtils.copyProperties(dto, currProdottoInTransito)) {
                log.trace("copyProperties SUCCESS, dto={}", dto);
                return dto;
            }
            log.warn("copyProperties FAILED, currProdottoInTransito={}", currProdottoInTransito);
            return null;
        });
        log.trace("END - page={}", respPage);
        return respPage;
    }

    @Override
    public void update(ProdottoInTransitoUpdateDTO prodottoInTransitoUpdateDTO) throws BaseEx {
        log.debug("START - update {}", prodottoInTransitoUpdateDTO);
        Integer id = prodottoInTransitoUpdateDTO.getId();

        Optional<ProdottoInTransito> prodottoInTransitoOptional = prodottoInTransitoRepository.findById(id);
        if (!prodottoInTransitoOptional.isPresent()) {
            log.warn("ProdottoInTransito with id={} not found", id);
            throw new PojoNotFound("ProdottoInTransito with id " + id + " not found");
        }

        ProdottoInTransito prodottoInTransito = prodottoInTransitoOptional.get();
        log.trace("prodottoInTransito={}", prodottoInTransito);

        try {
            if (PrvConverterUtils.copyProperties(prodottoInTransito, prodottoInTransitoUpdateDTO)) {
                log.info("Model - prodottoInTransito={}", prodottoInTransito);
                if (prodottoInTransito.getContoTerzi() != null && !prodottoInTransito.getContoTerzi()) {
                    prodottoInTransito.setBanchina(null);
                    prodottoInTransito.setAziendaTerza(null);
                }
                prodottoInTransito = prodottoInTransitoRepository.save(prodottoInTransito);
                log.info("END - saved {}", prodottoInTransito);
                return;

            }
        } catch (Exception e) {
            log.error("ERROR saving {}, message={}", prodottoInTransito, e.getMessage(), e);
            throw new InternalError("Object not saved", prodottoInTransitoUpdateDTO);
        }
        log.warn("END - copyProperties FAILED, prodottoInTransitoUpdateDTO={}", prodottoInTransitoUpdateDTO);
    }
}
