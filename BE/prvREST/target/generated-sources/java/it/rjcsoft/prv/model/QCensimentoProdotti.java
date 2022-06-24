package it.rjcsoft.prv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCensimentoProdotti is a Querydsl query type for CensimentoProdotti
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCensimentoProdotti extends EntityPathBase<CensimentoProdotti> {

    private static final long serialVersionUID = 1642715099L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCensimentoProdotti censimentoProdotti = new QCensimentoProdotti("censimentoProdotti");

    public final NumberPath<Integer> censimentoAziendaId = createNumber("censimentoAziendaId", Integer.class);

    public final QClasseGranulometrica classeGranulometrica;

    public final NumberPath<Double> densita = createNumber("densita", Double.class);

    public final NumberPath<Integer> inConfezioni = createNumber("inConfezioni", Integer.class);

    public final ComparablePath<org.locationtech.jts.geom.Polygon> mappaStabilimento = createComparable("mappaStabilimento", org.locationtech.jts.geom.Polygon.class);

    public final ListPath<ProdottiAllegati, QProdottiAllegati> prodottiAllegati = this.<ProdottiAllegati, QProdottiAllegati>createList("prodottiAllegati", ProdottiAllegati.class, QProdottiAllegati.class, PathInits.DIRECT2);

    public final QProdotto prodotto;

    public final NumberPath<Integer> schedaId = createNumber("schedaId", Integer.class);

    public final NumberPath<Integer> sfuso = createNumber("sfuso", Integer.class);

    public final NumberPath<Integer> silt = createNumber("silt", Integer.class);

    public final NumberPath<Integer> umidita = createNumber("umidita", Integer.class);

    public QCensimentoProdotti(String variable) {
        this(CensimentoProdotti.class, forVariable(variable), INITS);
    }

    public QCensimentoProdotti(Path<? extends CensimentoProdotti> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCensimentoProdotti(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCensimentoProdotti(PathMetadata metadata, PathInits inits) {
        this(CensimentoProdotti.class, metadata, inits);
    }

    public QCensimentoProdotti(Class<? extends CensimentoProdotti> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.classeGranulometrica = inits.isInitialized("classeGranulometrica") ? new QClasseGranulometrica(forProperty("classeGranulometrica")) : null;
        this.prodotto = inits.isInitialized("prodotto") ? new QProdotto(forProperty("prodotto"), inits.get("prodotto")) : null;
    }

}

