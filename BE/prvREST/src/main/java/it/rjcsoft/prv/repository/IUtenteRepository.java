package it.rjcsoft.prv.repository;

import java.util.List;
import java.util.Optional;

import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.rjcsoft.prv.model.QUtente;
import it.rjcsoft.prv.model.Utente;

@Repository
public interface IUtenteRepository
		extends JpaRepository<Utente, Integer>, QuerydslPredicateExecutor<Utente>, QuerydslBinderCustomizer<QUtente> {
	@Override
	default void customize(QuerydslBindings bindings, QUtente utente) {
		bindings.bind(String.class)
				.first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);

	}
     public List<Utente> findByCompanyId(int id);
     
     public List<Utente> findByCompanyIdAndRoleValue(int id, String roleValue);
     
     public Optional<Utente> findByEmail(String email);
     
     public Optional<Utente> findByUsername(String username);

	 @Query(value="SELECT DISTINCT(u2.email) FROM users u1 JOIN users u2 ON u1.company_id = u2.company_id WHERE u1.email IN (:mail) AND u2.contact_person = true", nativeQuery=true)
	 public List<String> findContactPersonMailByMail(@Param("mail") String[] mail);
}
