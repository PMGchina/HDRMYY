package com.beamofsoul.springboot.other.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.beamofsoul.springboot.entity.BaseAbstractEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ClassName HealthExaminationItem
 * @Description 体检项目实体类
 * @author MingshuJian
 * @Date 2017年7月25日 下午1:51:59
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "T_HEALTH_EXAMINATION_ITEM")
public class HealthExaminationItem extends BaseAbstractEntity  {

	private static final long serialVersionUID = -3492276022642815937L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	protected Long id;
	
	@Column(name = "name", length = 100)
	private String name;
	
	@Column(name = "description", length = 512)
	private String description;
	
	@Column(name = "price")
	private Double price;
	
	private Boolean available;
}
