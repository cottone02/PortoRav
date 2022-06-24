package it.rjcsoft.prv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAzienda is a Querydsl query type for Azienda
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAzienda extends EntityPathBase<Azienda> {

    private static final long serialVersionUID = 1434015163L;

    public static final QAzienda azienda = new QAzienda("azienda");

    public final StringPath address = createString("address");

    public final StringPath businessName = createString("businessName");

    public final StringPath city = createString("city");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final StringPath vatNumber = createString("vatNumber");

    public QAzienda(String variable) {
        super(Azienda.class, forVariable(variable));
    }

    public QAzienda(Path<? extends Azienda> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAzienda(PathMetadata metadata) {
        super(Azienda.class, metadata);
    }

}

