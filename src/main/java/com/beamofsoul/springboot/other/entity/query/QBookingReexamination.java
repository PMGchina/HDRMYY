package com.beamofsoul.springboot.other.entity.query;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;

import com.beamofsoul.springboot.entity.query.QBaseAbstractEntity;
import com.beamofsoul.springboot.other.entity.BookingReexamination;
import com.querydsl.core.types.Path;


/**
 * QBookingReexamination is a Querydsl query type for BookingReexamination
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBookingReexamination extends EntityPathBase<BookingReexamination> {

    private static final long serialVersionUID = 947817835L;

    public static final QBookingReexamination bookingReexamination = new QBookingReexamination("bookingReexamination");

    public final QBaseAbstractEntity _super = new QBaseAbstractEntity(this);

    public final ArrayPath<Long[], Long> chosenReexaminationItems = createArray("chosenReexaminationItems", Long[].class);

    //inherited
    public final DateTimePath<java.util.Date> createDate = _super.createDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath medicalCard = createString("medicalCard");
    
    public final BooleanPath available = createBoolean("available");
    
    public final BooleanPath process = createBoolean("process");

    //inherited
    public final DateTimePath<java.util.Date> modifyDate = _super.modifyDate;

    public final DateTimePath<java.util.Date> targetTime = createDateTime("targetTime", java.util.Date.class);

    public final NumberPath<Double> totalAmount = createNumber("totalAmount", Double.class);

    public QBookingReexamination(String variable) {
        super(BookingReexamination.class, forVariable(variable));
    }

    public QBookingReexamination(Path<? extends BookingReexamination> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBookingReexamination(PathMetadata metadata) {
        super(BookingReexamination.class, metadata);
    }

}

