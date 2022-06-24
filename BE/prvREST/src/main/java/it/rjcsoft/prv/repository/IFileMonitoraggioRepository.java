
package it.rjcsoft.prv.repository;

import java.util.List;

import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;

import it.rjcsoft.prv.model.FileMonitoraggio;
import it.rjcsoft.prv.model.QFileMonitoraggio;

public interface IFileMonitoraggioRepository extends JpaRepository<FileMonitoraggio, Integer>,
        QuerydslPredicateExecutor<FileMonitoraggio>, QuerydslBinderCustomizer<QFileMonitoraggio> {

    @Override
    public default void customize(QuerydslBindings bindings, QFileMonitoraggio fileMonitoraggio) {
        bindings.bind(String.class)
                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
    }

    public List<FileMonitoraggio> findByIdMonitoring(int idMonitoring);
    
    public FileMonitoraggio findByIdMonitoringAndFileName(int idMonitoring, String fileName);
    
    
    public Integer deleteByIdMonitoringAndFileName(int idMonitoring,String FileName);
}
