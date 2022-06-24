package it.rjcsoft.prv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.rjcsoft.prv.model.Stili;

@Repository
public interface IStiliRepository extends JpaRepository<Stili, Integer> {

	public List<Stili> findByIdGeotiff(Integer idGeotiff);

}
