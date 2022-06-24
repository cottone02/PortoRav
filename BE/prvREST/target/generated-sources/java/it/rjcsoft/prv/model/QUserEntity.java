package it.rjcsoft.prv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserEntity is a Querydsl query type for UserEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserEntity extends EntityPathBase<UserEntity> {

    private static final long serialVersionUID = -2054545943L;

    public static final QUserEntity userEntity = new QUserEntity("userEntity");

    public final NumberPath<Integer> companyId = createNumber("companyId", Integer.class);

    public final BooleanPath contactPerson = createBoolean("contactPerson");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final StringPath email = createString("email");

    public final DateTimePath<java.util.Date> expirationOtp = createDateTime("expirationOtp", java.util.Date.class);

    public final StringPath firstName = createString("firstName");

    public final NumberPath<Integer> Id = createNumber("Id", Integer.class);

    public final StringPath lastName = createString("lastName");

    public final DateTimePath<java.util.Date> modifiedDate = createDateTime("modifiedDate", java.util.Date.class);

    public final StringPath otp = createString("otp");

    public final StringPath password = createString("password");

    public final StringPath roleValue = createString("roleValue");

    public final StringPath statusValue = createString("statusValue");

    public final StringPath username = createString("username");

    public QUserEntity(String variable) {
        super(UserEntity.class, forVariable(variable));
    }

    public QUserEntity(Path<? extends UserEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserEntity(PathMetadata metadata) {
        super(UserEntity.class, metadata);
    }

}

