package it.rjcsoft.prv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QProdottiAllegati is a Querydsl query type for ProdottiAllegati
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProdottiAllegati extends EntityPathBase<ProdottiAllegati> {

    private static final long serialVersionUID = 320234975L;

    public static final QProdottiAllegati prodottiAllegati = new QProdottiAllegati("prodottiAllegati");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath nomeFile = createString("nomeFile");

    public final NumberPath<Integer> schedaId = createNumber("schedaId", Integer.class);

    public QProdottiAllegati(String variable) {
        super(ProdottiAllegati.class, forVariable(variable));
    }

    public QProdottiAllegati(Path<? extends ProdottiAllegati> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProdottiAllegati(PathMetadata metadata) {
        super(ProdottiAllegati.class, metadata);
    }

}

