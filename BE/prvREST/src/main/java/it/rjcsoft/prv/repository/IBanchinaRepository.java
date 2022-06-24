package it.rjcsoft.prv.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.stereotype.Repository;

import it.rjcsoft.prv.enums.PrvSpatialOps;
import it.rjcsoft.prv.model.Banchina;
import it.rjcsoft.prv.model.QBanchina;
import it.rjcsoft.prv.utils.PrvGeometryUtils;

@Repository
public interface IBanchinaRepository extends JpaRepository<Banchina, Integer>,
        QuerydslPredicateExecutor<Banchina>,QuerydslBinderCustomizer<QBanchina>{
    
    @Override
    default void customize(QuerydslBindings bindings, QBanchina banchina){
        
        bindings.bind(String.class)
				.first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
        
        bindings.bind(banchina.banchinaGeo).all((path,value)->{
            List<Object> polygons=new ArrayList<>(value);
            BooleanExpression expression= PrvGeometryUtils.buildGeometryCondiction(PrvSpatialOps.ST_INTERSECTS, path, 
                    polygons.get(0).toString());
            return Optional.of(expression);     
        });
    }
    
    @Query(value = "select distinct(banchina) from prodotti_dettagli", nativeQuery = true)
    Set<Integer> isBanchinaDeletable();
    
}
