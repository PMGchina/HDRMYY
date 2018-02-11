package com.beamofsoul.springboot.other.entity.query;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;

import com.beamofsoul.springboot.entity.query.QBaseAbstractEntity;
import com.beamofsoul.springboot.other.entity.Doctor;
import com.querydsl.core.types.Path;


/**
 * QDoctor is a Querydsl query type for Doctor
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDoctor extends EntityPathBase<Doctor> {

    private static final long serialVersionUID = -1385352955L;

    public static final QDoctor doctor = new QDoctor("doctor");

    public final QBaseAbstractEntity _super = new QBaseAbstractEntity(this);

    public final BooleanPath available = createBoolean("available");
    
    public final StringPath code = createString("code");

    //inherited
    public final DateTimePath<java.util.Date> createDate = _super.createDate;

    public final StringPath departmentCode = createString("departmentCode");

    public final BooleanPath expert = createBoolean("expert");

    public final BooleanPath gender = createBoolean("gender");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath image = createString("image");

    //inherited
    public final DateTimePath<java.util.Date> modifyDate = _super.modifyDate;

    public final StringPath name = createString("name");

    public final StringPath title = createString("title");
    
    public final StringPath description = createString("description");

    public QDoctor(String variable) {
        super(Doctor.class, forVariable(variable));
    }

    public QDoctor(Path<? extends Doctor> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDoctor(PathMetadata metadata) {
        super(Doctor.class, metadata);
    }

}

