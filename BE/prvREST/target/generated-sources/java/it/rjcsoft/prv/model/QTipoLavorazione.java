package it.rjcsoft.prv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTipoLavorazione is a Querydsl query type for TipoLavorazione
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTipoLavorazione extends EntityPathBase<TipoLavorazione> {

    private static final long serialVersionUID = 492707851L;

    public static final QTipoLavorazione tipoLavorazione = new QTipoLavorazione("tipoLavorazione");

    public final StringPath tipo = createString("tipo");

    public QTipoLavorazione(String variable) {
        super(TipoLavorazione.class, forVariable(variable));
    }

    public QTipoLavorazione(Path<? extends TipoLavorazione> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTipoLavorazione(PathMetadata metadata) {
        super(TipoLavorazione.class, metadata);
    }

}

