package it.rjcsoft.prv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProdotto is a Querydsl query type for Prodotto
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProdotto extends EntityPathBase<Prodotto> {

    private static final long serialVersionUID = -757016558L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProdotto prodotto = new QProdotto("prodotto");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath nome = createString("nome");

    public final QPolverosita polverosita;

    public QProdotto(String variable) {
        this(Prodotto.class, forVariable(variable), INITS);
    }

    public QProdotto(Path<? extends Prodotto> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProdotto(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProdotto(PathMetadata metadata, PathInits inits) {
        this(Prodotto.class, metadata, inits);
    }

    public QProdotto(Class<? extends Prodotto> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.polverosita = inits.isInitialized("polverosita") ? new QPolverosita(forProperty("polverosita")) : null;
    }

}

