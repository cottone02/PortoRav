package it.rjcsoft.prv.repository;

 import org.springframework.data.jpa.repository.JpaRepository;
 import org.springframework.data.querydsl.QuerydslPredicateExecutor;
 import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
 import org.springframework.data.querydsl.binding.QuerydslBindings;
 import org.springframework.data.querydsl.binding.SingleValueBinding;
 import org.springframework.stereotype.Repository;

 import com.querydsl.core.types.dsl.StringExpression;
 import com.querydsl.core.types.dsl.StringPath;

 import it.rjcsoft.prv.model.MonitoraggioAria;
 import it.rjcsoft.prv.model.QMonitoraggioAria;

 @Repository
 public interface IMonitoraggioAriaRepository extends JpaRepository<MonitoraggioAria, Integer>,
 		QuerydslPredicateExecutor<MonitoraggioAria>, QuerydslBinderCustomizer<QMonitoraggioAria> {

 	@Override
 	default void customize(QuerydslBindings bindings, QMonitoraggioAria monitoraggioAria) {

 		bindings.bind(String.class)
 				.first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);

 	}

 }
