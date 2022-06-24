package it.rjcsoft.prv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QProdottoInUscita is a Querydsl query type for ProdottoInUscita
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProdottoInUscita extends EntityPathBase<ProdottoInUscita> {

    private static final long serialVersionUID = 252378280L;

    public static final QProdottoInUscita prodottoInUscita = new QProdottoInUscita("prodottoInUscita");

    public final StringPath aziendaTerza = createString("aziendaTerza");

    public final NumberPath<Integer> banchina = createNumber("banchina", Integer.class);

    public final BooleanPath contoTerzi = createBoolean("contoTerzi");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final BooleanPath inProprio = createBoolean("inProprio");

    public final NumberPath<Double> movViaFerrovia = createNumber("movViaFerrovia", Double.class);

    public final NumberPath<Double> movViaNave = createNumber("movViaNave", Double.class);

    public final NumberPath<Double> movViaTerra = createNumber("movViaTerra", Double.class);

    public final NumberPath<Integer> schedaId = createNumber("schedaId", Integer.class);

    public QProdottoInUscita(String variable) {
        super(ProdottoInUscita.class, forVariable(variable));
    }

    public QProdottoInUscita(Path<? extends ProdottoInUscita> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProdottoInUscita(PathMetadata metadata) {
        super(ProdottoInUscita.class, metadata);
    }

}

