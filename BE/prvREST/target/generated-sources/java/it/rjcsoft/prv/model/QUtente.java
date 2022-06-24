package it.rjcsoft.prv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUtente is a Querydsl query type for Utente
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUtente extends EntityPathBase<Utente> {

    private static final long serialVersionUID = 59000788L;

    public static final QUtente utente = new QUtente("utente");

    public final NumberPath<Integer> companyId = createNumber("companyId", Integer.class);

    public final BooleanPath contactPerson = createBoolean("contactPerson");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final StringPath email = createString("email");

    public final DateTimePath<java.sql.Timestamp> expirationOtp = createDateTime("expirationOtp", java.sql.Timestamp.class);

    public final StringPath firstName = createString("firstName");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath lastName = createString("lastName");

    public final DateTimePath<java.util.Date> modifiedDate = createDateTime("modifiedDate", java.util.Date.class);

    public final StringPath otp = createString("otp");

    public final StringPath password = createString("password");

    public final StringPath roleValue = createString("roleValue");

    public final StringPath statusValue = createString("statusValue");

    public final StringPath username = createString("username");

    public QUtente(String variable) {
        super(Utente.class, forVariable(variable));
    }

    public QUtente(Path<? extends Utente> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUtente(PathMetadata metadata) {
        super(Utente.class, metadata);
    }

}

