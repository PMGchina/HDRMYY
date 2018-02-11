package com.beamofsoul.springboot.entity.query;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;

import com.beamofsoul.springboot.entity.DepartmentConstruction;
import com.querydsl.core.types.Path;


/**
 * QDepartmentConstruction is a Querydsl query type for DepartmentConstruction
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDepartmentConstruction extends EntityPathBase<DepartmentConstruction> {

    private static final long serialVersionUID = -875350457L;

    public static final QDepartmentConstruction departmentConstruction = new QDepartmentConstruction("departmentConstruction");

    public final QBaseAbstractEntity _super = new QBaseAbstractEntity(this);

    public final BooleanPath available = createBoolean("available");

    //inherited
    public final DateTimePath<java.util.Date> createDate = _super.createDate;

    public final StringPath departmentCode = createString("departmentCode");

    public final StringPath description = createString("description");

    public final StringPath iconUrl = createString("iconUrl");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.util.Date> modifyDate = _super.modifyDate;

    public final StringPath name = createString("name");

    public QDepartmentConstruction(String variable) {
        super(DepartmentConstruction.class, forVariable(variable));
    }

    public QDepartmentConstruction(Path<? extends DepartmentConstruction> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDepartmentConstruction(PathMetadata metadata) {
        super(DepartmentConstruction.class, metadata);
    }

}

