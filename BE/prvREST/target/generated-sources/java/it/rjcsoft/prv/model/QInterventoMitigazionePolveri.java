package it.rjcsoft.prv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QInterventoMitigazionePolveri is a Querydsl query type for InterventoMitigazionePolveri
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QInterventoMitigazionePolveri extends EntityPathBase<InterventoMitigazionePolveri> {

    private static final long serialVersionUID = -1895828864L;

    public static final QInterventoMitigazionePolveri interventoMitigazionePolveri = new QInterventoMitigazionePolveri("interventoMitigazionePolveri");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath tipologia = createString("tipologia");

    public QInterventoMitigazionePolveri(String variable) {
        super(InterventoMitigazionePolveri.class, forVariable(variable));
    }

    public QInterventoMitigazionePolveri(Path<? extends InterventoMitigazionePolveri> path) {
        super(path.getType(), path.getMetadata());
    }

    public QInterventoMitigazionePolveri(PathMetadata metadata) {
        super(InterventoMitigazionePolveri.class, metadata);
    }

}

