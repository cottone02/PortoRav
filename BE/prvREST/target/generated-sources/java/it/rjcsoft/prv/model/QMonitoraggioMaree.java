package it.rjcsoft.prv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMonitoraggioMaree is a Querydsl query type for MonitoraggioMaree
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMonitoraggioMaree extends EntityPathBase<MonitoraggioMaree> {

    private static final long serialVersionUID = -365915402L;

    public static final QMonitoraggioMaree monitoraggioMaree = new QMonitoraggioMaree("monitoraggioMaree");

    public final DatePath<java.sql.Date> dataOra = createDate("dataOra", java.sql.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> idMonitoraggio = createNumber("idMonitoraggio", Integer.class);

    public final NumberPath<Double> lvlIdrometrico = createNumber("lvlIdrometrico", Double.class);

    public final StringPath nomeFile = createString("nomeFile");

    public final NumberPath<Integer> numeroPagina = createNumber("numeroPagina", Integer.class);

    public QMonitoraggioMaree(String variable) {
        super(MonitoraggioMaree.class, forVariable(variable));
    }

    public QMonitoraggioMaree(Path<? extends MonitoraggioMaree> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMonitoraggioMaree(PathMetadata metadata) {
        super(MonitoraggioMaree.class, metadata);
    }

}

