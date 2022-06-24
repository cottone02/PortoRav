package it.rjcsoft.prv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTipoDepositoStoccaggio is a Querydsl query type for TipoDepositoStoccaggio
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTipoDepositoStoccaggio extends EntityPathBase<TipoDepositoStoccaggio> {

    private static final long serialVersionUID = -958512071L;

    public static final QTipoDepositoStoccaggio tipoDepositoStoccaggio = new QTipoDepositoStoccaggio("tipoDepositoStoccaggio");

    public final StringPath depositoStoccaggio = createString("depositoStoccaggio");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QTipoDepositoStoccaggio(String variable) {
        super(TipoDepositoStoccaggio.class, forVariable(variable));
    }

    public QTipoDepositoStoccaggio(Path<? extends TipoDepositoStoccaggio> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTipoDepositoStoccaggio(PathMetadata metadata) {
        super(TipoDepositoStoccaggio.class, metadata);
    }

}

