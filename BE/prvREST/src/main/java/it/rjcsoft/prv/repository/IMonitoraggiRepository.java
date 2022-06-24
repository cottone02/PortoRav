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
import it.rjcsoft.prv.model.Monitoraggi;
import it.rjcsoft.prv.model.QMonitoraggi;
import it.rjcsoft.prv.utils.PrvGeometryUtils;

@Repository
public interface IMonitoraggiRepository extends JpaRepository<Monitoraggi, Integer>,
        QuerydslPredicateExecutor<Monitoraggi>, QuerydslBinderCustomizer<QMonitoraggi> {

    @Override
    default void customize(QuerydslBindings bindings, QMonitoraggi monitoraggio) {
        bindings.bind(String.class)
                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);

        bindings.bind(monitoraggio.geometry).all((path, value) -> {
            List<Object> polygons = new ArrayList<>(value);
            BooleanExpression expression = PrvGeometryUtils.buildGeometryCondiction(PrvSpatialOps.ST_INTERSECTS, path,
                    polygons.get(0).toString());
            return Optional.of(expression);
        });

        bindings.bind(monitoraggio.date).all((path, value) -> {
            List<? extends Timestamp> timestamps = new ArrayList<>(value);
            if (timestamps.size() == 1) {
                return Optional.of(path.goe(timestamps.get(0)));
            } else {
                Timestamp from = timestamps.get(0);
                Timestamp to = timestamps.get(1);
                return Optional.of(path.between(from, to));

            }
        });
    }

    public Optional<List<Monitoraggi>> findByCompanyId(int id);

}
