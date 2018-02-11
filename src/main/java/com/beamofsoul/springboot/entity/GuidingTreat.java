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
 * @ClassName GuidingTreat
 * @Description 门诊导医实体类
 * @author MingshuJian
 * @Date 2017年7月25日 下午1:51:59
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "T_GUIDING_TREAT")
public class GuidingTreat extends BaseAbstractEntity  {
	
	private static final long serialVersionUID = -483311293401029576L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	
	@Column(length = 100)
	private String item;
	
	@Column(name = "image_url", length = 256)
	private String imageUrl;
	
	@Column(columnDefinition = "text")
	private String content;
	
	private Boolean available;
}
