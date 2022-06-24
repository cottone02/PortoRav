package it.rjcsoft.prv.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import it.rjcsoft.prv.dto.SchedaUnoInfoAziendaDto;
import it.rjcsoft.prv.exceptions.PojoNotFound;
import it.rjcsoft.prv.model.SchedaUnoInfoAzienda;
import it.rjcsoft.prv.repository.ISchedaUnoRepository;
import it.rjcsoft.prv.service.BaseService;
import it.rjcsoft.prv.service.ISchedaUnoService;
import it.rjcsoft.prv.utils.PrvConverterUtils;

@Service
public class SchedaUnoService extends BaseService implements ISchedaUnoService {

	@Autowired
	private ISchedaUnoRepository schedaAziendaRepository;

	@Override
	public Optional<SchedaUnoInfoAziendaDto> findByNumScheda(Integer numScheda) throws PojoNotFound {
		Optional<SchedaUnoInfoAzienda> schedaOpt = schedaAziendaRepository.findByNumScheda(numScheda);
		if (!schedaOpt.isPresent()) {
			throw new PojoNotFound("SchedaUno with numScheda " + numScheda + " not found");
		}
		SchedaUnoInfoAziendaDto dto = new SchedaUnoInfoAziendaDto();
		if (!PrvConverterUtils.copyProperties(dto, schedaOpt.get())) {
			throw new PojoNotFound("SchedaUno with numScheda " + numScheda + " cause error during converting");
		}
		return Optional.of(dto);
	}

	@Override
	public Page<SchedaUnoInfoAziendaDto> findAll(Predicate predicate, Pageable pageable) {
		Page<SchedaUnoInfoAzienda> schedePage = schedaAziendaRepository.findAll(predicate, pageable);
		return schedePage.map(currScheda -> {
			SchedaUnoInfoAziendaDto dto = new SchedaUnoInfoAziendaDto();
			if (PrvConverterUtils.copyProperties(dto, currScheda)) {
				return dto;
			}
			return null;
		});
	}

	@Override
	@Transactional
	public boolean deleteByIdAndNumScheda(Integer id, Integer numScheda) throws PojoNotFound {
		schedaAziendaRepository.findById(id)
				.orElseThrow(() -> new PojoNotFound("SchedaUno with id " + id + " not found"));
		return schedaAziendaRepository.deleteByIdAndNumScheda(id, numScheda) == 1;
	}

}
