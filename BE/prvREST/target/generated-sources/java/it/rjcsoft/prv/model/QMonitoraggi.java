package it.rjcsoft.prv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMonitoraggi is a Querydsl query type for Monitoraggi
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMonitoraggi extends EntityPathBase<Monitoraggi> {

    private static final long serialVersionUID = -87733017L;

    public static final QMonitoraggi monitoraggi = new QMonitoraggi("monitoraggi");

    public final NumberPath<Integer> companyId = createNumber("companyId", Integer.class);

    public final DateTimePath<java.sql.Timestamp> date = createDateTime("date", java.sql.Timestamp.class);

    public final StringPath description = createString("description");

    public final ListPath<FileMonitoraggio, QFileMonitoraggio> fileMonitoraggi = this.<FileMonitoraggio, QFileMonitoraggio>createList("fileMonitoraggi", FileMonitoraggio.class, QFileMonitoraggio.class, PathInits.DIRECT2);

    public final ComparablePath<org.locationtech.jts.geom.Point> geometry = createComparable("geometry", org.locationtech.jts.geom.Point.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath performedBy = createString("performedBy");

    public final StringPath place = createString("place");

    public final NumberPath<Integer> userId = createNumber("userId", Integer.class);

    public QMonitoraggi(String variable) {
        super(Monitoraggi.class, forVariable(variable));
    }

    public QMonitoraggi(Path<? extends Monitoraggi> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMonitoraggi(PathMetadata metadata) {
        super(Monitoraggi.class, metadata);
    }

}

