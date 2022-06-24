package it.rjcsoft.prv.repository;

import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.stereotype.Repository;

import it.rjcsoft.prv.model.DettaglioMovimentazione;
import it.rjcsoft.prv.model.QDettaglioMovimentazione;

@Repository
public interface IDettaglioMovimentazioneRepository extends JpaRepository<DettaglioMovimentazione, Integer>,
        QuerydslPredicateExecutor<DettaglioMovimentazione>, QuerydslBinderCustomizer<QDettaglioMovimentazione> {

    @Override
    default void customize(QuerydslBindings bindings, QDettaglioMovimentazione dettaglioMovimentazione) {

        bindings.bind(String.class)
                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);

    }

}
