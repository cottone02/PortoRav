package it.rjcsoft.prv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBanchina is a Querydsl query type for Banchina
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBanchina extends EntityPathBase<Banchina> {

    private static final long serialVersionUID = -1618990141L;

    public static final QBanchina banchina = new QBanchina("banchina");

    public final ComparablePath<org.locationtech.jts.geom.LineString> banchinaGeo = createComparable("banchinaGeo", org.locationtech.jts.geom.LineString.class);

    public final StringPath colore = createString("colore");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath nome = createString("nome");

    public QBanchina(String variable) {
        super(Banchina.class, forVariable(variable));
    }

    public QBanchina(Path<? extends Banchina> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBanchina(PathMetadata metadata) {
        super(Banchina.class, metadata);
    }

}

