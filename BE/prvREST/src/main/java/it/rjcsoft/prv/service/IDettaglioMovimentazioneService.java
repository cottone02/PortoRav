package it.rjcsoft.prv.service;

import com.querydsl.core.types.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import it.rjcsoft.prv.dto.movimentazione.DettaglioMovimentazioneDTO;
import it.rjcsoft.prv.dto.movimentazione.DettaglioMovimentazioneSaveDTO;
import it.rjcsoft.prv.dto.movimentazione.DettaglioMovimentazioneUpdateDTO;
import it.rjcsoft.prv.exceptions.BaseEx;

public interface IDettaglioMovimentazioneService {

    public Page<DettaglioMovimentazioneDTO> findAll(Predicate predicate, Pageable pageable);

    public DettaglioMovimentazioneDTO save(DettaglioMovimentazioneSaveDTO dettaglioMovimentazione) throws BaseEx;

    public void deleteById(int id) throws BaseEx;

    public void update(DettaglioMovimentazioneUpdateDTO updateMovimentazioneDTO) throws BaseEx;

}
