package com.beamofsoul.springboot.other.entity;

import java.util.Date;

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
 * @ClassName Scheduling
 * @Description 前置数据库中医生排班信息表对应的业务对象实体类
 * @author MingshuJian
 * @Date 2017年8月9日 下午1:16:09
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "T_SCHEDULING")
public class Scheduling extends BaseAbstractEntity {
	
	private static final long serialVersionUID = -6876170793787496272L;
	
	@Id
	@GeneratedValue
    @Column
    protected Long id;
	/**
	 * 医生编号
	 */
    @Column(name = "doctor_code", length = 20)
    private String doctorCode;
    /**
     * 工作日
     */
    @Column(name = "workday")
    private Date workday;
    /**
     * 是否可继续挂号
     */
    @Column(name = "available")
    private Boolean available;
}
