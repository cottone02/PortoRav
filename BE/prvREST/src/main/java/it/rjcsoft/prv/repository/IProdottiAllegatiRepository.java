package it.rjcsoft.prv.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.rjcsoft.prv.model.ProdottiAllegati;

@Repository
public interface IProdottiAllegatiRepository extends JpaRepository<ProdottiAllegati, Integer> {

    public Optional<List<ProdottiAllegati>> findBySchedaId(Integer schedaId);

}
