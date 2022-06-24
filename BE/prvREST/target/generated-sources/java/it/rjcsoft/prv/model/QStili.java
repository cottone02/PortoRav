package it.rjcsoft.prv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QStili is a Querydsl query type for Stili
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStili extends EntityPathBase<Stili> {

    private static final long serialVersionUID = 1108438634L;

    public static final QStili stili = new QStili("stili");

    public final StringPath descrizione = createString("descrizione");

    public final StringPath geotiffBase64 = createString("geotiffBase64");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> idGeotiff = createNumber("idGeotiff", Integer.class);

    public final StringPath legendBase64 = createString("legendBase64");

    public final BooleanPath stato = createBoolean("stato");

    public QStili(String variable) {
        super(Stili.class, forVariable(variable));
    }

    public QStili(Path<? extends Stili> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStili(PathMetadata metadata) {
        super(Stili.class, metadata);
    }

}

