package it.rjcsoft.prv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLavorazioneProdottoDettagli is a Querydsl query type for LavorazioneProdottoDettagli
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLavorazioneProdottoDettagli extends EntityPathBase<LavorazioneProdottoDettagli> {

    private static final long serialVersionUID = -1568737542L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLavorazioneProdottoDettagli lavorazioneProdottoDettagli = new QLavorazioneProdottoDettagli("lavorazioneProdottoDettagli");

    public final NumberPath<Integer> ggAnno = createNumber("ggAnno", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QLavorazione lavorazione;

    public final NumberPath<Integer> oreGg = createNumber("oreGg", Integer.class);

    public final NumberPath<Integer> prodottoDettaglioId = createNumber("prodottoDettaglioId", Integer.class);

    public final QTipoLavorazione tipoLavorazione;

    public QLavorazioneProdottoDettagli(String variable) {
        this(LavorazioneProdottoDettagli.class, forVariable(variable), INITS);
    }

    public QLavorazioneProdottoDettagli(Path<? extends LavorazioneProdottoDettagli> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLavorazioneProdottoDettagli(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLavorazioneProdottoDettagli(PathMetadata metadata, PathInits inits) {
        this(LavorazioneProdottoDettagli.class, metadata, inits);
    }

    public QLavorazioneProdottoDettagli(Class<? extends LavorazioneProdottoDettagli> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.lavorazione = inits.isInitialized("lavorazione") ? new QLavorazione(forProperty("lavorazione")) : null;
        this.tipoLavorazione = inits.isInitialized("tipoLavorazione") ? new QTipoLavorazione(forProperty("tipoLavorazione")) : null;
    }

}

