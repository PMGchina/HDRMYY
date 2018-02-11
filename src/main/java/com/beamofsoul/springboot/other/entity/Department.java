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
 * @ClassName Department
 * @Description 前置数据库中科室信息表对应的业务对象实体类
 * @author MingshuJian
 * @Date 2017年8月9日 下午1:16:09
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "T_DEPARTMENT")
public class Department extends BaseAbstractEntity {

	private static final long serialVersionUID = 1042620559425392778L;

	@Id
	@GeneratedValue
	@Column
	protected Long id;
	/**
	 * 科室编号
	 */
	@Column(name = "code", length = 20)
	private String code;
	/**
	 * 科室名称
	 */
	@Column(name = "name", length = 20)
	private String name;
	/**
	 * 科室图标base64
	 */
	@Column(name = "icon", columnDefinition = "text")
	private String icon;
	/**
	 * 是否可用
	 */
	@Column(name = "available")
	private Boolean available;
}
