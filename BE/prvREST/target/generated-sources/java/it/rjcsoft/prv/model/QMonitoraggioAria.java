package it.rjcsoft.prv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMonitoraggioAria is a Querydsl query type for MonitoraggioAria
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMonitoraggioAria extends EntityPathBase<MonitoraggioAria> {

    private static final long serialVersionUID = 957686161L;

    public static final QMonitoraggioAria monitoraggioAria = new QMonitoraggioAria("monitoraggioAria");

    public final DatePath<java.sql.Date> data = createDate("data", java.sql.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> idMonitoraggio = createNumber("idMonitoraggio", Integer.class);

    public final NumberPath<Float> mediaOraria = createNumber("mediaOraria", Float.class);

    public final StringPath nomeFile = createString("nomeFile");

    public final NumberPath<Integer> numeroPagina = createNumber("numeroPagina", Integer.class);

    public final StringPath parametro = createString("parametro");

    public final StringPath stazione = createString("stazione");

    public final BooleanPath validita = createBoolean("validita");

    public QMonitoraggioAria(String variable) {
        super(MonitoraggioAria.class, forVariable(variable));
    }

    public QMonitoraggioAria(Path<? extends MonitoraggioAria> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMonitoraggioAria(PathMetadata metadata) {
        super(MonitoraggioAria.class, metadata);
    }

}

