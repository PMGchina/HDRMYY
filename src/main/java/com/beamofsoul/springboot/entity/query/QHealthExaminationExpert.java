package com.beamofsoul.springboot.entity.query;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;

import com.beamofsoul.springboot.entity.HealthExaminationExpert;
import com.querydsl.core.types.Path;


/**
 * QHealthExaminationExpert is a Querydsl query type for HealthExaminationExpert
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QHealthExaminationExpert extends EntityPathBase<HealthExaminationExpert> {

    private static final long serialVersionUID = 1635884539L;

    public static final QHealthExaminationExpert healthExaminationExpert = new QHealthExaminationExpert("healthExaminationExpert");

    public final QBaseAbstractEntity _super = new QBaseAbstractEntity(this);

    public final BooleanPath available = createBoolean("available");

    //inherited
    public final DateTimePath<java.util.Date> createDate = _super.createDate;

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    //inherited
    public final DateTimePath<java.util.Date> modifyDate = _super.modifyDate;

    public final StringPath name = createString("name");

    public final StringPath title = createString("title");

    public QHealthExaminationExpert(String variable) {
        super(HealthExaminationExpert.class, forVariable(variable));
    }

    public QHealthExaminationExpert(Path<? extends HealthExaminationExpert> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHealthExaminationExpert(PathMetadata metadata) {
        super(HealthExaminationExpert.class, metadata);
    }

}

