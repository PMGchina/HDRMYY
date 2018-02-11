package com.beamofsoul.springboot.other.entity;

import com.beamofsoul.springboot.entity.BaseAbstractEntity;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Description 经验报告单
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "T_CHECK_REPORT")
public class CheckReport extends BaseAbstractEntity {

   private static final long serialVersionUID = 0L;

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id")
   protected Long id;
   /**
    * 预约挂号患者的就诊卡号
    */
   @Column(name = "medical_card", length = 20)
   private String medicalCard;
   /**
    * 患者姓名
    */
   @Column(name = "name", length = 20)
   private String name;
   /**
    * 患者性别
    */
   @Column(length = 2)
   private String sex;
   /**
    * 患者年龄
    */
   @Column(length = 3)
   private String age;
   /**
    * 送检医生
    */
   @Column(name = "doctor_name", length = 20)
   private String doctorName;
   /**
    * 科别
    */
   @Column(name = "department_name", length = 20)
   private String departmentName;
   /**
    * 病历号
    */
   @Column(name = "medical_records_no", length = 20)
   private String medicalRecordsNo;
   /**
    * 条码号
    */
   @Column(name = "barcode_no", length = 20)
   private String barcodeNo;
   /**
    * 标本号
    */
   @Column(name = "specimen_no", length = 20)
   private String specimenNo;
   /**
    * 标本类型
    */
   @Column(name = "specimen_type", length = 20)
   private String specimenType;
   /**
    * 送检时间
    */
   @Column(name = "inspection_date")
   private Date inspectionDate;
   /**
    * 备注
    */
   @Column
   private String remark;

   @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
   @JoinColumn(name = "checkReport_id", referencedColumnName = "id")
   @OrderBy("id ASC")
   private Set<CheckReportDetail> checkReportDetail = new HashSet<>();
}
