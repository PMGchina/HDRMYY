package com.beamofsoul.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.beamofsoul.springboot.entity.Question;
import com.beamofsoul.springboot.entity.Questionnaire;
import com.beamofsoul.springboot.entity.dto.SingleAnswerStatistics;
import com.querydsl.core.types.Predicate;

public interface QuestionService {

	Question create(Question question);
	List<Question> create(String questions, Questionnaire questionnaire);
	Question update(Question question);
	long deleteByQuestionnaireIds(Long... ids);
	long delete(Long... ids);

	Question findById(Long id);

	List<Question> findAll();
	Page<Question> findAll(Pageable pageable);
	Page<Question> findAll(Pageable pageable, Predicate predicate);
	List<Question> findAll(Long questionnaireId);
	
	boolean isPublished(Long... ids);
	List<List<SingleAnswerStatistics>> getChartData(List<Question> questions, Questionnaire questionnaire);
}
