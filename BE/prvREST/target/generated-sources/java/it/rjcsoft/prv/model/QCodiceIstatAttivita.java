package it.rjcsoft.prv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCodiceIstatAttivita is a Querydsl query type for CodiceIstatAttivita
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCodiceIstatAttivita extends EntityPathBase<CodiceIstatAttivita> {

    private static final long serialVersionUID = 934942807L;

    public static final QCodiceIstatAttivita codiceIstatAttivita = new QCodiceIstatAttivita("codiceIstatAttivita");

    public final StringPath codice = createString("codice");

    public final StringPath descrizione = createString("descrizione");

    public QCodiceIstatAttivita(String variable) {
        super(CodiceIstatAttivita.class, forVariable(variable));
    }

    public QCodiceIstatAttivita(Path<? extends CodiceIstatAttivita> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCodiceIstatAttivita(PathMetadata metadata) {
        super(CodiceIstatAttivita.class, metadata);
    }

}

