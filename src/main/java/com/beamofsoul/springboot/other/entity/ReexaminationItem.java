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
 * @ClassName ReexaminationItem
 * @Description 前置数据库中复查项目信息表对应的业务对象实体类
 * @author MingshuJian
 * @Date 2017年8月9日 下午1:16:09
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "T_REEXAMINATION_ITEM")
public class ReexaminationItem extends BaseAbstractEntity {
	
	private static final long serialVersionUID = 7275816136044570927L;
	
	@Id
	@GeneratedValue
    @Column
    protected Long id;
	/**
	 * 复查项目编号
	 */
    @Column(name = "code", length = 20)
    private String code;
    /**
     * 复查项目名称
     */
    @Column(name = "name", length = 20)
    private String name;
    /**
     * 价格
     */
    private Double price;
    /**
     * 描述
     */
    @Column(length = 512)
    private String description;
    /**
     * 是否可用
     */
    private Boolean available;
}
