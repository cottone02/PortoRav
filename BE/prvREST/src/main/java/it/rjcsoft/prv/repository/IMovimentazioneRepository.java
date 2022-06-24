package it.rjcsoft.prv.repository;

import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.stereotype.Repository;

import it.rjcsoft.prv.model.Movimentazione;
import it.rjcsoft.prv.model.QMovimentazione;

@Repository
public interface IMovimentazioneRepository extends JpaRepository<Movimentazione, Integer>,
		QuerydslPredicateExecutor<Movimentazione>, QuerydslBinderCustomizer<QMovimentazione> {

	@Override
	default void customize(QuerydslBindings bindings, QMovimentazione movimentazione) {

		bindings.bind(String.class)
				.first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
	}

}
