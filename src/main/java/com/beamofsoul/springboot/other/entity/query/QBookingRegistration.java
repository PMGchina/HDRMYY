package com.beamofsoul.springboot.other.entity.query;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;

import com.beamofsoul.springboot.entity.query.QBaseAbstractEntity;
import com.beamofsoul.springboot.other.entity.BookingRegistration;
import com.querydsl.core.types.Path;


/**
 * QBookingRegistration is a Querydsl query type for BookingRegistration
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBookingRegistration extends EntityPathBase<BookingRegistration> {

    private static final long serialVersionUID = 1955294604L;

    public static final QBookingRegistration bookingRegistration = new QBookingRegistration("bookingRegistration");

    public final QBaseAbstractEntity _super = new QBaseAbstractEntity(this);

    //inherited
    public final DateTimePath<java.util.Date> createDate = _super.createDate;

    public final StringPath departmentCode = createString("departmentCode");

    public final StringPath doctorCode = createString("doctorCode");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath medicalCard = createString("medicalCard");
    
    public final BooleanPath available = createBoolean("available");
    
    public final BooleanPath process = createBoolean("process");

    //inherited
    public final DateTimePath<java.util.Date> modifyDate = _super.modifyDate;

    public final DateTimePath<java.util.Date> targetTime = createDateTime("targetTime", java.util.Date.class);

    public QBookingRegistration(String variable) {
        super(BookingRegistration.class, forVariable(variable));
    }

    public QBookingRegistration(Path<? extends BookingRegistration> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBookingRegistration(PathMetadata metadata) {
        super(BookingRegistration.class, metadata);
    }

}

