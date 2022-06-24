package it.rjcsoft.prv.repository;

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

import it.rjcsoft.prv.enums.PrvSpatialOps;
import it.rjcsoft.prv.model.CensimentoProdotti;
import it.rjcsoft.prv.model.QCensimentoProdotti;
import it.rjcsoft.prv.utils.PrvGeometryUtils;

public interface ICensimentoProdottiRepository extends JpaRepository<CensimentoProdotti, Integer>,
		QuerydslPredicateExecutor<CensimentoProdotti>, QuerydslBinderCustomizer<QCensimentoProdotti> {

	@Override
	default void customize(QuerydslBindings bindings, QCensimentoProdotti censimentoProdotti) {

		bindings.bind(String.class)
				.first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);

		bindings.bind(censimentoProdotti.mappaStabilimento).all((path, value) -> {
			List<Object> polygons = new ArrayList<>(value);
			BooleanExpression expression = PrvGeometryUtils.buildGeometryCondiction(PrvSpatialOps.ST_INTERSECTS, path,
					polygons.get(0).toString());
			return Optional.of(expression);
		});

	}

	public boolean existsBySchedaIdAndCensimentoAziendaId(int schedaId, int censimentoAziendaId);

	public Optional<CensimentoProdotti> findBySchedaIdAndCensimentoAziendaId(int schedaId, int censimentoAziendaId);

}
