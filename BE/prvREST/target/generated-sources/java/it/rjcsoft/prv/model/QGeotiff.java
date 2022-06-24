package it.rjcsoft.prv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QGeotiff is a Querydsl query type for Geotiff
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QGeotiff extends EntityPathBase<Geotiff> {

    private static final long serialVersionUID = 1868841035L;

    public static final QGeotiff geotiff = new QGeotiff("geotiff");

    public final StringPath content = createString("content");

    public final ComparablePath<org.locationtech.jts.geom.Polygon> geometria = createComparable("geometria", org.locationtech.jts.geom.Polygon.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> idUtente = createNumber("idUtente", Integer.class);

    public final StringPath nomeFile = createString("nomeFile");

    public final BooleanPath processed = createBoolean("processed");

    public final StringPath uom = createString("uom");

    public QGeotiff(String variable) {
        super(Geotiff.class, forVariable(variable));
    }

    public QGeotiff(Path<? extends Geotiff> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGeotiff(PathMetadata metadata) {
        super(Geotiff.class, metadata);
    }

}

