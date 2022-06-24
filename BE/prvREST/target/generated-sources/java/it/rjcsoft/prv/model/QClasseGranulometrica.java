package it.rjcsoft.prv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QClasseGranulometrica is a Querydsl query type for ClasseGranulometrica
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QClasseGranulometrica extends EntityPathBase<ClasseGranulometrica> {

    private static final long serialVersionUID = -286336711L;

    public static final QClasseGranulometrica classeGranulometrica = new QClasseGranulometrica("classeGranulometrica");

    public final StringPath classe = createString("classe");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath intervalloDimensionale = createString("intervalloDimensionale");

    public final StringPath scala = createString("scala");

    public QClasseGranulometrica(String variable) {
        super(ClasseGranulometrica.class, forVariable(variable));
    }

    public QClasseGranulometrica(Path<? extends ClasseGranulometrica> path) {
        super(path.getType(), path.getMetadata());
    }

    public QClasseGranulometrica(PathMetadata metadata) {
        super(ClasseGranulometrica.class, metadata);
    }

}

