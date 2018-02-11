package com.beamofsoul.springboot.other.entity.query;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;

import com.beamofsoul.springboot.entity.query.QBaseAbstractEntity;
import com.beamofsoul.springboot.other.entity.Scheduling;
import com.querydsl.core.types.Path;


/**
 * QScheduling is a Querydsl query type for Scheduling
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QScheduling extends EntityPathBase<Scheduling> {

    private static final long serialVersionUID = 441164410L;

    public static final QScheduling scheduling = new QScheduling("scheduling");

    public final QBaseAbstractEntity _super = new QBaseAbstractEntity(this);

    public final BooleanPath available = createBoolean("available");

    //inherited
    public final DateTimePath<java.util.Date> createDate = _super.createDate;

    public final StringPath doctorCode = createString("doctorCode");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.util.Date> modifyDate = _super.modifyDate;

    public final DateTimePath<java.util.Date> workday = createDateTime("workday", java.util.Date.class);

    public QScheduling(String variable) {
        super(Scheduling.class, forVariable(variable));
    }

    public QScheduling(Path<? extends Scheduling> path) {
        super(path.getType(), path.getMetadata());
    }

    public QScheduling(PathMetadata metadata) {
        super(Scheduling.class, metadata);
    }

}

