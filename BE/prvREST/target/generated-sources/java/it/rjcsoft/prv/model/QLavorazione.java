package it.rjcsoft.prv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QLavorazione is a Querydsl query type for Lavorazione
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLavorazione extends EntityPathBase<Lavorazione> {

    private static final long serialVersionUID = -1710547361L;

    public static final QLavorazione lavorazione1 = new QLavorazione("lavorazione1");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath lavorazione = createString("lavorazione");

    public QLavorazione(String variable) {
        super(Lavorazione.class, forVariable(variable));
    }

    public QLavorazione(Path<? extends Lavorazione> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLavorazione(PathMetadata metadata) {
        super(Lavorazione.class, metadata);
    }

}

