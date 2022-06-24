package it.rjcsoft.prv.repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.stereotype.Repository;

import it.rjcsoft.prv.enums.PrvSpatialOps;
import it.rjcsoft.prv.model.CensimentoAzienda;
import it.rjcsoft.prv.model.QCensimentoAzienda;
import it.rjcsoft.prv.utils.PrvGeometryUtils;

@Repository
public interface ICensimentoAziendaRepository extends JpaRepository<CensimentoAzienda, Integer>,
		QuerydslPredicateExecutor<CensimentoAzienda>, QuerydslBinderCustomizer<QCensimentoAzienda> {

	@Override
	default void customize(QuerydslBindings bindings, QCensimentoAzienda censimentoAzienda) {

		bindings.bind(String.class)
				.first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);

		bindings.bind(censimentoAzienda.indirizzoImpiantoGeo).all((path, value) -> {
			List<Object> polygons = new ArrayList<>(value);
			BooleanExpression expression = PrvGeometryUtils.buildGeometryCondiction(PrvSpatialOps.ST_INTERSECTS, path,
					polygons.get(0).toString());
			return Optional.of(expression);
		});

		bindings.bind(censimentoAzienda.created).all((path, value) -> {
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

}
