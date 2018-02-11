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
 * @ClassName Answer
 * @Description 答案实体类
 * @author MingshuJian
 * @Date 2017年7月25日 下午1:51:59
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "T_ANSWER")
public class Answer extends BaseAbstractEntity  {

	private static final long serialVersionUID = -1553574053600992496L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	protected Long id;
	/**
	 * 对应题目
	 */
	@Column(name = "question_id")
	private Long questionId;
	/**
	 * 答题用户
	 */
	@Column(name = "user_id")
	private Long userId;
	/**
	 * 答案
	 */
	@Column(name = "content", length = 512)
	private String content;
	/**
	 * 可选备注
	 */
	@Column(name = "optional_remark", length = 256)
	private String optionalRemark;
}
