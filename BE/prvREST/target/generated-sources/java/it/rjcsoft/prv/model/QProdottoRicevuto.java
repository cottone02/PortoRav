package it.rjcsoft.prv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QProdottoRicevuto is a Querydsl query type for ProdottoRicevuto
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProdottoRicevuto extends EntityPathBase<ProdottoRicevuto> {

    private static final long serialVersionUID = -996292603L;

    public static final QProdottoRicevuto prodottoRicevuto = new QProdottoRicevuto("prodottoRicevuto");

    public final StringPath aziendaTerza = createString("aziendaTerza");

    public final NumberPath<Integer> banchina = createNumber("banchina", Integer.class);

    public final BooleanPath contoTerzi = createBoolean("contoTerzi");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final BooleanPath inProprio = createBoolean("inProprio");

    public final NumberPath<Double> movViaFerrovia = createNumber("movViaFerrovia", Double.class);

    public final NumberPath<Double> movViaNave = createNumber("movViaNave", Double.class);

    public final NumberPath<Double> movViaTerra = createNumber("movViaTerra", Double.class);

    public final NumberPath<Integer> schedaId = createNumber("schedaId", Integer.class);

    public QProdottoRicevuto(String variable) {
        super(ProdottoRicevuto.class, forVariable(variable));
    }

    public QProdottoRicevuto(Path<? extends ProdottoRicevuto> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProdottoRicevuto(PathMetadata metadata) {
        super(ProdottoRicevuto.class, metadata);
    }

}

