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
 * @ClassName Doctor
 * @Description 前置数据库中医生信息表对应的业务对象实体类
 * @author MingshuJian
 * @Date 2017年8月9日 下午1:16:09
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "T_DOCTOR")
public class Doctor extends BaseAbstractEntity {

	private static final long serialVersionUID = 7275816136044570927L;
	@Id
	@GeneratedValue
	@Column
	protected Long id;
	/**
	 * 医生编号
	 */
	@Column(name = "code", length = 20)
	private String code;
	/**
	 * 医生名称
	 */
	@Column(name = "name", length = 20)
	private String name;
	/**
	 * 医生性别 - true:男性，false:女性
	 */
	@Column(name = "gender")
	private Boolean gender;
	/**
	 * 医生职称
	 */
	@Column(name = "title", length = 100)
	private String title;
	/**
	 * 医生照片base64
	 */
	@Column(name = "image", columnDefinition = "text")
	private String image;
	/**
	 * 是否可用
	 */
	@Column(name = "available")
	private Boolean available;
	/**
	 * 所在部门编号
	 */
	@Column(name = "department_code", length = 20)
	private String departmentCode;
	/**
	 * 是否专家
	 */
	@Column(name = "expert")
	private Boolean expert;
	/**
	 * 描述
	 */
	@Column(columnDefinition = "text")
	private String description;
}
