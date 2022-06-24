package it.rjcsoft.prv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;

import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;

import it.rjcsoft.prv.model.QTipoLavorazione;
import it.rjcsoft.prv.model.TipoLavorazione;

public interface ITipoLavorazioneRepository extends JpaRepository<TipoLavorazione, Integer>,
	QuerydslPredicateExecutor<TipoLavorazione>, QuerydslBinderCustomizer<QTipoLavorazione>{
	
	 @Override
	    default void customize(QuerydslBindings bindings, QTipoLavorazione tipoLavorazione) {

	        bindings.bind(String.class)
	                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
	    }
	
	

}
