package it.rjcsoft.prv.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.querydsl.core.types.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import it.rjcsoft.prv.dto.censimentoprodotti.ProdottoDTO;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.exceptions.InternalError;
import it.rjcsoft.prv.exceptions.PojoNotFound;
import it.rjcsoft.prv.model.Polverosita;
import it.rjcsoft.prv.model.Prodotto;
import it.rjcsoft.prv.repository.IPolverositaRepository;
import it.rjcsoft.prv.repository.IProdottoRepository;
import it.rjcsoft.prv.service.BaseService;
import it.rjcsoft.prv.service.IProdottoService;
import it.rjcsoft.prv.utils.PrvConverterUtils;

@Service
public class ProdottoService extends BaseService implements IProdottoService {

    @Autowired
    private IProdottoRepository prodottoRepository;

    @Autowired
    private IPolverositaRepository polverositaRepository;

    @Override
    public Page<ProdottoDTO> findAll(Predicate predicate, Pageable pageable) {
        log.debug("START - predicate={}, pageable={}", predicate, pageable);
        Page<Prodotto> prodottiPage = prodottoRepository.findAll(predicate, pageable);
        log.trace("prodottiPage={}", prodottiPage);
        Set<Integer> undeletableProducts = prodottoRepository.isProductDeletable();
        Page<ProdottoDTO> respPage = prodottiPage.map(currProdotto -> {
            ProdottoDTO dto = PrvConverterUtils.initProdottoDTO(currProdotto);
            if (!CollectionUtils.isEmpty(undeletableProducts) && dto != null) {
                dto.setIsDeletable(!undeletableProducts.contains(currProdotto.getId()));
                return dto;
            } else {
                log.warn("copyProperties FAILED, currProdotto={}", currProdotto);
                return null;
            }
        });
        log.trace("END - page={}", respPage);
        return respPage;
    }

    @Override
    public ProdottoDTO save(ProdottoDTO newProdottoDTO) throws InternalError {
        log.debug("START - save {}", newProdottoDTO);
        newProdottoDTO.setNome(StringUtils.upperCase(newProdottoDTO.getNome()));
        Prodotto prodotto = null;
        try {
            prodotto = new Prodotto();
            if (PrvConverterUtils.copyPropertiesIgnoreField(prodotto, newProdottoDTO, "polverosita")) {
                log.info("Model - prodotto={}", prodotto);
                Polverosita polverosita = new Polverosita();
                polverosita.setTipo(StringUtils.upperCase(newProdottoDTO.getPolverosita()));
                prodotto.setPolverosita(polverosita);
                prodotto = prodottoRepository.save(prodotto);
                log.info("END - saved {}", prodotto);
                return PrvConverterUtils.initProdottoDTO(prodotto);
            }
        } catch (Exception e) {
            log.error("ERROR saving {}, message={}", prodotto, e.getMessage(), e);
            throw new InternalError("Object not saved", newProdottoDTO);
        }
        log.warn("END - copyProperties FAILED, newProdottoDTO={}", newProdottoDTO);
        return null;
    }

    @Override
    public void deleteById(int id) throws PojoNotFound, InternalError {
        log.debug("START - delete Prodotto id={}", id);

        prodottoRepository.findById(id).orElseThrow(() -> new PojoNotFound("Prodotto with id " + id + " not found"));
        try {
            prodottoRepository.deleteById(id);
            log.info("END - deleted {}", id);
        } catch (Exception e) {
            log.error("END - not deleted {}, message={}", id, e.getMessage(), e);
            throw new InternalError("Prodotto id=" + id + " non deleted");
        }
    }

    @Override
    public ProdottoDTO update(Integer id, String polverosita) throws BaseEx {
        log.debug("START - id={}, polverosita={}", id, polverosita);

        Prodotto prodotto = prodottoRepository.findById(id)
                .orElseThrow(() -> new PojoNotFound("Prodotto with id " + id + " not found"));

        try {
            Polverosita polverositaModel = new Polverosita();
            polverositaModel.setTipo(polverosita);
            prodotto.setPolverosita(polverositaModel);
            log.info("Model - prodotto={}", prodotto);
            prodotto = prodottoRepository.save(prodotto);
            log.info("END - saved={}", prodotto);
            return PrvConverterUtils.initProdottoDTO(prodotto);

        } catch (Exception e) {
            log.error("ERROR saving {}, message={}", prodotto, e.getMessage(), e);
            throw new InternalError("Object not saved with id=" + id + "and polverosita=" + polverosita);
        }

    }

    @Override
    public List<String> findAllPolverosita() {
        log.debug("START - findAllPolverosita");
        List<Polverosita> polverositas = polverositaRepository.findAll();
        List<String> polverositaList = new ArrayList<>();
        polverositas.forEach(polverosita -> polverositaList.add(polverosita.getTipo()));
        return polverositaList;
    }

}
