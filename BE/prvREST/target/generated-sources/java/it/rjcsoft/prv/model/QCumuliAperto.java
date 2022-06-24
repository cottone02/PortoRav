package it.rjcsoft.prv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCumuliAperto is a Querydsl query type for CumuliAperto
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCumuliAperto extends EntityPathBase<CumuliAperto> {

    private static final long serialVersionUID = -1107152983L;

    public static final QCumuliAperto cumuliAperto = new QCumuliAperto("cumuliAperto");

    public final StringPath aziendaTerza = createString("aziendaTerza");

    public final NumberPath<Integer> banchina = createNumber("banchina", Integer.class);

    public final BooleanPath contoTerzi = createBoolean("contoTerzi");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final BooleanPath inProprio = createBoolean("inProprio");

    public final ListPath<CumuliApertoLocalizzazione, QCumuliApertoLocalizzazione> listaCumuliApertoLocalizzazione = this.<CumuliApertoLocalizzazione, QCumuliApertoLocalizzazione>createList("listaCumuliApertoLocalizzazione", CumuliApertoLocalizzazione.class, QCumuliApertoLocalizzazione.class, PathInits.DIRECT2);

    public final NumberPath<Double> movViaFerrovia = createNumber("movViaFerrovia", Double.class);

    public final NumberPath<Double> movViaNave = createNumber("movViaNave", Double.class);

    public final NumberPath<Double> movViaTerra = createNumber("movViaTerra", Double.class);

    public final NumberPath<Integer> schedaId = createNumber("schedaId", Integer.class);

    public QCumuliAperto(String variable) {
        super(CumuliAperto.class, forVariable(variable));
    }

    public QCumuliAperto(Path<? extends CumuliAperto> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCumuliAperto(PathMetadata metadata) {
        super(CumuliAperto.class, metadata);
    }

}

