package com.beamofsoul.springboot.other.entity.query;

import com.beamofsoul.springboot.entity.QBaseAbstractEntity;
import com.beamofsoul.springboot.other.entity.CheckReportRequest;
import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

/**
 * QCheckReportRequest is a Querydsl query type for CheckReportRequest
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCheckReportRequest extends EntityPathBase<CheckReportRequest> {

   private static final long serialVersionUID = -12361351L;

   public static final QCheckReportRequest checkReportRequest = new QCheckReportRequest("checkReportRequest");

   public final QBaseAbstractEntity _super = new QBaseAbstractEntity(this);

   //inherited
   public final DateTimePath<java.util.Date> createDate = _super.createDate;

   public final NumberPath<Long> id = createNumber("id", Long.class);

   public final DateTimePath<java.util.Date> intoDate = createDateTime("intoDate", java.util.Date.class);

   public final BooleanPath isInto = createBoolean("isInto");

   public final StringPath medicalCard = createString("medicalCard");

   //inherited
   public final DateTimePath<java.util.Date> modifyDate = _super.modifyDate;

   public final DateTimePath<java.util.Date> requestDate = createDateTime("requestDate", java.util.Date.class);

   public QCheckReportRequest(String variable) {
      super(CheckReportRequest.class, forVariable(variable));
   }

   public QCheckReportRequest(Path<? extends CheckReportRequest> path) {
      super(path.getType(), path.getMetadata());
   }

   public QCheckReportRequest(PathMetadata metadata) {
      super(CheckReportRequest.class, metadata);
   }

}
