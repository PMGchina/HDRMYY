package com.beamofsoul.springboot.entity.query;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;

import com.beamofsoul.springboot.entity.SeekingTreatStrategy;
import com.querydsl.core.types.Path;


/**
 * QSeekingTreatStrategy is a Querydsl query type for SeekingTreatStrategy
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSeekingTreatStrategy extends EntityPathBase<SeekingTreatStrategy> {

    private static final long serialVersionUID = -1164014233L;

    public static final QSeekingTreatStrategy seekingTreatStrategy = new QSeekingTreatStrategy("seekingTreatStrategy");

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

    public QSeekingTreatStrategy(String variable) {
        super(SeekingTreatStrategy.class, forVariable(variable));
    }

    public QSeekingTreatStrategy(Path<? extends SeekingTreatStrategy> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSeekingTreatStrategy(PathMetadata metadata) {
        super(SeekingTreatStrategy.class, metadata);
    }

}

