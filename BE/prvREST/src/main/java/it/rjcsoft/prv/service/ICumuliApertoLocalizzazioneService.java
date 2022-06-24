package it.rjcsoft.prv.service;

import com.querydsl.core.types.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import it.rjcsoft.prv.dto.cumuliaperto.CumuliApertoLocalizzazioneDTO;
import it.rjcsoft.prv.dto.cumuliaperto.CumuliApertoLocalizzazioneSaveDTO;
import it.rjcsoft.prv.dto.cumuliaperto.CumuliApertoLocalizzazioneUpdateDTO;
import it.rjcsoft.prv.exceptions.BaseEx;

public interface ICumuliApertoLocalizzazioneService {

    public Page<CumuliApertoLocalizzazioneDTO> findAll(Predicate predicate, Pageable pageable);

    public CumuliApertoLocalizzazioneDTO save(CumuliApertoLocalizzazioneSaveDTO savedCumuli) throws BaseEx;

    public void deleteById(int id) throws BaseEx;

	public void update(CumuliApertoLocalizzazioneUpdateDTO updatedCumuliDTO) throws BaseEx;
    
}
