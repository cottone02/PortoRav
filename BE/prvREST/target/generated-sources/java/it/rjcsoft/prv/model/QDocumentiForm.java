package it.rjcsoft.prv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDocumentiForm is a Querydsl query type for DocumentiForm
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDocumentiForm extends EntityPathBase<DocumentiForm> {

    private static final long serialVersionUID = 970835031L;

    public static final QDocumentiForm documentiForm = new QDocumentiForm("documentiForm");

    public final StringPath description = createString("description");

    public final StringPath fileName = createString("fileName");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public QDocumentiForm(String variable) {
        super(DocumentiForm.class, forVariable(variable));
    }

    public QDocumentiForm(Path<? extends DocumentiForm> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDocumentiForm(PathMetadata metadata) {
        super(DocumentiForm.class, metadata);
    }

}

