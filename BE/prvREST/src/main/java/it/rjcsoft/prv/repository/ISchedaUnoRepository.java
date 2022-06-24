package it.rjcsoft.prv.repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;

import it.rjcsoft.prv.model.QSchedaUnoInfoAzienda;
import it.rjcsoft.prv.model.SchedaUnoInfoAzienda;

@Repository
public interface ISchedaUnoRepository
		extends JpaRepository<SchedaUnoInfoAzienda, Integer>, QuerydslPredicateExecutor<SchedaUnoInfoAzienda>,
		QuerydslBinderCustomizer<QSchedaUnoInfoAzienda>, ISchedaUnoRepositoryCustom {

	@Override
	default void customize(QuerydslBindings bindings, QSchedaUnoInfoAzienda schedaUnoInfoAzienda) {

		bindings.bind(String.class)
				.first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);

		bindings.bind(schedaUnoInfoAzienda.numScheda).first(NumberExpression::goe);

		bindings.bind(schedaUnoInfoAzienda.created).all((path, value) -> {
			List<? extends Timestamp> timestamps = new ArrayList<>(value);
			if (timestamps.size() == 1) {
				return Optional.of(path.eq(timestamps.get(0)));
			} else {
				Timestamp from = timestamps.get(0);
				Timestamp to = timestamps.get(1);
				return Optional.of(path.between(from, to));
			}
		});

	}

	Optional<SchedaUnoInfoAzienda> findByNumScheda(Integer numScheda);

	Integer deleteByIdAndNumScheda(Integer id, Integer numScheda);

}
