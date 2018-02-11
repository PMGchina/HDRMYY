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
 * @ClassName HospitalEvent
 * @Description 院内大事件实体类
 * @author MingshuJian
 * @Date 2017年7月25日 下午1:51:59
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "T_HOSPITAL_EVENT")
public class HospitalEvent extends BaseAbstractEntity  {
	
	private static final long serialVersionUID = 5261254526292084994L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	
	@Column(length = 100)
	private String title;
	
	@Column(name = "image_url", length = 256)
	private String imageUrl;
	
	@Column(columnDefinition = "text")
	private String content;
	
	private Boolean available;
}
