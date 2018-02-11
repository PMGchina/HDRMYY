package com.beamofsoul.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.beamofsoul.springboot.entity.QuestionnaireUser;
import com.querydsl.core.types.Predicate;

public interface QuestionnaireUserService {

	QuestionnaireUser create(QuestionnaireUser questionnaireUser);

	QuestionnaireUser update(QuestionnaireUser questionnaireUser);

	long delete(Long... ids);

	long deleteByQuestionnaireIds(Long[] ids);

	QuestionnaireUser findById(Long id);

	boolean isExistQuestionnaireUser(Long questionnaireId, String departmentName, Long userId);

	List<QuestionnaireUser> findAll();

	Page<QuestionnaireUser> findAll(Pageable pageable);

	Page<QuestionnaireUser> findAll(Pageable pageable, Predicate predicate);

	boolean isExistQuestionnaireUser(Long... questionnaireIds);

}
