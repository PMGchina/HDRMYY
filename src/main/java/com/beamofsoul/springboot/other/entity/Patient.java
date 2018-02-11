package com.beamofsoul.springboot.other.entity;

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
 * @ClassName Patient
 * @Description 前置数据库中患者信息表对应的业务对象实体类
 * @author MingshuJian
 * @Date 2017年8月9日 下午1:16:09
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "T_PATIENT")
public class Patient extends BaseAbstractEntity {

	private static final long serialVersionUID = -196818518398370026L;

	@Id
	@GeneratedValue
	@Column
	protected Long id;
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
	 * 患者出生年月
	 */
	@Column(name = "brth_date", length = 10)
	private String brthDate;
	/**
	 * 患者联系电话
	 */
	@Column(name = "phone_number", length = 20)
	private String phoneNumber;
	/**
	 * 患者就诊卡号码
	 */
	@Column(name = "medical_card", length = 20, unique = true)
	private String medicalCard;
	/**
	 * 患者就诊卡密码
	 */
	@Column(name = "password", length = 20)
	private String password;
	/**
	 * 一维码
	 */
	@Column(name = "barcode", columnDefinition = "text")
	private String barcode;
	/**
	 * 二维码
	 */
	@Column(name = "qrcode", columnDefinition = "text")
	private String qrcode;
	/**
	 * 患者就诊卡是否可用
	 */
	@Column(name = "available")
	private Boolean available;
}
