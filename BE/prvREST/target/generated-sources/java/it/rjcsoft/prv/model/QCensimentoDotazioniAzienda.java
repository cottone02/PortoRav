package it.rjcsoft.prv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCensimentoDotazioniAzienda is a Querydsl query type for CensimentoDotazioniAzienda
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCensimentoDotazioniAzienda extends EntityPathBase<CensimentoDotazioniAzienda> {

    private static final long serialVersionUID = -1531098115L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCensimentoDotazioniAzienda censimentoDotazioniAzienda = new QCensimentoDotazioniAzienda("censimentoDotazioniAzienda");

    public final NumberPath<Integer> censimentoAziendaId = createNumber("censimentoAziendaId", Integer.class);

    public final QDotazione dotazione;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> quantita = createNumber("quantita", Integer.class);

    public QCensimentoDotazioniAzienda(String variable) {
        this(CensimentoDotazioniAzienda.class, forVariable(variable), INITS);
    }

    public QCensimentoDotazioniAzienda(Path<? extends CensimentoDotazioniAzienda> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCensimentoDotazioniAzienda(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCensimentoDotazioniAzienda(PathMetadata metadata, PathInits inits) {
        this(CensimentoDotazioniAzienda.class, metadata, inits);
    }

    public QCensimentoDotazioniAzienda(Class<? extends CensimentoDotazioniAzienda> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.dotazione = inits.isInitialized("dotazione") ? new QDotazione(forProperty("dotazione")) : null;
    }

}

