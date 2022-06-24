package it.rjcsoft.prv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.rjcsoft.prv.model.ClasseGranulometrica;

@Repository
public interface IClasseGranulomentricaRepository extends JpaRepository<ClasseGranulometrica, Integer> {

}
