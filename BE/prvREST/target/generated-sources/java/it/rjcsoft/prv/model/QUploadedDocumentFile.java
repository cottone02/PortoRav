package it.rjcsoft.prv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUploadedDocumentFile is a Querydsl query type for UploadedDocumentFile
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUploadedDocumentFile extends EntityPathBase<UploadedDocumentFile> {

    private static final long serialVersionUID = 748898386L;

    public static final QUploadedDocumentFile uploadedDocumentFile = new QUploadedDocumentFile("uploadedDocumentFile");

    public final StringPath fileName = createString("fileName");

    public final NumberPath<Integer> idDocument = createNumber("idDocument", Integer.class);

    public QUploadedDocumentFile(String variable) {
        super(UploadedDocumentFile.class, forVariable(variable));
    }

    public QUploadedDocumentFile(Path<? extends UploadedDocumentFile> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUploadedDocumentFile(PathMetadata metadata) {
        super(UploadedDocumentFile.class, metadata);
    }

}

