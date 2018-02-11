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
 * @ClassName BookingRegistration
 * @Description 前置数据库中预约挂号信息表对应的业务对象实体类
 * @author MingshuJian
 * @Date 2017年8月9日 下午1:16:09
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "T_BOOKING_REGISTRATION")
public class BookingRegistration extends BaseAbstractEntity {

	private static final long serialVersionUID = 2873279537806589433L;

	@Id
	@GeneratedValue
	@Column
	protected Long id;
	/**
	 * 预约挂号患者的就诊卡号
	 */
	@Column(name = "medical_card", length = 20)
	private String medicalCard;
	/**
	 * 预约挂号对应的部门编号
	 */
	@Column(name = "department_code", length = 20)
	private String departmentCode;
	/**
	 * 预约挂号对应的医生编号
	 */
	@Column(name = "doctor_code", length = 20)
	private String doctorCode;
	/**
	 * 预约哪天什么时间的号
	 */
	@Column(name = "target_time")
	private Date targetTime;

	private Boolean available;

	private Boolean process;
}
