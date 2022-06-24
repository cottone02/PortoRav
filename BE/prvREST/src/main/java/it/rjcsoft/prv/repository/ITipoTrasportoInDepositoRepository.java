package it.rjcsoft.prv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.rjcsoft.prv.model.TipoTrasportoInDeposito;

public interface ITipoTrasportoInDepositoRepository extends JpaRepository<TipoTrasportoInDeposito, Integer> {
    
    @Query(value = "select * from tipo_trasporto_in_deposito td where id not in (select distinct(dst.trasporto_in_deposito_id) from dettagli_sito_trasporti dst where prodotto_dettaglio_id = :prodottoDettaglioId)", nativeQuery = true)
    List<TipoTrasportoInDeposito> findAvailableTrasportiInDeposito(@Param("prodottoDettaglioId") Integer prodottoDettaglioId);
}
