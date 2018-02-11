package com.beamofsoul.springboot.other.entity.query;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;

import com.beamofsoul.springboot.entity.query.QBaseAbstractEntity;
import com.beamofsoul.springboot.other.entity.HealthExaminationItem;
import com.querydsl.core.types.Path;


/**
 * QHealthExaminationItem is a Querydsl query type for HealthExaminationItem
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QHealthExaminationItem extends EntityPathBase<HealthExaminationItem> {

    private static final long serialVersionUID = 1239804708L;

    public static final QHealthExaminationItem healthExaminationItem = new QHealthExaminationItem("healthExaminationItem");

    public final QBaseAbstractEntity _super = new QBaseAbstractEntity(this);

    //inherited
    public final DateTimePath<java.util.Date> createDate = _super.createDate;
    
    public final BooleanPath available = createBoolean("available");

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.util.Date> modifyDate = _super.modifyDate;

    public final StringPath name = createString("name");

    public final NumberPath<Double> price = createNumber("price", Double.class);

    public QHealthExaminationItem(String variable) {
        super(HealthExaminationItem.class, forVariable(variable));
    }

    public QHealthExaminationItem(Path<? extends HealthExaminationItem> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHealthExaminationItem(PathMetadata metadata) {
        super(HealthExaminationItem.class, metadata);
    }

}

