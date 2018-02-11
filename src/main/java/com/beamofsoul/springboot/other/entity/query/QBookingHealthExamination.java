package com.beamofsoul.springboot.other.entity.query;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;

import com.beamofsoul.springboot.entity.query.QBaseAbstractEntity;
import com.beamofsoul.springboot.other.entity.BookingHealthExamination;
import com.querydsl.core.types.Path;


/**
 * QBookingHealthExamination is a Querydsl query type for BookingHealthExamination
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBookingHealthExamination extends EntityPathBase<BookingHealthExamination> {

    private static final long serialVersionUID = -1936014176L;

    public static final QBookingHealthExamination bookingHealthExamination = new QBookingHealthExamination("bookingHealthExamination");

    public final QBaseAbstractEntity _super = new QBaseAbstractEntity(this);

    public final DateTimePath<java.util.Date> birthday = createDateTime("birthday", java.util.Date.class);

    public final ArrayPath<Long[], Long> chosenExaminationItems = createArray("chosenExaminationItems", Long[].class);

    public final ArrayPath<Long[], Long> chosenExaminationPackages = createArray("chosenExaminationPackages", Long[].class);

    //inherited
    public final DateTimePath<java.util.Date> createDate = _super.createDate;

    public final BooleanPath gender = createBoolean("gender");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath identity = createString("identity");
    
    public final BooleanPath available = createBoolean("available");
    
    public final BooleanPath process = createBoolean("process");

    //inherited
    public final DateTimePath<java.util.Date> modifyDate = _super.modifyDate;

    public final StringPath name = createString("name");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final DateTimePath<java.util.Date> targetTime = createDateTime("targetTime", java.util.Date.class);

    public final NumberPath<Double> totalAmount = createNumber("totalAmount", Double.class);

    public QBookingHealthExamination(String variable) {
        super(BookingHealthExamination.class, forVariable(variable));
    }

    public QBookingHealthExamination(Path<? extends BookingHealthExamination> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBookingHealthExamination(PathMetadata metadata) {
        super(BookingHealthExamination.class, metadata);
    }

}

