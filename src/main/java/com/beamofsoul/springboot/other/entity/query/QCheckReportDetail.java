package com.beamofsoul.springboot.other.entity.query;

import com.beamofsoul.springboot.entity.query.QBaseAbstractEntity;
import com.beamofsoul.springboot.other.entity.CheckReportDetail;
import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

/**
 * QCheckReportDetail is a Querydsl query type for CheckReportDetail
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCheckReportDetail extends EntityPathBase<CheckReportDetail> {

   private static final long serialVersionUID = -401136601L;

   public static final QCheckReportDetail checkReportDetail = new QCheckReportDetail("checkReportDetail");

   public final QBaseAbstractEntity _super = new QBaseAbstractEntity(this);

   public final StringPath checkResult = createString("checkResult");

   public final StringPath checkState = createString("checkState");

   public final StringPath chineseName = createString("chineseName");

   //inherited
   public final DateTimePath<java.util.Date> createDate = _super.createDate;

   public final StringPath englishName = createString("englishName");

   public final NumberPath<Long> id = createNumber("id", Long.class);

   //inherited
   public final DateTimePath<java.util.Date> modifyDate = _super.modifyDate;

   public final StringPath referenceRange = createString("referenceRange");

   public final StringPath unit = createString("unit");

   public QCheckReportDetail(String variable) {
      super(CheckReportDetail.class, forVariable(variable));
   }

   public QCheckReportDetail(Path<? extends CheckReportDetail> path) {
      super(path.getType(), path.getMetadata());
   }

   public QCheckReportDetail(PathMetadata metadata) {
      super(CheckReportDetail.class, metadata);
   }

}
