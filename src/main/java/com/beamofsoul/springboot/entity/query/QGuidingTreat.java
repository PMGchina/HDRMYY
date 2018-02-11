package com.beamofsoul.springboot.entity.query;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;

import com.beamofsoul.springboot.entity.GuidingTreat;
import com.querydsl.core.types.Path;


/**
 * QGuidingTreat is a Querydsl query type for GuidingTreat
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QGuidingTreat extends EntityPathBase<GuidingTreat> {

    private static final long serialVersionUID = 326090981L;

    public static final QGuidingTreat guidingTreat = new QGuidingTreat("guidingTreat");

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

    public QGuidingTreat(String variable) {
        super(GuidingTreat.class, forVariable(variable));
    }

    public QGuidingTreat(Path<? extends GuidingTreat> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGuidingTreat(PathMetadata metadata) {
        super(GuidingTreat.class, metadata);
    }

}

