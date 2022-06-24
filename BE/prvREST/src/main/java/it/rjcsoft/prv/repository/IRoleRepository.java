package it.rjcsoft.prv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import it.rjcsoft.prv.model.Role;

@Repository
public interface IRoleRepository
		extends JpaRepository<Role, Integer>, QuerydslPredicateExecutor<Role> {

}
