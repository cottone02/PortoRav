package it.rjcsoft.prv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QProdottoDettagli is a Querydsl query type for ProdottoDettagli
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProdottoDettagli extends EntityPathBase<ProdottoDettagli> {

    private static final long serialVersionUID = 1619028118L;

    public static final QProdottoDettagli prodottoDettagli = new QProdottoDettagli("prodottoDettagli");

    public final StringPath aziendaTerza = createString("aziendaTerza");

    public final NumberPath<Integer> banchina = createNumber("banchina", Integer.class);

    public final BooleanPath contoTerzi = createBoolean("contoTerzi");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final BooleanPath inProprio = createBoolean("inProprio");

    public final NumberPath<Double> movViaFerrovia = createNumber("movViaFerrovia", Double.class);

    public final NumberPath<Double> movViaNave = createNumber("movViaNave", Double.class);

    public final NumberPath<Double> movViaTerra = createNumber("movViaTerra", Double.class);

    public final NumberPath<Integer> schedaId = createNumber("schedaId", Integer.class);

    public QProdottoDettagli(String variable) {
        super(ProdottoDettagli.class, forVariable(variable));
    }

    public QProdottoDettagli(Path<? extends ProdottoDettagli> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProdottoDettagli(PathMetadata metadata) {
        super(ProdottoDettagli.class, metadata);
    }

}

