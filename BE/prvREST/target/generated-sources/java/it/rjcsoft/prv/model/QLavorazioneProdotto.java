package it.rjcsoft.prv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLavorazioneProdotto is a Querydsl query type for LavorazioneProdotto
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLavorazioneProdotto extends EntityPathBase<LavorazioneProdotto> {

    private static final long serialVersionUID = 829819510L;

    public static final QLavorazioneProdotto lavorazioneProdotto = new QLavorazioneProdotto("lavorazioneProdotto");

    public final StringPath aziendaTerza = createString("aziendaTerza");

    public final NumberPath<Integer> banchina = createNumber("banchina", Integer.class);

    public final BooleanPath contoTerzi = createBoolean("contoTerzi");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final BooleanPath inProprio = createBoolean("inProprio");

    public final ListPath<LavorazioneProdottoDettagli, QLavorazioneProdottoDettagli> listalavorazioneProdottoDettagli = this.<LavorazioneProdottoDettagli, QLavorazioneProdottoDettagli>createList("listalavorazioneProdottoDettagli", LavorazioneProdottoDettagli.class, QLavorazioneProdottoDettagli.class, PathInits.DIRECT2);

    public final NumberPath<Double> movViaFerrovia = createNumber("movViaFerrovia", Double.class);

    public final NumberPath<Double> movViaNave = createNumber("movViaNave", Double.class);

    public final NumberPath<Double> movViaTerra = createNumber("movViaTerra", Double.class);

    public final NumberPath<Integer> schedaId = createNumber("schedaId", Integer.class);

    public QLavorazioneProdotto(String variable) {
        super(LavorazioneProdotto.class, forVariable(variable));
    }

    public QLavorazioneProdotto(Path<? extends LavorazioneProdotto> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLavorazioneProdotto(PathMetadata metadata) {
        super(LavorazioneProdotto.class, metadata);
    }

}

