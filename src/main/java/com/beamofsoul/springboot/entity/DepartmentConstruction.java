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
 * @ClassName DepartmentConstruction
 * @Description 科室建设实体类
 * @author MingshuJian
 * @Date 2017年7月25日 下午1:51:59
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "T_DEPARTMENT_CONSTRUCTION")
public class DepartmentConstruction extends BaseAbstractEntity  {

	private static final long serialVersionUID = 3538759092324424930L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;

	@Column(length = 30)
	private String name;
	
	@Column(name = "icon_url", length = 256)
	private String iconUrl;
	
	@Column(name = "department_code", length = 20)
	private String departmentCode;
	
	@Column(length = 5000)
	private String description;
	
	private Boolean available;
}
