package it.rjcsoft.prv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPolverosita is a Querydsl query type for Polverosita
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPolverosita extends EntityPathBase<Polverosita> {

    private static final long serialVersionUID = -1025978175L;

    public static final QPolverosita polverosita = new QPolverosita("polverosita");

    public final StringPath tipo = createString("tipo");

    public QPolverosita(String variable) {
        super(Polverosita.class, forVariable(variable));
    }

    public QPolverosita(Path<? extends Polverosita> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPolverosita(PathMetadata metadata) {
        super(Polverosita.class, metadata);
    }

}

