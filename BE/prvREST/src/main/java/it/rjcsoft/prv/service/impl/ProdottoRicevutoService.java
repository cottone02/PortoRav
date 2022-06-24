package it.rjcsoft.prv.service.impl;

import java.util.Optional;

import com.querydsl.core.types.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.rjcsoft.prv.dto.prodottoricevuto.ProdottoRicevutoDTO;
import it.rjcsoft.prv.dto.prodottoricevuto.ProdottoRicevutoUpdateDTO;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.exceptions.InternalError;
import it.rjcsoft.prv.exceptions.PojoNotFound;
import it.rjcsoft.prv.model.ProdottoRicevuto;
import it.rjcsoft.prv.repository.IProdottoRicevutoRepository;
import it.rjcsoft.prv.service.BaseService;
import it.rjcsoft.prv.service.IProdottoRicevutoService;
import it.rjcsoft.prv.utils.PrvConverterUtils;

@Service
public class ProdottoRicevutoService extends BaseService implements IProdottoRicevutoService {

    @Autowired
    private IProdottoRicevutoRepository prodottoRicevutoRepository;

    @Override
    public Page<ProdottoRicevutoDTO> findAll(Predicate predicate, Pageable pageable) {
        log.debug("START - predicate={}, pageable={}", predicate, pageable);
        Page<ProdottoRicevuto> prodottiRicevutiPage = prodottoRicevutoRepository.findAll(predicate, pageable);
        log.trace("prodottiRicevutiPage={}", prodottiRicevutiPage);
        Page<ProdottoRicevutoDTO> respPage = prodottiRicevutiPage.map(currProdottoRicevuto -> {
            ProdottoRicevutoDTO dto = new ProdottoRicevutoDTO();
            if (PrvConverterUtils.copyProperties(dto, currProdottoRicevuto)) {
                log.trace("copyProperties SUCCESS, dto={}", dto);
                return dto;
            }
            log.warn("copyProperties FAILED, currProdottoRicevuto={}", currProdottoRicevuto);
            return null;
        });
        log.trace("END - page={}", respPage);
        return respPage;
    }

    @Override
	public void update(ProdottoRicevutoUpdateDTO prodottoRicevutoUpdateDTO) throws BaseEx {
		log.debug("START - update {}", prodottoRicevutoUpdateDTO);
		Integer id = prodottoRicevutoUpdateDTO.getId();
    
        Optional<ProdottoRicevuto> prodottoRicevutoOptional = prodottoRicevutoRepository.findById(id);
		if (!prodottoRicevutoOptional.isPresent()) {
			log.warn("ProdottoRicevuto with id={} not found", id);
			throw new PojoNotFound("ProdottoRicevuto with id " + id + " not found");
		}

        ProdottoRicevuto prodottoRicevuto = prodottoRicevutoOptional.get();
        log.trace("prodottoRicevuto={}", prodottoRicevuto);
        
		try {
			if (PrvConverterUtils.copyProperties(prodottoRicevuto, prodottoRicevutoUpdateDTO)) {
				log.info("Model - prodottoDettagli={}", prodottoRicevuto);
                
				prodottoRicevuto = prodottoRicevutoRepository.save(prodottoRicevuto);
				log.info("END - saved {}", prodottoRicevuto);
				return;
			}
		} catch (Exception e) {
			log.error("ERROR saving {}, message={}", prodottoRicevuto, e.getMessage(), e);
			throw new InternalError("Object not saved", prodottoRicevutoUpdateDTO);
		}
		log.warn("END - copyProperties FAILED, prodottoRicevutoUpdateDTO={}", prodottoRicevutoUpdateDTO);
	}
}
