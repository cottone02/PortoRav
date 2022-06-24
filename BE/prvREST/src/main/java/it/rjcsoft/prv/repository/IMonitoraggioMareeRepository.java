package it.rjcsoft.prv.repository;

 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;

import it.rjcsoft.prv.model.MonitoraggioMaree;
import it.rjcsoft.prv.model.QMonitoraggioMaree;

 @Repository
 public interface IMonitoraggioMareeRepository extends JpaRepository<MonitoraggioMaree, Integer>,
 		QuerydslPredicateExecutor<MonitoraggioMaree>, QuerydslBinderCustomizer<QMonitoraggioMaree> {

 	@Override
 	default void customize(QuerydslBindings bindings, QMonitoraggioMaree monitoraggioMaree) {

 		bindings.bind(String.class)
 				.first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);

 	}

 }
