package com.beamofsoul.springboot.entity;

import java.util.HashMap;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * @ClassName Question
 * @Description 问题实体类
 * @author MingshuJian
 * @Date 2017年7月25日 下午1:43:47
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "T_QUESTION")
public class Question extends BaseAbstractEntity  {

	private static final long serialVersionUID = 2982680853865410202L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	protected Long id;
	/**
	 * 对应问卷
	 */
	@ManyToOne(cascade = CascadeType.ALL)  
	@JoinColumn(name = "questionnaire_id")
	private Questionnaire questionnaire;
	/**
	 * 题干
	 */
	@Column(name = "stem", length = 512)
	private String stem;
	/**
	 * 题目选项或内容，不是答案
	 */
	@Column(name = "content", length = 512)
	private String content;
	/**
	 * 题目序号
	 */
	@Column(name = "sort")
	private Integer sort;
	/**
	 * 是否必答题
	 */
	@Column(name = "required")
	private Boolean required;
	/**
	 * 题目类型
	 */
	@ManyToOne  
	@JoinColumn(name = "question_type_id")
	private QuestionType type;
	
	@RequiredArgsConstructor(access=AccessLevel.PROTECTED)
	public static enum Type {
		SINGLE_CHOICE("单选题"), MULTIPLE_CHOICE("多选题"), ESSAY("问答题"), SCORE("评分题"), MATRIX("矩阵题");
		@Getter private final String value;
		private static HashMap<String, Type> codeValueMap = new HashMap<>(5);
		static {
			for (Type type : Type.values()) {
				codeValueMap.put(type.value, type);
			}
		}
		
		public static Type getInstance(String code) {
			return codeValueMap.get(code);
		}
	}
}
