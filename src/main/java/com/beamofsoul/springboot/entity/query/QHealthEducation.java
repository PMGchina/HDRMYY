package com.beamofsoul.springboot.entity.query;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;

import com.beamofsoul.springboot.entity.HealthEducation;
import com.querydsl.core.types.Path;


/**
 * QHealthEducation is a Querydsl query type for HealthEducation
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QHealthEducation extends EntityPathBase<HealthEducation> {

    private static final long serialVersionUID = -206188504L;

    public static final QHealthEducation healthEducation = new QHealthEducation("healthEducation");

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

    public QHealthEducation(String variable) {
        super(HealthEducation.class, forVariable(variable));
    }

    public QHealthEducation(Path<? extends HealthEducation> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHealthEducation(PathMetadata metadata) {
        super(HealthEducation.class, metadata);
    }

}

