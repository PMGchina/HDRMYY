package com.beamofsoul.springboot.other.entity.query;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;

import com.beamofsoul.springboot.entity.query.QBaseAbstractEntity;
import com.beamofsoul.springboot.other.entity.HealthExaminationPackage;
import com.querydsl.core.types.Path;


/**
 * QHealthExaminationPackage is a Querydsl query type for HealthExaminationPackage
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QHealthExaminationPackage extends EntityPathBase<HealthExaminationPackage> {

    private static final long serialVersionUID = -324895083L;

    public static final QHealthExaminationPackage healthExaminationPackage = new QHealthExaminationPackage("healthExaminationPackage");

    public final QBaseAbstractEntity _super = new QBaseAbstractEntity(this);

    public final NumberPath<Double> amount = createNumber("amount", Double.class);

    //inherited
    public final DateTimePath<java.util.Date> createDate = _super.createDate;
    
    public final BooleanPath available = createBoolean("available");

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ArrayPath<Long[], Long> items = createArray("items", Long[].class);

    //inherited
    public final DateTimePath<java.util.Date> modifyDate = _super.modifyDate;

    public final StringPath name = createString("name");

    public QHealthExaminationPackage(String variable) {
        super(HealthExaminationPackage.class, forVariable(variable));
    }

    public QHealthExaminationPackage(Path<? extends HealthExaminationPackage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHealthExaminationPackage(PathMetadata metadata) {
        super(HealthExaminationPackage.class, metadata);
    }

}

