package it.rjcsoft.prv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.rjcsoft.prv.model.InterventoMitigazionePolveri;

@Repository
public interface IInterventoRepository extends JpaRepository<InterventoMitigazionePolveri, Integer> {

}
