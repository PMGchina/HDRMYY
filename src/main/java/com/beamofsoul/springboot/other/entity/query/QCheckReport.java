package com.beamofsoul.springboot.other.entity.query;

import com.beamofsoul.springboot.entity.query.QBaseAbstractEntity;
import com.beamofsoul.springboot.other.entity.CheckReport;
import com.beamofsoul.springboot.other.entity.CheckReportDetail;
import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

/**
 * QCheckReport is a Querydsl query type for CheckReport
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCheckReport extends EntityPathBase<CheckReport> {

   private static final long serialVersionUID = 1341104182L;

   public static final QCheckReport checkReport = new QCheckReport("checkReport");

   public final QBaseAbstractEntity _super = new QBaseAbstractEntity(this);

   public final StringPath age = createString("age");

   public final StringPath barcodeNo = createString("barcodeNo");

   public final SetPath<CheckReportDetail, QCheckReportDetail> checkReportDetail = this.<CheckReportDetail, QCheckReportDetail>createSet("checkReportDetail", CheckReportDetail.class, QCheckReportDetail.class, PathInits.DIRECT2);

   //inherited
   public final DateTimePath<java.util.Date> createDate = _super.createDate;

   public final StringPath departmentName = createString("departmentName");

   public final StringPath doctorName = createString("doctorName");

   public final NumberPath<Long> id = createNumber("id", Long.class);

   public final DateTimePath<java.util.Date> inspectionDate = createDateTime("inspectionDate", java.util.Date.class);

   public final StringPath medicalCard = createString("medicalCard");

   public final StringPath medicalRecordsNo = createString("medicalRecordsNo");

   //inherited
   public final DateTimePath<java.util.Date> modifyDate = _super.modifyDate;

   public final StringPath name = createString("name");

   public final StringPath remark = createString("remark");

   public final StringPath sex = createString("sex");

   public final StringPath specimenNo = createString("specimenNo");

   public final StringPath specimenType = createString("specimenType");

   public QCheckReport(String variable) {
      super(CheckReport.class, forVariable(variable));
   }

   public QCheckReport(Path<? extends CheckReport> path) {
      super(path.getType(), path.getMetadata());
   }

   public QCheckReport(PathMetadata metadata) {
      super(CheckReport.class, metadata);
   }

}
