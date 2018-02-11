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
import lombok.NonNull;

/**
 * @ClassName Questionnaire
 * @Description 问卷
 * @author MingshuJian
 * @Date 2017年7月25日 下午1:32:45
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "T_QUESTIONNAIRE")
public class Questionnaire extends BaseAbstractEntity  {

	private static final long serialVersionUID = 6255217253551938272L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	protected Long id;
	/**
	 * 问卷名称
	 */
	@Column(name = "name", length = 50)
	private String name;
	/**
	 * 问卷描述
	 */
	@Column(name = "description", length = 200)
	private String description;
	/**
	 * 答题人数
	 */
	@Column(name = "number_of_answers")
	private Integer numberOfAnswers;
	/**
	 * 创建者
	 */
	@Column(name = "creator")
	private Long creator;
	/**
	 * 是否已经发布
	 */
	@Column(name = "published")
	private Boolean published;
	/**
	 * 是否可用
	 */
	@Column(name = "available")
	private Boolean available;
	
	public Questionnaire(Long id) {
		this.id = id;
	}
	
	public Questionnaire(@NonNull String name, String description, Long creator) {
		this.name = name;
		this.description = description;
		this.numberOfAnswers = 0;
		this.creator = creator;
		this.published = false;
		this.available = true;
	}
}
