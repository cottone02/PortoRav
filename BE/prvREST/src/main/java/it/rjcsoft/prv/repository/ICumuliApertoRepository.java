package it.rjcsoft.prv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;

import it.rjcsoft.prv.model.CumuliAperto;
import it.rjcsoft.prv.model.QCumuliAperto;

@Repository
public interface ICumuliApertoRepository extends JpaRepository<CumuliAperto, Integer>,
	QuerydslPredicateExecutor<CumuliAperto>, QuerydslBinderCustomizer<QCumuliAperto> {
	
	 @Override
	    default void customize(QuerydslBindings bindings, QCumuliAperto cumuliAperto) {

	        bindings.bind(String.class)
	                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
	    }

}
