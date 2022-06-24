package it.rjcsoft.prv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QHtmlMail is a Querydsl query type for HtmlMail
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QHtmlMail extends EntityPathBase<HtmlMail> {

    private static final long serialVersionUID = -90491619L;

    public static final QHtmlMail htmlMail = new QHtmlMail("htmlMail");

    public final StringPath body = createString("body");

    public final BooleanPath flagReferenti = createBoolean("flagReferenti");

    public final StringPath htmlMailId = createString("htmlMailId");

    public final StringPath lingua = createString("lingua");

    public final StringPath oggetto = createString("oggetto");

    public QHtmlMail(String variable) {
        super(HtmlMail.class, forVariable(variable));
    }

    public QHtmlMail(Path<? extends HtmlMail> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHtmlMail(PathMetadata metadata) {
        super(HtmlMail.class, metadata);
    }

}

