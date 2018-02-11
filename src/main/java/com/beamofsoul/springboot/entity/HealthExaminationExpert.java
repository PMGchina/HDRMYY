package com.beamofsoul.springboot.entity;

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
 * @ClassName HealthExaminationExpert
 * @Description 体检专家组成员实体类
 * @author MingshuJian
 * @Date 2017年7月25日 下午1:51:59
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "T_HEALTH_EXAMINATION_EXPERT")
public class HealthExaminationExpert extends BaseAbstractEntity  {

	private static final long serialVersionUID = -6144867378754136446L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	protected Long id;
	/**
	 * 姓名
	 */
	@Column(length = 100)
	private String name;
	/**
	 * 职称
	 */
	@Column(length = 100)
	private String title;
	/**
	 * 头像照片
	 */
	@Column(name = "image_url", length = 256)
	private String imageUrl;
	/**
	 * 描述
	 */
	@Column(columnDefinition = "text default null")
	private String description;
	/**
	 * 是否可用
	 */
	@Column
	private Boolean available;
}
