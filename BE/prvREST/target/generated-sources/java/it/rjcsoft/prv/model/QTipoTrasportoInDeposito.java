package it.rjcsoft.prv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTipoTrasportoInDeposito is a Querydsl query type for TipoTrasportoInDeposito
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTipoTrasportoInDeposito extends EntityPathBase<TipoTrasportoInDeposito> {

    private static final long serialVersionUID = -631308027L;

    public static final QTipoTrasportoInDeposito tipoTrasportoInDeposito = new QTipoTrasportoInDeposito("tipoTrasportoInDeposito");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath proprietaDeposito = createString("proprietaDeposito");

    public final BooleanPath tipoDepositoRequired = createBoolean("tipoDepositoRequired");

    public QTipoTrasportoInDeposito(String variable) {
        super(TipoTrasportoInDeposito.class, forVariable(variable));
    }

    public QTipoTrasportoInDeposito(Path<? extends TipoTrasportoInDeposito> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTipoTrasportoInDeposito(PathMetadata metadata) {
        super(TipoTrasportoInDeposito.class, metadata);
    }

}

