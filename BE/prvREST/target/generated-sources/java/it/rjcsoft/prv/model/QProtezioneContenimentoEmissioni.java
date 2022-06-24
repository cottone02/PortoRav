package it.rjcsoft.prv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QProtezioneContenimentoEmissioni is a Querydsl query type for ProtezioneContenimentoEmissioni
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProtezioneContenimentoEmissioni extends EntityPathBase<ProtezioneContenimentoEmissioni> {

    private static final long serialVersionUID = -2022937557L;

    public static final QProtezioneContenimentoEmissioni protezioneContenimentoEmissioni = new QProtezioneContenimentoEmissioni("protezioneContenimentoEmissioni");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath tipologia = createString("tipologia");

    public QProtezioneContenimentoEmissioni(String variable) {
        super(ProtezioneContenimentoEmissioni.class, forVariable(variable));
    }

    public QProtezioneContenimentoEmissioni(Path<? extends ProtezioneContenimentoEmissioni> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProtezioneContenimentoEmissioni(PathMetadata metadata) {
        super(ProtezioneContenimentoEmissioni.class, metadata);
    }

}

