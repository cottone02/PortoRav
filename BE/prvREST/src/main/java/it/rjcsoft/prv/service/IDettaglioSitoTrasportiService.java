package it.rjcsoft.prv.service;

import java.util.List;

import javax.validation.Valid;

import com.querydsl.core.types.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import it.rjcsoft.prv.dto.dettagliositotrasporti.DettaglioSitoTrasportiFullDTO;
import it.rjcsoft.prv.dto.dettagliositotrasporti.DettaglioSitoTrasportiSaveDTO;
import it.rjcsoft.prv.dto.dettagliositotrasporti.DettaglioSitoTrasportiUpdateDTO;
import it.rjcsoft.prv.dto.dettagliositotrasporti.TipoTrasportoInDepositoDTO;
import it.rjcsoft.prv.exceptions.BaseEx;

public interface IDettaglioSitoTrasportiService {

    public Page<DettaglioSitoTrasportiFullDTO> findAll(Predicate predicate, Pageable pageable);

    public DettaglioSitoTrasportiFullDTO save(@Valid DettaglioSitoTrasportiSaveDTO newDettaglio) throws BaseEx;

    public void deleteById(int id) throws BaseEx;

    public void update(DettaglioSitoTrasportiUpdateDTO dettaglioUpdateDTO) throws BaseEx;

    public List<TipoTrasportoInDepositoDTO> findAvailableTrasportiInDeposito(Integer prodottoDettaglioId);

}
