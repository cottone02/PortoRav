package it.rjcsoft.prv.repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;

import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;

import it.rjcsoft.prv.model.CensimentoInterventiMitigazione;
import it.rjcsoft.prv.model.QCensimentoInterventiMitigazione;

public interface ICensimentoInterventiRepository extends JpaRepository<CensimentoInterventiMitigazione, Integer>,
		QuerydslPredicateExecutor<CensimentoInterventiMitigazione>,
		QuerydslBinderCustomizer<QCensimentoInterventiMitigazione> {

	@Override
	default void customize(QuerydslBindings bindings,
			QCensimentoInterventiMitigazione censimentoInterventiMitigazione) {

		bindings.bind(String.class)
				.first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
		
		bindings.bind(censimentoInterventiMitigazione.dataAttivazione).all((path, value) -> {
			List<? extends Timestamp> timestamps = new ArrayList<>(value);
			if (timestamps.size() == 1) {
				return Optional.of(path.goe(timestamps.get(0)));
			} else {
				Timestamp from = timestamps.get(0);
				Timestamp to = timestamps.get(1);
				return Optional.of(path.between(from, to));
			}
		});
			
	}

	public boolean existsByIdAndCensimentoAziendaId(int id, int censimentoAziendaId);

	public Optional<CensimentoInterventiMitigazione> findByIdAndCensimentoAziendaId(int id, int censimentoAziendaId);
}
