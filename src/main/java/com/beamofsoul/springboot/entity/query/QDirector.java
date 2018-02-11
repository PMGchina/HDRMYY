package com.beamofsoul.springboot.entity.query;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;

import com.beamofsoul.springboot.entity.Director;
import com.querydsl.core.types.Path;


/**
 * QDirector is a Querydsl query type for Director
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDirector extends EntityPathBase<Director> {

    private static final long serialVersionUID = -1613847664L;

    public static final QDirector director = new QDirector("director");

    public final QBaseAbstractEntity _super = new QBaseAbstractEntity(this);

    public final BooleanPath available = createBoolean("available");

    //inherited
    public final DateTimePath<java.util.Date> createDate = _super.createDate;

    public final StringPath departmentCode = createString("departmentCode");

    public final StringPath description = createString("description");

    public final StringPath doctorCode = createString("doctorCode");

    public final BooleanPath gender = createBoolean("gender");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    //inherited
    public final DateTimePath<java.util.Date> modifyDate = _super.modifyDate;

    public final StringPath name = createString("name");

    public final StringPath title = createString("title");

    public QDirector(String variable) {
        super(Director.class, forVariable(variable));
    }

    public QDirector(Path<? extends Director> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDirector(PathMetadata metadata) {
        super(Director.class, metadata);
    }

}

