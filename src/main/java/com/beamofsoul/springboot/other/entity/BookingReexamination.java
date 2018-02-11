package com.beamofsoul.springboot.other.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.beamofsoul.springboot.entity.BaseAbstractEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ClassName BookingReexamination
 * @Description 前置数据库中预约复查记录信息表对应的业务对象实体类
 * @author MingshuJian
 * @Date 2017年8月9日 下午1:16:09
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "T_BOOKING_REEXAMINATION")
public class BookingReexamination extends BaseAbstractEntity {
	
	private static final long serialVersionUID = 7883996512052974861L;
	
	@Id
	@GeneratedValue
    @Column
    protected Long id;
	
	@Column(name = "medical_card", length = 20)
	private String medicalCard;
	
	@Column(name = "total_amount")
	private Double totalAmount;
	
	@Column(name = "chosen_reexamination_items")
	private Long[] chosenReexaminationItems;
	
	@Column(name = "target_time")
	private Date targetTime;
	
	private Boolean available;
	
	private Boolean process;
}
