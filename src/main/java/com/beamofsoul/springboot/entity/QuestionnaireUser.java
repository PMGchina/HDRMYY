package com.beamofsoul.springboot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ClassName QuestionnaireUser
 * @Description 问卷用户关联立体类
 * @author MingshuJian
 * @Date 2017年7月25日 下午1:42:19
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "T_QUESTIONNAIRE_USER")
public class QuestionnaireUser extends BaseAbstractEntity {

	private static final long serialVersionUID = -5459209258725535461L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	protected Long id;
	/**
	 * 问卷
	 */
	@ManyToOne
	@JoinColumn(name = "QUESTIONNAIRE_ID")
	private Questionnaire questionnaire;
	/**
	 * 用户
	 */
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;
	/**
	 * 科室名称
	 */
	@Column(name = "department_name", length = 20)
	private String departmentName;

	public QuestionnaireUser(Questionnaire questionnaire, User user) {
		this.questionnaire = questionnaire;
		this.user = user;
	}

	public QuestionnaireUser(Long questionnaireId, String departmentName, Long userId) {
		this.questionnaire = new Questionnaire(questionnaireId);
		this.departmentName = departmentName;
		this.user = new User(userId);
	}
}
