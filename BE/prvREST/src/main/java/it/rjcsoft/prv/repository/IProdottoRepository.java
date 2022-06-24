package it.rjcsoft.prv.repository;

import java.util.Set;

import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.stereotype.Repository;

import it.rjcsoft.prv.model.Prodotto;
import it.rjcsoft.prv.model.QProdotto;

@Repository
public interface IProdottoRepository extends JpaRepository<Prodotto, Integer>, QuerydslPredicateExecutor<Prodotto>,
		QuerydslBinderCustomizer<QProdotto> {
	@Override
	default void customize(QuerydslBindings bindings, QProdotto prodotto) {

		bindings.bind(String.class)
				.first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
	}

	@Query(value="select distinct(prodotto_id) from censimenti_prodotti cp ", nativeQuery = true)
	Set<Integer> isProductDeletable();

	boolean existsById(Integer id);

}
