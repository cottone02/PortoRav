package it.rjcsoft.prv.service;

import java.util.List;

import javax.validation.Valid;

import com.querydsl.core.types.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import it.rjcsoft.prv.dto.dettagliositodepositi.DettaglioSitoDepositiFullDTO;
import it.rjcsoft.prv.dto.dettagliositodepositi.DettaglioSitoDepositiSaveDTO;
import it.rjcsoft.prv.dto.dettagliositodepositi.DettaglioSitoDepositiUpdateDTO;
import it.rjcsoft.prv.dto.dettagliositodepositi.TipoDepositoStoccaggioDTO;
import it.rjcsoft.prv.exceptions.BaseEx;

public interface IDettaglioSitoDepositiService {

    public Page<DettaglioSitoDepositiFullDTO> findAll(Predicate predicate, Pageable pageable);

    public DettaglioSitoDepositiFullDTO save(@Valid DettaglioSitoDepositiSaveDTO newDettaglio) throws BaseEx;

    public void deleteById(int id) throws BaseEx;

    public void update(DettaglioSitoDepositiUpdateDTO dettaglioUpdateDTO) throws BaseEx;
    
 public List<TipoDepositoStoccaggioDTO> findAllDepositoInStoccaggio () throws Exception;
    
}
