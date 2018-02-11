package com.beamofsoul.springboot.entity.query;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;

import com.beamofsoul.springboot.entity.HospitalEvent;
import com.querydsl.core.types.Path;


/**
 * QHospitalEvent is a Querydsl query type for HospitalEvent
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QHospitalEvent extends EntityPathBase<HospitalEvent> {

    private static final long serialVersionUID = -1213895940L;

    public static final QHospitalEvent hospitalEvent = new QHospitalEvent("hospitalEvent");

    public final QBaseAbstractEntity _super = new QBaseAbstractEntity(this);

    public final BooleanPath available = createBoolean("available");

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.util.Date> createDate = _super.createDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    //inherited
    public final DateTimePath<java.util.Date> modifyDate = _super.modifyDate;

    public final StringPath title = createString("title");

    public QHospitalEvent(String variable) {
        super(HospitalEvent.class, forVariable(variable));
    }

    public QHospitalEvent(Path<? extends HospitalEvent> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHospitalEvent(PathMetadata metadata) {
        super(HospitalEvent.class, metadata);
    }

}

