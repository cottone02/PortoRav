package it.rjcsoft.prv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;

import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;

import it.rjcsoft.prv.model.QTipoDepositoStoccaggio;
import it.rjcsoft.prv.model.TipoDepositoStoccaggio;
import java.util.Set;
import org.springframework.data.jpa.repository.Query;

public interface ITipoDepositoStoccaggioRepository extends JpaRepository<TipoDepositoStoccaggio, Integer>,
        QuerydslPredicateExecutor<TipoDepositoStoccaggio>, QuerydslBinderCustomizer<QTipoDepositoStoccaggio> {

    @Override
    default void customize(QuerydslBindings bindings, QTipoDepositoStoccaggio tipoDepositoStoccaggio) {

        bindings.bind(String.class)
                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);

    }

    @Query(value = "select distinct(deposito_stoccaggio_id) from dettagli_sito_depositi", nativeQuery = true)
    Set<Integer> isDepositoDeletable();

}
