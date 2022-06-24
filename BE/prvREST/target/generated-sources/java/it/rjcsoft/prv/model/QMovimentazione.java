package it.rjcsoft.prv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMovimentazione is a Querydsl query type for Movimentazione
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMovimentazione extends EntityPathBase<Movimentazione> {

    private static final long serialVersionUID = 2136014436L;

    public static final QMovimentazione movimentazione = new QMovimentazione("movimentazione");

    public final StringPath aziendaTerza = createString("aziendaTerza");

    public final NumberPath<Integer> banchina = createNumber("banchina", Integer.class);

    public final BooleanPath contoTerzi = createBoolean("contoTerzi");

    public final ListPath<DettaglioMovimentazione, QDettaglioMovimentazione> dettagliMovimentazione = this.<DettaglioMovimentazione, QDettaglioMovimentazione>createList("dettagliMovimentazione", DettaglioMovimentazione.class, QDettaglioMovimentazione.class, PathInits.DIRECT2);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final BooleanPath inProprio = createBoolean("inProprio");

    public final NumberPath<Double> movViaFerrovia = createNumber("movViaFerrovia", Double.class);

    public final NumberPath<Double> movViaNave = createNumber("movViaNave", Double.class);

    public final NumberPath<Double> movViaTerra = createNumber("movViaTerra", Double.class);

    public final NumberPath<Integer> schedaId = createNumber("schedaId", Integer.class);

    public QMovimentazione(String variable) {
        super(Movimentazione.class, forVariable(variable));
    }

    public QMovimentazione(Path<? extends Movimentazione> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMovimentazione(PathMetadata metadata) {
        super(Movimentazione.class, metadata);
    }

}

