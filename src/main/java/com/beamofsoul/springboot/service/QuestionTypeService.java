package com.beamofsoul.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.beamofsoul.springboot.entity.QuestionType;
import com.querydsl.core.types.Predicate;

public interface QuestionTypeService {

	QuestionType create(QuestionType questionType);
	QuestionType update(QuestionType questionType);
	long delete(Long... ids);

	QuestionType findById(Long id);

	List<QuestionType> findAll();
	Page<QuestionType> findAll(Pageable pageable);
	Page<QuestionType> findAll(Pageable pageable, Predicate predicate);
	
	boolean isPublished(Long... ids);
}
