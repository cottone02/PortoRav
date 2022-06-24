package it.rjcsoft.prv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDotazione is a Querydsl query type for Dotazione
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDotazione extends EntityPathBase<Dotazione> {

    private static final long serialVersionUID = 1649933796L;

    public static final QDotazione dotazione = new QDotazione("dotazione");

    public final StringPath attrezzatura = createString("attrezzatura");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QDotazione(String variable) {
        super(Dotazione.class, forVariable(variable));
    }

    public QDotazione(Path<? extends Dotazione> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDotazione(PathMetadata metadata) {
        super(Dotazione.class, metadata);
    }

}

