package it.rjcsoft.prv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCensimentoInterventiMitigazione is a Querydsl query type for CensimentoInterventiMitigazione
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCensimentoInterventiMitigazione extends EntityPathBase<CensimentoInterventiMitigazione> {

    private static final long serialVersionUID = -534448310L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCensimentoInterventiMitigazione censimentoInterventiMitigazione = new QCensimentoInterventiMitigazione("censimentoInterventiMitigazione");

    public final NumberPath<Integer> censimentoAziendaId = createNumber("censimentoAziendaId", Integer.class);

    public final BooleanPath cumuliAperto = createBoolean("cumuliAperto");

    public final DateTimePath<java.sql.Timestamp> dataAttivazione = createDateTime("dataAttivazione", java.sql.Timestamp.class);

    public final BooleanPath emissioniConvogliate = createBoolean("emissioniConvogliate");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QInterventoMitigazionePolveri intervento;

    public final StringPath note = createString("note");

    public final StringPath presenzaProceduraFormalizzata = createString("presenzaProceduraFormalizzata");

    public final BooleanPath sbarcoMateriale = createBoolean("sbarcoMateriale");

    public final BooleanPath trasportoAutocarri = createBoolean("trasportoAutocarri");

    public final NumberPath<Integer> valutazioneQualitativa = createNumber("valutazioneQualitativa", Integer.class);

    public QCensimentoInterventiMitigazione(String variable) {
        this(CensimentoInterventiMitigazione.class, forVariable(variable), INITS);
    }

    public QCensimentoInterventiMitigazione(Path<? extends CensimentoInterventiMitigazione> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCensimentoInterventiMitigazione(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCensimentoInterventiMitigazione(PathMetadata metadata, PathInits inits) {
        this(CensimentoInterventiMitigazione.class, metadata, inits);
    }

    public QCensimentoInterventiMitigazione(Class<? extends CensimentoInterventiMitigazione> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.intervento = inits.isInitialized("intervento") ? new QInterventoMitigazionePolveri(forProperty("intervento")) : null;
    }

}

