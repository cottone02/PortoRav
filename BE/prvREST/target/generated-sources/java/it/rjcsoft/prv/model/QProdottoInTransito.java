package it.rjcsoft.prv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProdottoInTransito is a Querydsl query type for ProdottoInTransito
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProdottoInTransito extends EntityPathBase<ProdottoInTransito> {

    private static final long serialVersionUID = -665614605L;

    public static final QProdottoInTransito prodottoInTransito = new QProdottoInTransito("prodottoInTransito");

    public final StringPath aziendaTerza = createString("aziendaTerza");

    public final NumberPath<Integer> banchina = createNumber("banchina", Integer.class);

    public final BooleanPath contoTerzi = createBoolean("contoTerzi");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final BooleanPath inProprio = createBoolean("inProprio");

    public final ListPath<DettaglioSitoDepositi, QDettaglioSitoDepositi> listaDettaglioSitoDepositi = this.<DettaglioSitoDepositi, QDettaglioSitoDepositi>createList("listaDettaglioSitoDepositi", DettaglioSitoDepositi.class, QDettaglioSitoDepositi.class, PathInits.DIRECT2);

    public final ListPath<DettaglioSitoTrasporti, QDettaglioSitoTrasporti> listaDettaglioSitoTrasporti = this.<DettaglioSitoTrasporti, QDettaglioSitoTrasporti>createList("listaDettaglioSitoTrasporti", DettaglioSitoTrasporti.class, QDettaglioSitoTrasporti.class, PathInits.DIRECT2);

    public final NumberPath<Integer> schedaId = createNumber("schedaId", Integer.class);

    public QProdottoInTransito(String variable) {
        super(ProdottoInTransito.class, forVariable(variable));
    }

    public QProdottoInTransito(Path<? extends ProdottoInTransito> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProdottoInTransito(PathMetadata metadata) {
        super(ProdottoInTransito.class, metadata);
    }

}

