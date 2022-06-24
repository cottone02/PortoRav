package it.rjcsoft.prv.dto.censimentoazienda;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCensimentoAziendaAdditionalInfo is a Querydsl query type for CensimentoAziendaAdditionalInfo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCensimentoAziendaAdditionalInfo extends EntityPathBase<CensimentoAziendaAdditionalInfo> {

    private static final long serialVersionUID = -391263728L;

    public static final QCensimentoAziendaAdditionalInfo censimentoAziendaAdditionalInfo = new QCensimentoAziendaAdditionalInfo("censimentoAziendaAdditionalInfo");

    public final BooleanPath canDelete = createBoolean("canDelete");

    public final NumberPath<Integer> censimentoAziendaId = createNumber("censimentoAziendaId", Integer.class);

    public final ArrayPath<Integer[], Integer> componentCounter = createArray("componentCounter", Integer[].class);

    public QCensimentoAziendaAdditionalInfo(String variable) {
        super(CensimentoAziendaAdditionalInfo.class, forVariable(variable));
    }

    public QCensimentoAziendaAdditionalInfo(Path<? extends CensimentoAziendaAdditionalInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCensimentoAziendaAdditionalInfo(PathMetadata metadata) {
        super(CensimentoAziendaAdditionalInfo.class, metadata);
    }

}

