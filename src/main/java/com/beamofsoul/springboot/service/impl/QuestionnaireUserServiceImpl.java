package com.beamofsoul.springboot.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beamofsoul.springboot.entity.QuestionnaireUser;
import com.beamofsoul.springboot.entity.query.QQuestionnaireUser;
import com.beamofsoul.springboot.repository.QuestionnaireUserRepository;
import com.beamofsoul.springboot.service.QuestionnaireUserService;
import com.querydsl.core.types.Predicate;

@Service("questionnaireUserService")
public class QuestionnaireUserServiceImpl extends BaseAbstractServiceImpl implements QuestionnaireUserService {

	@Autowired
	private QuestionnaireUserRepository questionnaireUserRepository;

	@Override
	public QuestionnaireUser create(QuestionnaireUser questionnaireUser) {
		return questionnaireUserRepository.save(questionnaireUser);
	}

	@Override
	public QuestionnaireUser update(QuestionnaireUser questionnaireUser) {
		QuestionnaireUser originalQuestionnaireUser = questionnaireUserRepository.findOne(questionnaireUser.getId());
		BeanUtils.copyProperties(questionnaireUser, originalQuestionnaireUser);
		return questionnaireUserRepository.save(originalQuestionnaireUser);
	}

	@Override
	@Transactional
	public long delete(Long... ids) {
		return questionnaireUserRepository.deleteByIds(ids);
	}

	@Override
	public QuestionnaireUser findById(Long id) {
		return questionnaireUserRepository.findOne(id);
	}

	@Override
	public boolean isExistQuestionnaireUser(Long questionnaireId, String departmentName, Long userId) {
		QQuestionnaireUser q = QQuestionnaireUser.questionnaireUser;
		return questionnaireUserRepository.exists(q.questionnaire.id.eq(questionnaireId)
				.and(q.user.id.eq(userId)).and(q.departmentName.eq(departmentName)));
	}

	@Override
	public boolean isExistQuestionnaireUser(Long... questionnaireIds) {
		return questionnaireUserRepository.exists(QQuestionnaireUser.questionnaireUser.questionnaire.id.in(questionnaireIds));
	}

	@Override
	@Transactional(readOnly = true)
	public List<QuestionnaireUser> findAll() {
		return questionnaireUserRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<QuestionnaireUser> findAll(Pageable pageable) {
		return questionnaireUserRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<QuestionnaireUser> findAll(Pageable pageable, Predicate predicate) {
		return questionnaireUserRepository.findAll(predicate, pageable);
	}

	@Override
	public long deleteByQuestionnaireIds(Long[] ids) {
		QQuestionnaireUser qu = new QQuestionnaireUser("QuestionnaireUser");
		return questionnaireUserRepository.deleteByPredicate(qu.questionnaire.id.in(ids));
	}
}
