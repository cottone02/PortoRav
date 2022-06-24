package it.rjcsoft.prv.repository;

import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;

import it.rjcsoft.prv.model.DettaglioSitoTrasporti;
import it.rjcsoft.prv.model.QDettaglioSitoTrasporti;

public interface IDettaglioSitoTrasportiRepository extends JpaRepository<DettaglioSitoTrasporti, Integer>,
        QuerydslPredicateExecutor<DettaglioSitoTrasporti>, QuerydslBinderCustomizer<QDettaglioSitoTrasporti> {

    @Override
    default void customize(QuerydslBindings bindings, QDettaglioSitoTrasporti dettaglioSitoTrasporti) {

        bindings.bind(String.class)
                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);

    }

}
