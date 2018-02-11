package com.beamofsoul.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.beamofsoul.springboot.entity.Answer;
import com.querydsl.core.types.Predicate;

public interface AnswerService {

	Answer create(Answer answer);

	long create(String answers, Long questionnaireId, String departmentName, Long userId);

	Answer update(Answer answer);

	long delete(Long... ids);

	Answer findById(Long id);

	List<Answer> findAll();

	Page<Answer> findAll(Pageable pageable);

	Page<Answer> findAll(Pageable pageable, Predicate predicate);

	boolean checkAnswered(Long questionnaireId, String departmentName, Long userId);
}
