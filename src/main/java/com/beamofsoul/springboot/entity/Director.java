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
 * @ClassName Director
 * @Description 主任风采实体类
 * @author MingshuJian
 * @Date 2017年7月25日 下午1:51:59
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "T_DIRECTOR")
public class Director extends BaseAbstractEntity  {
	
	private static final long serialVersionUID = 4634578712080375144L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	protected Long id;
	
	@Column(length = 100, nullable = false)
	private String name;
	
	@Column(length = 100)
	private String title;
	
	private Boolean gender;
	
	@Column(name = "image_url")
	private String imageUrl;
	
	@Column(length = 5000)
	private String description;
	
	private Boolean available;

	@Column(name = "doctor_code", length = 20)
	private String doctorCode;
	
	@Column(name = "department_code", length = 20)
	private String departmentCode;
}
