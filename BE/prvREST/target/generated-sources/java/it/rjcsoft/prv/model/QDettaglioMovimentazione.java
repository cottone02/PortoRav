package it.rjcsoft.prv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDettaglioMovimentazione is a Querydsl query type for DettaglioMovimentazione
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDettaglioMovimentazione extends EntityPathBase<DettaglioMovimentazione> {

    private static final long serialVersionUID = 423505529L;

    public static final QDettaglioMovimentazione dettaglioMovimentazione = new QDettaglioMovimentazione("dettaglioMovimentazione");

    public final DateTimePath<java.sql.Timestamp> dataDettaglio = createDateTime("dataDettaglio", java.sql.Timestamp.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> prodottoDettaglioId = createNumber("prodottoDettaglioId", Integer.class);

    public final NumberPath<Double> quantita = createNumber("quantita", Double.class);

    public QDettaglioMovimentazione(String variable) {
        super(DettaglioMovimentazione.class, forVariable(variable));
    }

    public QDettaglioMovimentazione(Path<? extends DettaglioMovimentazione> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDettaglioMovimentazione(PathMetadata metadata) {
        super(DettaglioMovimentazione.class, metadata);
    }

}

