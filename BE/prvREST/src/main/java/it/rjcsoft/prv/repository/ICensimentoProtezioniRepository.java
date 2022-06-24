package it.rjcsoft.prv.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;

import it.rjcsoft.prv.model.CensimentoProtezioniContenimento;
import it.rjcsoft.prv.model.QCensimentoProtezioniContenimento;

@Repository
public interface ICensimentoProtezioniRepository extends JpaRepository<CensimentoProtezioniContenimento, Integer>,
		QuerydslPredicateExecutor<CensimentoProtezioniContenimento>,
		QuerydslBinderCustomizer<QCensimentoProtezioniContenimento> {

	@Override
	default void customize(QuerydslBindings bindings,
			QCensimentoProtezioniContenimento censimentoProtezioniContenimento) {

		bindings.bind(String.class)
				.first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);

	}

	public boolean existsByIdAndCensimentoAziendaId(int id, int censimentoAziendaId);

	public Optional<CensimentoProtezioniContenimento> findByIdAndCensimentoAziendaId(int id, int censimentoAziendaId);
}
