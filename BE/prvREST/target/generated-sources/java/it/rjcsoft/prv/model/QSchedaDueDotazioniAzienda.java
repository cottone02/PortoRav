package it.rjcsoft.prv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSchedaDueDotazioniAzienda is a Querydsl query type for SchedaDueDotazioniAzienda
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSchedaDueDotazioniAzienda extends EntityPathBase<SchedaDueDotazioniAzienda> {

    private static final long serialVersionUID = 1936446082L;

    public static final QSchedaDueDotazioniAzienda schedaDueDotazioniAzienda = new QSchedaDueDotazioniAzienda("schedaDueDotazioniAzienda");

    public final StringPath anno = createString("anno");

    public final NumberPath<Integer> aspiratore = createNumber("aspiratore", Integer.class);

    public final NumberPath<Integer> autocisterne = createNumber("autocisterne", Integer.class);

    public final BooleanPath autocisterneStradeInterne = createBoolean("autocisterneStradeInterne");

    public final BooleanPath barriereFisseContenimento = createBoolean("barriereFisseContenimento");

    public final BooleanPath copertureCamionProdottiSfusi = createBoolean("copertureCamionProdottiSfusi");

    public final DateTimePath<java.sql.Timestamp> created = createDateTime("created", java.sql.Timestamp.class);

    public final NumberPath<Integer> createdBy = createNumber("createdBy", Integer.class);

    public final NumberPath<Integer> depositiApertoAltro = createNumber("depositiApertoAltro", Integer.class);

    public final StringPath depositiApertoAltroDesc = createString("depositiApertoAltroDesc");

    public final NumberPath<Integer> depositiApertoConfezionamento = createNumber("depositiApertoConfezionamento", Integer.class);

    public final NumberPath<Integer> depositiApertoMacinazione = createNumber("depositiApertoMacinazione", Integer.class);

    public final NumberPath<Integer> depositiChiusiAltro = createNumber("depositiChiusiAltro", Integer.class);

    public final StringPath depositiChiusiAltroDesc = createString("depositiChiusiAltroDesc");

    public final NumberPath<Integer> depositiChiusiConfezionamento = createNumber("depositiChiusiConfezionamento", Integer.class);

    public final NumberPath<Integer> depositiChiusiMacinazione = createNumber("depositiChiusiMacinazione", Integer.class);

    public final NumberPath<Integer> gruFisseBenna = createNumber("gruFisseBenna", Integer.class);

    public final NumberPath<Integer> gruFisseBennaGomma = createNumber("gruFisseBennaGomma", Integer.class);

    public final NumberPath<Integer> gruMobiliBenna = createNumber("gruMobiliBenna", Integer.class);

    public final NumberPath<Integer> gruMobiliBennaGomma = createNumber("gruMobiliBennaGomma", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final BooleanPath impiantiIrrigazioneCumuli = createBoolean("impiantiIrrigazioneCumuli");

    public final BooleanPath impiantiIrrigazioneStrade = createBoolean("impiantiIrrigazioneStrade");

    public final BooleanPath lavaggioRuoteCamion = createBoolean("lavaggioRuoteCamion");

    public final NumberPath<Integer> muletti = createNumber("muletti", Integer.class);

    public final NumberPath<Integer> nastriTrasportatori = createNumber("nastriTrasportatori", Integer.class);

    public final NumberPath<Integer> numScheda = createNumber("numScheda", Integer.class);

    public final NumberPath<Integer> paleGommate = createNumber("paleGommate", Integer.class);

    public final BooleanPath percorsiSeparatiCamionSistemi = createBoolean("percorsiSeparatiCamionSistemi");

    public final BooleanPath presenzaOperatoreVerifica = createBoolean("presenzaOperatoreVerifica");

    public final BooleanPath presenzaProceduraMovimentazione = createBoolean("presenzaProceduraMovimentazione");

    public final BooleanPath puliziaCamion = createBoolean("puliziaCamion");

    public final NumberPath<Integer> reedler = createNumber("reedler", Integer.class);

    public final NumberPath<Integer> spazzatrici = createNumber("spazzatrici", Integer.class);

    public final NumberPath<Integer> tramoggiaAltro = createNumber("tramoggiaAltro", Integer.class);

    public final StringPath tramoggiaAltroDesc = createString("tramoggiaAltroDesc");

    public final NumberPath<Integer> tramoggiaAspirata = createNumber("tramoggiaAspirata", Integer.class);

    public final NumberPath<Integer> tramoggiaSemplice = createNumber("tramoggiaSemplice", Integer.class);

    public final NumberPath<Integer> trasportoPneumatico = createNumber("trasportoPneumatico", Integer.class);

    public final DateTimePath<java.sql.Timestamp> updated = createDateTime("updated", java.sql.Timestamp.class);

    public final NumberPath<Integer> updatedBy = createNumber("updatedBy", Integer.class);

    public final BooleanPath utilizzoProdottiAggreganti = createBoolean("utilizzoProdottiAggreganti");

    public QSchedaDueDotazioniAzienda(String variable) {
        super(SchedaDueDotazioniAzienda.class, forVariable(variable));
    }

    public QSchedaDueDotazioniAzienda(Path<? extends SchedaDueDotazioniAzienda> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSchedaDueDotazioniAzienda(PathMetadata metadata) {
        super(SchedaDueDotazioniAzienda.class, metadata);
    }

}

