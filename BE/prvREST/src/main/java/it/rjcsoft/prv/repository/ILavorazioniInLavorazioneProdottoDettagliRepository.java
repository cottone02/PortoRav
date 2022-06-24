package it.rjcsoft.prv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.rjcsoft.prv.model.Lavorazione;

public interface ILavorazioniInLavorazioneProdottoDettagliRepository extends JpaRepository<Lavorazione, Integer> {
	@Query(value = "select * from lavorazioni lav where id not in (select lp.lavorazione_id from lavorazioni_prodotto lp where prodotto_dettaglio_id = :prodottoDettaglioId)", nativeQuery = true)
    List<Lavorazione> findAvailableLavorazioneInLavorazioneProdottoDettagli(@Param("prodottoDettaglioId") Integer prodottoDettaglioId);
}
