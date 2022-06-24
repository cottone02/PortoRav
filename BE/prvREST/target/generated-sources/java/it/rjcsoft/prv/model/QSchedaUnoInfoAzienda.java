package it.rjcsoft.prv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSchedaUnoInfoAzienda is a Querydsl query type for SchedaUnoInfoAzienda
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSchedaUnoInfoAzienda extends EntityPathBase<SchedaUnoInfoAzienda> {

    private static final long serialVersionUID = -259236201L;

    public static final QSchedaUnoInfoAzienda schedaUnoInfoAzienda = new QSchedaUnoInfoAzienda("schedaUnoInfoAzienda");

    public final StringPath anno = createString("anno");

    public final BooleanPath autEmissioniConvogliate = createBoolean("autEmissioniConvogliate");

    public final StringPath autEmissioniConvogliateDesc = createString("autEmissioniConvogliateDesc");

    public final NumberPath<Integer> codIstatAttivita = createNumber("codIstatAttivita", Integer.class);

    public final NumberPath<Double> consumiIdriciAnnui = createNumber("consumiIdriciAnnui", Double.class);

    public final DateTimePath<java.sql.Timestamp> created = createDateTime("created", java.sql.Timestamp.class);

    public final NumberPath<Integer> createdBy = createNumber("createdBy", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> indirizzoImpianto = createNumber("indirizzoImpianto", Integer.class);

    public final NumberPath<Integer> numDipendenti = createNumber("numDipendenti", Integer.class);

    public final NumberPath<Integer> numScheda = createNumber("numScheda", Integer.class);

    public final NumberPath<Double> rifiutiTotProdotti = createNumber("rifiutiTotProdotti", Double.class);

    public final NumberPath<Double> rifiutiTotProdottiPericolosi = createNumber("rifiutiTotProdottiPericolosi", Double.class);

    public final NumberPath<Double> scarichiIdriciAnnui = createNumber("scarichiIdriciAnnui", Double.class);

    public final StringPath sistTrattAcqueDesc = createString("sistTrattAcqueDesc");

    public final BooleanPath sistTrattAcqueMet = createBoolean("sistTrattAcqueMet");

    public final NumberPath<Double> supTotCoperta = createNumber("supTotCoperta", Double.class);

    public final NumberPath<Double> supTotOccupata = createNumber("supTotOccupata", Double.class);

    public final StringPath unitaProduttiva = createString("unitaProduttiva");

    public final DateTimePath<java.sql.Timestamp> updated = createDateTime("updated", java.sql.Timestamp.class);

    public final NumberPath<Integer> updatedBy = createNumber("updatedBy", Integer.class);

    public QSchedaUnoInfoAzienda(String variable) {
        super(SchedaUnoInfoAzienda.class, forVariable(variable));
    }

    public QSchedaUnoInfoAzienda(Path<? extends SchedaUnoInfoAzienda> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSchedaUnoInfoAzienda(PathMetadata metadata) {
        super(SchedaUnoInfoAzienda.class, metadata);
    }

}

