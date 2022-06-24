package it.rjcsoft.prv.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.querydsl.core.types.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.rjcsoft.prv.config.PrvRestConfig;
import it.rjcsoft.prv.dto.censimentoprodotti.CensimentoProdottiDTO;
import it.rjcsoft.prv.dto.censimentoprodotti.CensimentoProdottiFullDTO;
import it.rjcsoft.prv.dto.censimentoprodotti.CensimentoProdottiUpdateDTO;
import it.rjcsoft.prv.dto.censimentoprodotti.ClasseGranulometricaDTO;
import it.rjcsoft.prv.dto.censimentoprodotti.ProdottoDTO;
import it.rjcsoft.prv.dto.censimentoprodottiallegati.ProdottiAllegatiDTO;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.exceptions.InternalError;
import it.rjcsoft.prv.exceptions.PojoNotFound;
import it.rjcsoft.prv.model.CensimentoProdotti;
import it.rjcsoft.prv.model.CensimentoProdottiAdditionalInfo;
import it.rjcsoft.prv.model.ClasseGranulometrica;
import it.rjcsoft.prv.model.Prodotto;
import it.rjcsoft.prv.repository.ICensimentoProdottiAdditionalInfoRepository;
import it.rjcsoft.prv.repository.ICensimentoProdottiRepository;
import it.rjcsoft.prv.repository.IClasseGranulomentricaRepository;
import it.rjcsoft.prv.service.BaseService;
import it.rjcsoft.prv.service.ICensimentoProdottiService;
import it.rjcsoft.prv.utils.PrvConverterUtils;
import it.rjcsoft.prv.utils.PrvFileUtils;

@Service
public class CensimentoProdottiService extends BaseService implements ICensimentoProdottiService {

	@Autowired
	private ICensimentoProdottiRepository censimentoProdottiRepository;

	@Autowired
	private PrvRestConfig prvRestConfig;

	@Autowired
	private IClasseGranulomentricaRepository classeGranulomentricaRepository;

	@Autowired
	private ICensimentoProdottiAdditionalInfoRepository censimentoProdottiAdditionalInfo;

	@Override
	public Page<CensimentoProdottiFullDTO> findAll(Predicate predicate, Pageable pageable) {
		log.debug("START - predicate={}, pageable={}", predicate, pageable);
		Page<CensimentoProdotti> censimentiProdottiPage = censimentoProdottiRepository.findAll(predicate, pageable);
		log.trace("censimentiProdottiPage={}", censimentiProdottiPage.getContent());
		Set<Integer> ids = censimentiProdottiPage.stream().map(CensimentoProdotti::getSchedaId)
				.collect(Collectors.toSet());
		List<CensimentoProdottiAdditionalInfo> details = censimentoProdottiAdditionalInfo
				.findAllByIds(StringUtils.join(ids, ","));
		Page<CensimentoProdottiFullDTO> respPage = censimentiProdottiPage.map(currCensimentoProdotti -> {
			CensimentoProdottiFullDTO dto = new CensimentoProdottiFullDTO();
			if (PrvConverterUtils.copyPropertiesIgnoreField(dto, currCensimentoProdotti, "prodotto",
					"prodottiAllegati")) {
				ProdottoDTO prodotto = PrvConverterUtils.initProdottoDTO(currCensimentoProdotti.getProdotto());
				ClasseGranulometricaDTO classeGranulometrica = PrvConverterUtils
						.initClasseGranulometricaDTO(currCensimentoProdotti.getClasseGranulometrica());
				List<ProdottiAllegatiDTO> prodottiAllegati = PrvConverterUtils
						.initProdottiAllegatiDTOList(currCensimentoProdotti.getProdottiAllegati());
				dto.setCanDelete(false);
				Optional<CensimentoProdottiAdditionalInfo> additionalInfo = details.stream()
						.filter(additional -> Objects.equals(additional.getSchedaId(), dto.getSchedaId())).findFirst();
				if (additionalInfo.isPresent()) {
					dto.setCanDelete(Boolean.TRUE.equals(additionalInfo.get().getCanDelete()));
				}
				dto.setProdotto(prodotto);
				dto.setGranulometria(classeGranulometrica);
				dto.setProdottiAllegati(prodottiAllegati);
				log.trace("copyProperties SUCCESS, dto={}", dto);
				return dto;
			}
			log.warn("copyProperties FAILED, currCensimentoProdotti={}", currCensimentoProdotti);
			return null;
		});
		log.trace("END - page={}", respPage);
		return respPage;
	}

	@Override
	public CensimentoProdottiDTO save(Integer prodottoId, int censimentoAziendaId) throws BaseEx {
		log.debug("START - censimentoProdotto prodottoId={}", prodottoId);

		CensimentoProdotti censimentoProdotti = null;

		try {
			Prodotto prodotto = new Prodotto();
			prodotto.setId(prodottoId);
			censimentoProdotti = new CensimentoProdotti();
			censimentoProdotti.setCensimentoAziendaId(censimentoAziendaId);
			censimentoProdotti.setProdotto(prodotto);
			log.trace("Model - censimentoProdotti={}", censimentoProdotti);
			censimentoProdotti = censimentoProdottiRepository.save(censimentoProdotti);
			log.info("END - saved={}", censimentoProdotti);
			return PrvConverterUtils.initCensimentoProdottiDTO(censimentoProdotti);
		} catch (Exception e) {
			log.error("ERROR saving {}, message={}", censimentoProdotti, e.getMessage(), e);
			throw new InternalError("Object not saved, prodottoId=" + prodottoId);
		}
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void deleteById(Integer id) throws BaseEx {
		log.debug("START - delete censimentoDotazioniId={}", id);

		CensimentoProdotti i = censimentoProdottiRepository.findById(id)
				.orElseThrow(() -> new PojoNotFound("CensimentoProdotti with id " + id + " not found"));
		try {
			i.getProdottiAllegati().forEach(p -> {
				File f = new File(prvRestConfig.getProdottiAllegatiBasePath() + "/" + id + "/" + p.getNomeFile());
				PrvFileUtils.deleteFile(f);
			});

			censimentoProdottiRepository.delete(i);

			log.info("END - deleted {}", id);
		} catch (Exception e) {
			log.error("END - not deleted {}, message={}", id, e.getMessage(), e);
			throw new InternalError("CensimentoProdotti id=" + id + " non deleted");
		}
	}

	@Override
	@Transactional(rollbackFor = { BaseEx.class })
	public void update(CensimentoProdottiUpdateDTO updateDTO) throws BaseEx {
		log.debug("START - updateDTO={}", updateDTO);
		int censimentoAziendaId = updateDTO.getCensimentoAziendaId();
		int id = updateDTO.getSchedaId();
		Optional<CensimentoProdotti> censimentoProdottiOptional = censimentoProdottiRepository
				.findBySchedaIdAndCensimentoAziendaId(id, censimentoAziendaId);
		if (censimentoProdottiOptional.isEmpty()) {
			log.warn("censimentoProdotti with id={}, censimentoAziendaId={} not found", id, censimentoAziendaId);
			throw new PojoNotFound("censimentoProdotti with id " + id + " not found");
		}

		CensimentoProdotti censimentoProdotti = censimentoProdottiOptional.get();

		try {
			PrvConverterUtils.copyPropertiesNotNull(censimentoProdotti, updateDTO);
			if (updateDTO.getClasseGranulometricaId() != null) {
				ClasseGranulometrica classeGranulometrica = new ClasseGranulometrica();
				classeGranulometrica.setId(updateDTO.getClasseGranulometricaId());
				censimentoProdotti.setClasseGranulometrica(classeGranulometrica);
			} else {
				censimentoProdotti.setClasseGranulometrica(null);
			}
			log.info("Model - censimentoProdotti={}", censimentoProdotti);
			censimentoProdottiRepository.save(censimentoProdotti);
			log.info("END - saved {}", censimentoProdotti);

		} catch (Exception e) {
			log.error("ERROR saving {}, message={}", censimentoProdotti, e.getMessage(), e);
			throw new InternalError(
					String.format("censimentoProdotti not saved id=%d, censimentoAziendaId=%d, updateDTO=%s", id,
							censimentoAziendaId, updateDTO.toString()));
		}

	}

	@Override
	public List<ClasseGranulometricaDTO> findAllClassiGranulometriche() {
		log.debug("START - getAll classiGranulometriche");
		List<ClasseGranulometrica> classiGranulometriche = classeGranulomentricaRepository.findAll();
		List<ClasseGranulometricaDTO> dtoList = new ArrayList<>();
		classiGranulometriche.forEach(classeGranulometrica -> dtoList
				.add(PrvConverterUtils.initClasseGranulometricaDTO(classeGranulometrica)));
		return dtoList;
	}

}
