package it.rjcsoft.prv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCompanyEntity is a Querydsl query type for CompanyEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCompanyEntity extends EntityPathBase<CompanyEntity> {

    private static final long serialVersionUID = 10220165L;

    public static final QCompanyEntity companyEntity = new QCompanyEntity("companyEntity");

    public final StringPath address = createString("address");

    public final StringPath businessName = createString("businessName");

    public final StringPath city = createString("city");

    public final ListPath<UserEntity, QUserEntity> dipendenti = this.<UserEntity, QUserEntity>createList("dipendenti", UserEntity.class, QUserEntity.class, PathInits.DIRECT2);

    public final NumberPath<Integer> Id = createNumber("Id", Integer.class);

    public final StringPath name = createString("name");

    public final StringPath vatNumber = createString("vatNumber");

    public QCompanyEntity(String variable) {
        super(CompanyEntity.class, forVariable(variable));
    }

    public QCompanyEntity(Path<? extends CompanyEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCompanyEntity(PathMetadata metadata) {
        super(CompanyEntity.class, metadata);
    }

}

