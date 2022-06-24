package it.rjcsoft.prv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCensimentoAzienda is a Querydsl query type for CensimentoAzienda
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCensimentoAzienda extends EntityPathBase<CensimentoAzienda> {

    private static final long serialVersionUID = -428236788L;

    public static final QCensimentoAzienda censimentoAzienda = new QCensimentoAzienda("censimentoAzienda");

    public final StringPath anno = createString("anno");

    public final BooleanPath autorizzazioneEmissioniConvogliate = createBoolean("autorizzazioneEmissioniConvogliate");

    public final StringPath autorizzazioneEmissioniConvogliateDesc = createString("autorizzazioneEmissioniConvogliateDesc");

    public final StringPath codIstatAttivita = createString("codIstatAttivita");

    public final NumberPath<Integer> companyId = createNumber("companyId", Integer.class);

    public final NumberPath<Float> consumiIdriciAnnui = createNumber("consumiIdriciAnnui", Float.class);

    public final DateTimePath<java.sql.Timestamp> created = createDateTime("created", java.sql.Timestamp.class);

    public final NumberPath<Integer> createdBy = createNumber("createdBy", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath indirizzoImpianto = createString("indirizzoImpianto");

    public final ComparablePath<org.locationtech.jts.geom.Polygon> indirizzoImpiantoGeo = createComparable("indirizzoImpiantoGeo", org.locationtech.jts.geom.Polygon.class);

    public final NumberPath<Integer> numDipendenti = createNumber("numDipendenti", Integer.class);

    public final NumberPath<Float> rifiutiTotaliProdotti = createNumber("rifiutiTotaliProdotti", Float.class);

    public final NumberPath<Float> rifiutiTotaliProdottiPericolosi = createNumber("rifiutiTotaliProdottiPericolosi", Float.class);

    public final NumberPath<Float> scarichiIdriciAnnui = createNumber("scarichiIdriciAnnui", Float.class);

    public final StringPath sistTrattamentoAcqueDesc = createString("sistTrattamentoAcqueDesc");

    public final BooleanPath sistTrattamentoAcqueMeteoriche = createBoolean("sistTrattamentoAcqueMeteoriche");

    public final NumberPath<Float> superficieTotale = createNumber("superficieTotale", Float.class);

    public final NumberPath<Float> superficieTotaleOccupata = createNumber("superficieTotaleOccupata", Float.class);

    public final StringPath unitaProduttiva = createString("unitaProduttiva");

    public final DateTimePath<java.sql.Timestamp> updated = createDateTime("updated", java.sql.Timestamp.class);

    public final NumberPath<Integer> updatedBy = createNumber("updatedBy", Integer.class);

    public QCensimentoAzienda(String variable) {
        super(CensimentoAzienda.class, forVariable(variable));
    }

    public QCensimentoAzienda(Path<? extends CensimentoAzienda> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCensimentoAzienda(PathMetadata metadata) {
        super(CensimentoAzienda.class, metadata);
    }

}

