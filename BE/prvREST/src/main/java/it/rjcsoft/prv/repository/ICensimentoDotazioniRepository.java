package it.rjcsoft.prv.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;

import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;

import it.rjcsoft.prv.model.CensimentoDotazioniAzienda;
import it.rjcsoft.prv.model.QCensimentoDotazioniAzienda;

public interface ICensimentoDotazioniRepository extends JpaRepository<CensimentoDotazioniAzienda, Integer>,
		QuerydslPredicateExecutor<CensimentoDotazioniAzienda>, QuerydslBinderCustomizer<QCensimentoDotazioniAzienda> {
	
	@Override
	default void customize(QuerydslBindings bindings, QCensimentoDotazioniAzienda censimentoDotazioniAzienda) {
		
		bindings.bind(String.class)
				.first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
		
	}

	public boolean existsByIdAndCensimentoAziendaId(int id, int censimentoAziendaId);
	
	public Optional<CensimentoDotazioniAzienda> findByIdAndCensimentoAziendaId(int id, int censimentoAziendaId);
}
