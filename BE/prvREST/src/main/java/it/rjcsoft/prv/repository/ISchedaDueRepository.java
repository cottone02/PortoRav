package it.rjcsoft.prv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import it.rjcsoft.prv.model.SchedaDueDotazioniAzienda;

@Repository
public interface ISchedaDueRepository
		extends JpaRepository<SchedaDueDotazioniAzienda, Long>, JpaSpecificationExecutor<SchedaDueDotazioniAzienda> {

}
