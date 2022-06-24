package it.rjcsoft.prv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCensimentoProdottiAdditionalInfo is a Querydsl query type for CensimentoProdottiAdditionalInfo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCensimentoProdottiAdditionalInfo extends EntityPathBase<CensimentoProdottiAdditionalInfo> {

    private static final long serialVersionUID = 88976944L;

    public static final QCensimentoProdottiAdditionalInfo censimentoProdottiAdditionalInfo = new QCensimentoProdottiAdditionalInfo("censimentoProdottiAdditionalInfo");

    public final BooleanPath canDelete = createBoolean("canDelete");

    public final NumberPath<Integer> schedaId = createNumber("schedaId", Integer.class);

    public QCensimentoProdottiAdditionalInfo(String variable) {
        super(CensimentoProdottiAdditionalInfo.class, forVariable(variable));
    }

    public QCensimentoProdottiAdditionalInfo(Path<? extends CensimentoProdottiAdditionalInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCensimentoProdottiAdditionalInfo(PathMetadata metadata) {
        super(CensimentoProdottiAdditionalInfo.class, metadata);
    }

}

