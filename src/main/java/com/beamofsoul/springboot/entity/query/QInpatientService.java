package com.beamofsoul.springboot.entity.query;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;

import com.beamofsoul.springboot.entity.InpatientService;
import com.querydsl.core.types.Path;


/**
 * QInpatientService is a Querydsl query type for InpatientService
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QInpatientService extends EntityPathBase<InpatientService> {

    private static final long serialVersionUID = -1025398695L;

    public static final QInpatientService inpatientService = new QInpatientService("inpatientService");

    public final QBaseAbstractEntity _super = new QBaseAbstractEntity(this);

    public final BooleanPath available = createBoolean("available");

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.util.Date> createDate = _super.createDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final StringPath item = createString("item");

    //inherited
    public final DateTimePath<java.util.Date> modifyDate = _super.modifyDate;

    public QInpatientService(String variable) {
        super(InpatientService.class, forVariable(variable));
    }

    public QInpatientService(Path<? extends InpatientService> path) {
        super(path.getType(), path.getMetadata());
    }

    public QInpatientService(PathMetadata metadata) {
        super(InpatientService.class, metadata);
    }

}

