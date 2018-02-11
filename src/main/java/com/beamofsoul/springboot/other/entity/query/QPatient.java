package com.beamofsoul.springboot.other.entity.query;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;

import com.beamofsoul.springboot.entity.query.QBaseAbstractEntity;
import com.beamofsoul.springboot.other.entity.Patient;
import com.querydsl.core.types.Path;

/**
 * QPatient is a Querydsl query type for Patient
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPatient extends EntityPathBase<Patient> {

	private static final long serialVersionUID = 1678395359L;

	public static final QPatient patient = new QPatient("patient");

	public final QBaseAbstractEntity _super = new QBaseAbstractEntity(this);

	public final BooleanPath available = createBoolean("available");

	public final StringPath barcode = createString("barcode");

	public final StringPath brthDate = createString("brthDate");

	//inherited
	public final DateTimePath<java.util.Date> createDate = _super.createDate;

	public final NumberPath<Long> id = createNumber("id", Long.class);

	public final StringPath medicalCard = createString("medicalCard");

	//inherited
	public final DateTimePath<java.util.Date> modifyDate = _super.modifyDate;

	public final StringPath name = createString("name");

	public final StringPath password = createString("password");

	public final StringPath phoneNumber = createString("phoneNumber");

	public final StringPath qrcode = createString("qrcode");

	public final StringPath sex = createString("sex");

	public QPatient(String variable) {
		super(Patient.class, forVariable(variable));
	}

	public QPatient(Path<? extends Patient> path) {
		super(path.getType(), path.getMetadata());
	}

	public QPatient(PathMetadata metadata) {
		super(Patient.class, metadata);
	}
}
