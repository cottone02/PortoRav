package it.rjcsoft.prv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCensimentoProtezioniContenimento is a Querydsl query type for CensimentoProtezioniContenimento
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCensimentoProtezioniContenimento extends EntityPathBase<CensimentoProtezioniContenimento> {

    private static final long serialVersionUID = 4949226L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCensimentoProtezioniContenimento censimentoProtezioniContenimento = new QCensimentoProtezioniContenimento("censimentoProtezioniContenimento");

    public final NumberPath<Integer> censimentoAziendaId = createNumber("censimentoAziendaId", Integer.class);

    public final QProtezioneContenimentoEmissioni contenimento;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath note = createString("note");

    public QCensimentoProtezioniContenimento(String variable) {
        this(CensimentoProtezioniContenimento.class, forVariable(variable), INITS);
    }

    public QCensimentoProtezioniContenimento(Path<? extends CensimentoProtezioniContenimento> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCensimentoProtezioniContenimento(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCensimentoProtezioniContenimento(PathMetadata metadata, PathInits inits) {
        this(CensimentoProtezioniContenimento.class, metadata, inits);
    }

    public QCensimentoProtezioniContenimento(Class<? extends CensimentoProtezioniContenimento> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.contenimento = inits.isInitialized("contenimento") ? new QProtezioneContenimentoEmissioni(forProperty("contenimento")) : null;
    }

}

