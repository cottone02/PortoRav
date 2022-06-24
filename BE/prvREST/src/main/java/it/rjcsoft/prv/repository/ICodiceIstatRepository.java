package it.rjcsoft.prv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.rjcsoft.prv.model.CodiceIstatAttivita;

@Repository
public interface ICodiceIstatRepository extends JpaRepository<CodiceIstatAttivita, Integer> {

}
