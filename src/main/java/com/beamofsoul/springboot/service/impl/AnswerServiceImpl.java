package com.beamofsoul.springboot.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.beamofsoul.springboot.entity.Answer;
import com.beamofsoul.springboot.entity.QuestionnaireUser;
import com.beamofsoul.springboot.repository.AnswerRepository;
import com.beamofsoul.springboot.service.AnswerService;
import com.beamofsoul.springboot.service.QuestionnaireService;
import com.beamofsoul.springboot.service.QuestionnaireUserService;
import com.querydsl.core.types.Predicate;

@Service("answerService")
public class AnswerServiceImpl extends BaseAbstractServiceImpl implements AnswerService {

	@Autowired
	private AnswerRepository answerRepository;

	@Autowired
	private QuestionnaireService questionnaireService;

	@Autowired
	private QuestionnaireUserService questionnaireUserService;

	@Override
	public Answer create(Answer answer) {
		return answerRepository.save(answer);
	}

	@Override
	public long create(String answers, Long questionnaireId, String departmentName, Long userId) {
		if (questionnaireUserService.isExistQuestionnaireUser(questionnaireId, userId)) {
			return -1L;
		} else {
			questionnaireUserService.create(new QuestionnaireUser(questionnaireId, departmentName, userId));
			questionnaireService.increaseNumberOfAnswers(questionnaireId);
			List<Answer> answersList = JSON.parseArray(answers, Answer.class);
			answersList.forEach(e -> e.setUserId(userId));
			return answerRepository.save(answersList).size();
		}
	}

	@Override
	public Answer update(Answer answer) {
		Answer originalAnswer = answerRepository.findOne(answer.getId());
		BeanUtils.copyProperties(answer, originalAnswer);
		return answerRepository.save(originalAnswer);
	}

	@Override
	@Transactional
	public long delete(Long... ids) {
		return answerRepository.deleteByIds(ids);
	}

	@Override
	public Answer findById(Long id) {
		return answerRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Answer> findAll() {
		return answerRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Answer> findAll(Pageable pageable) {
		return answerRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Answer> findAll(Pageable pageable, Predicate predicate) {
		return answerRepository.findAll(predicate, pageable);
	}

	@Override
	public boolean checkAnswered(Long questionnaireId, String departmentName, Long userId) {
		return questionnaireUserService.isExistQuestionnaireUser(questionnaireId, departmentName, userId);
	}
}
