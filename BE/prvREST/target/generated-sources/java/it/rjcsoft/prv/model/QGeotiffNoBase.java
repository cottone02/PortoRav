package it.rjcsoft.prv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QGeotiffNoBase is a Querydsl query type for GeotiffNoBase
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QGeotiffNoBase extends EntityPathBase<GeotiffNoBase> {

    private static final long serialVersionUID = -302167971L;

    public static final QGeotiffNoBase geotiffNoBase = new QGeotiffNoBase("geotiffNoBase");

    public final ComparablePath<org.locationtech.jts.geom.Polygon> geometria = createComparable("geometria", org.locationtech.jts.geom.Polygon.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> idUtente = createNumber("idUtente", Integer.class);

    public final StringPath nomeFile = createString("nomeFile");

    public final BooleanPath processed = createBoolean("processed");

    public final StringPath uom = createString("uom");

    public QGeotiffNoBase(String variable) {
        super(GeotiffNoBase.class, forVariable(variable));
    }

    public QGeotiffNoBase(Path<? extends GeotiffNoBase> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGeotiffNoBase(PathMetadata metadata) {
        super(GeotiffNoBase.class, metadata);
    }

}

