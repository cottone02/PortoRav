package it.rjcsoft.prv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUploadedDocument is a Querydsl query type for UploadedDocument
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUploadedDocument extends EntityPathBase<UploadedDocument> {

    private static final long serialVersionUID = 964274614L;

    public static final QUploadedDocument uploadedDocument = new QUploadedDocument("uploadedDocument");

    public final NumberPath<Integer> companyId = createNumber("companyId", Integer.class);

    public final StringPath description = createString("description");

    public final ListPath<UploadedDocumentFile, QUploadedDocumentFile> fileDocumenti = this.<UploadedDocumentFile, QUploadedDocumentFile>createList("fileDocumenti", UploadedDocumentFile.class, QUploadedDocumentFile.class, PathInits.DIRECT2);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> userId = createNumber("userId", Integer.class);

    public QUploadedDocument(String variable) {
        super(UploadedDocument.class, forVariable(variable));
    }

    public QUploadedDocument(Path<? extends UploadedDocument> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUploadedDocument(PathMetadata metadata) {
        super(UploadedDocument.class, metadata);
    }

}

