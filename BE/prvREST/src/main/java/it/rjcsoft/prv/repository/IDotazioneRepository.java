package it.rjcsoft.prv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.rjcsoft.prv.model.Dotazione;

@Repository
public interface IDotazioneRepository extends JpaRepository<Dotazione, Integer> {

}
