package it.rjcsoft.prv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDettaglioSitoTrasporti is a Querydsl query type for DettaglioSitoTrasporti
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDettaglioSitoTrasporti extends EntityPathBase<DettaglioSitoTrasporti> {

    private static final long serialVersionUID = -1174773865L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDettaglioSitoTrasporti dettaglioSitoTrasporti = new QDettaglioSitoTrasporti("dettaglioSitoTrasporti");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> prodottoDettaglioId = createNumber("prodottoDettaglioId", Integer.class);

    public final NumberPath<Double> quantita = createNumber("quantita", Double.class);

    public final QTipoTrasportoInDeposito trasportoInDeposito;

    public QDettaglioSitoTrasporti(String variable) {
        this(DettaglioSitoTrasporti.class, forVariable(variable), INITS);
    }

    public QDettaglioSitoTrasporti(Path<? extends DettaglioSitoTrasporti> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDettaglioSitoTrasporti(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDettaglioSitoTrasporti(PathMetadata metadata, PathInits inits) {
        this(DettaglioSitoTrasporti.class, metadata, inits);
    }

    public QDettaglioSitoTrasporti(Class<? extends DettaglioSitoTrasporti> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.trasportoInDeposito = inits.isInitialized("trasportoInDeposito") ? new QTipoTrasportoInDeposito(forProperty("trasportoInDeposito")) : null;
    }

}

