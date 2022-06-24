package it.rjcsoft.prv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDettaglioSitoDepositi is a Querydsl query type for DettaglioSitoDepositi
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDettaglioSitoDepositi extends EntityPathBase<DettaglioSitoDepositi> {

    private static final long serialVersionUID = 174312684L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDettaglioSitoDepositi dettaglioSitoDepositi = new QDettaglioSitoDepositi("dettaglioSitoDepositi");

    public final NumberPath<Double> capacita = createNumber("capacita", Double.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> numero = createNumber("numero", Integer.class);

    public final NumberPath<Integer> prodottoDettaglioId = createNumber("prodottoDettaglioId", Integer.class);

    public final QTipoDepositoStoccaggio tipoDepositoStoccaggio;

    public QDettaglioSitoDepositi(String variable) {
        this(DettaglioSitoDepositi.class, forVariable(variable), INITS);
    }

    public QDettaglioSitoDepositi(Path<? extends DettaglioSitoDepositi> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDettaglioSitoDepositi(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDettaglioSitoDepositi(PathMetadata metadata, PathInits inits) {
        this(DettaglioSitoDepositi.class, metadata, inits);
    }

    public QDettaglioSitoDepositi(Class<? extends DettaglioSitoDepositi> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.tipoDepositoStoccaggio = inits.isInitialized("tipoDepositoStoccaggio") ? new QTipoDepositoStoccaggio(forProperty("tipoDepositoStoccaggio")) : null;
    }

}

