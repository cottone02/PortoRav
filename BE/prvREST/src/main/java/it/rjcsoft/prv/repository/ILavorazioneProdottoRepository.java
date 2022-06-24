package it.rjcsoft.prv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;

import it.rjcsoft.prv.model.LavorazioneProdotto;
import it.rjcsoft.prv.model.QLavorazioneProdotto;

@Repository
public interface ILavorazioneProdottoRepository extends JpaRepository<LavorazioneProdotto, Integer>,
        QuerydslPredicateExecutor<LavorazioneProdotto>, QuerydslBinderCustomizer<QLavorazioneProdotto> {

    @Override
    default void customize(QuerydslBindings bindings, QLavorazioneProdotto lavorazioneProdotto) {

        bindings.bind(String.class)
                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
    }

}
