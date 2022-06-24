package it.rjcsoft.prv.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QFileMonitoraggio is a Querydsl query type for FileMonitoraggio
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFileMonitoraggio extends EntityPathBase<FileMonitoraggio> {

    private static final long serialVersionUID = 375519876L;

    public static final QFileMonitoraggio fileMonitoraggio = new QFileMonitoraggio("fileMonitoraggio");

    public final StringPath fileName = createString("fileName");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> idMonitoring = createNumber("idMonitoring", Integer.class);

    public final BooleanPath processed = createBoolean("processed");

    public QFileMonitoraggio(String variable) {
        super(FileMonitoraggio.class, forVariable(variable));
    }

    public QFileMonitoraggio(Path<? extends FileMonitoraggio> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFileMonitoraggio(PathMetadata metadata) {
        super(FileMonitoraggio.class, metadata);
    }

}

