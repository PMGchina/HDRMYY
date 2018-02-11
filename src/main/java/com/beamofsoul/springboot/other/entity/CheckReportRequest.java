package com.beamofsoul.springboot.other.entity;

import com.beamofsoul.springboot.entity.BaseAbstractEntity;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Description 经验报告请求
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "T_CHECK_REPORT_REQUEST")
public class CheckReportRequest extends BaseAbstractEntity {

   private static final long serialVersionUID = 0L;

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id")
   protected Long id;

   /**
    * 预约挂号患者的就诊卡号
    */
   @Column(name = "medical_card", length = 20, unique = true)
   private String medicalCard;
   /**
    * 请求时间
    */
   @Column(name = "request_date")
   private Date requestDate;
   /**
    * 是否从HIS调入到前置检验报告单里
    */
   @Column(name = "is_into", columnDefinition = "bit DEFAULT 0")
   private Boolean isInto;
   /**
    * 调入时间
    */
   @Column(name = "into_date")
   private Date intoDate;
}
