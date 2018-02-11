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
 * @ClassName InpatientService
 * @Description 住院服务实体类
 * @author MingshuJian
 * @Date 2017年7月25日 下午1:51:59
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "T_INPATIENT_SERVICE")
public class InpatientService extends BaseAbstractEntity  {
	
	private static final long serialVersionUID = -5544451740791933652L;

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
