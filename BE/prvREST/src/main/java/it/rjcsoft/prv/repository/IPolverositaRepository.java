package it.rjcsoft.prv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import it.rjcsoft.prv.model.Polverosita;

public interface IPolverositaRepository extends JpaRepository<Polverosita, String> {
    
}
