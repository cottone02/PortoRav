package it.rjcsoft.prv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCumuliApertoLocalizzazione is a Querydsl query type for CumuliApertoLocalizzazione
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCumuliApertoLocalizzazione extends EntityPathBase<CumuliApertoLocalizzazione> {

    private static final long serialVersionUID = -865926979L;

    public static final QCumuliApertoLocalizzazione cumuliApertoLocalizzazione = new QCumuliApertoLocalizzazione("cumuliApertoLocalizzazione");

    public final NumberPath<Double> altezza = createNumber("altezza", Double.class);

    public final NumberPath<Double> area = createNumber("area", Double.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final ComparablePath<org.locationtech.jts.geom.Polygon> localizzazione = createComparable("localizzazione", org.locationtech.jts.geom.Polygon.class);

    public final NumberPath<Integer> prodottoDettaglioId = createNumber("prodottoDettaglioId", Integer.class);

    public QCumuliApertoLocalizzazione(String variable) {
        super(CumuliApertoLocalizzazione.class, forVariable(variable));
    }

    public QCumuliApertoLocalizzazione(Path<? extends CumuliApertoLocalizzazione> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCumuliApertoLocalizzazione(PathMetadata metadata) {
        super(CumuliApertoLocalizzazione.class, metadata);
    }

}

