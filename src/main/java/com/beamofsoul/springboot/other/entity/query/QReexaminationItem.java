package com.beamofsoul.springboot.other.entity.query;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;

import com.beamofsoul.springboot.entity.query.QBaseAbstractEntity;
import com.beamofsoul.springboot.other.entity.ReexaminationItem;
import com.querydsl.core.types.Path;

/**
 * QReexaminationItem is a Querydsl query type for ReexaminationItem
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QReexaminationItem extends EntityPathBase<ReexaminationItem> {

	private static final long serialVersionUID = 638639211L;

	public static final QReexaminationItem reexaminationItem = new QReexaminationItem("reexaminationItem");

	public final QBaseAbstractEntity _super = new QBaseAbstractEntity(this);

	public final BooleanPath available = createBoolean("available");

	public final StringPath code = createString("code");

	//inherited
	public final DateTimePath<java.util.Date> createDate = _super.createDate;

	public final StringPath description = createString("description");

	public final NumberPath<Long> id = createNumber("id", Long.class);

	//inherited
	public final DateTimePath<java.util.Date> modifyDate = _super.modifyDate;

	public final StringPath name = createString("name");

	public final NumberPath<Double> price = createNumber("price", Double.class);

	public QReexaminationItem(String variable) {
		super(ReexaminationItem.class, forVariable(variable));
	}

	public QReexaminationItem(Path<? extends ReexaminationItem> path) {
		super(path.getType(), path.getMetadata());
	}

	public QReexaminationItem(PathMetadata metadata) {
		super(ReexaminationItem.class, metadata);
	}

}
