package it.rjcsoft.prv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.rjcsoft.prv.model.ProdottoDettagli;

@Repository
public interface IProdottoDettagliRepository extends JpaRepository<ProdottoDettagli, Integer> {
    
}
