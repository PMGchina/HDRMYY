package com.beamofsoul.springboot.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beamofsoul.springboot.entity.QuestionType;
import com.beamofsoul.springboot.entity.query.QQuestion;
import com.beamofsoul.springboot.repository.QuestionRepository;
import com.beamofsoul.springboot.repository.QuestionTypeRepository;
import com.beamofsoul.springboot.service.QuestionTypeService;
import com.querydsl.core.types.Predicate;

@Service("questionTypeService")
public class QuestionTypeServiceImpl extends BaseAbstractServiceImpl implements QuestionTypeService {

	@Autowired
	private QuestionTypeRepository questionTypeRepository;
	
	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public QuestionType create(QuestionType questionType) {
		return questionTypeRepository.save(questionType);
	}

	@Override
	public QuestionType update(QuestionType questionType) {
		QuestionType originalQuestionType = questionTypeRepository.findOne(questionType.getId());
		BeanUtils.copyProperties(questionType, originalQuestionType);
		return questionTypeRepository.save(originalQuestionType);
	}

	@Override
	@Transactional
	public long delete(Long... ids) {
		return questionTypeRepository.deleteByIds(ids);
	}

	@Override
	public QuestionType findById(Long id) {
		return questionTypeRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<QuestionType> findAll() {
		return questionTypeRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<QuestionType> findAll(Pageable pageable) {
		return questionTypeRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<QuestionType> findAll(Pageable pageable, Predicate predicate) {
		return questionTypeRepository.findAll(predicate, pageable);
	}
	
	@Override
	@Transactional(readOnly = true)
	public boolean isPublished(Long... ids) {
		return questionRepository.exists(QQuestion.question.type.id.in(ids));
	}
}
