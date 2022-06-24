package it.rjcsoft.prv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.rjcsoft.prv.model.ProtezioneContenimentoEmissioni;

@Repository
public interface IProtezioneRepository extends JpaRepository<ProtezioneContenimentoEmissioni, Integer> {

}
