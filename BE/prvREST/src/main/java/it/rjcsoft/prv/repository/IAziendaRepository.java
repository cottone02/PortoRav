package it.rjcsoft.prv.repository;

import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import it.rjcsoft.prv.model.Azienda;
import it.rjcsoft.prv.model.QAzienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.stereotype.Repository;

@Repository
public interface IAziendaRepository extends JpaRepository<Azienda, Integer>,
		QuerydslPredicateExecutor<Azienda>, QuerydslBinderCustomizer<QAzienda>{
    @Override
	default void customize(QuerydslBindings bindings, QAzienda azienda) {

		bindings.bind(String.class)
				.first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
        }
}
