package com.beamofsoul.springboot.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beamofsoul.springboot.entity.Questionnaire;
import com.beamofsoul.springboot.entity.User;
import com.beamofsoul.springboot.entity.query.QQuestionnaire;
import com.beamofsoul.springboot.repository.QuestionnaireRepository;
import com.beamofsoul.springboot.service.QuestionService;
import com.beamofsoul.springboot.service.QuestionnaireService;
import com.beamofsoul.springboot.service.QuestionnaireUserService;
import com.querydsl.core.types.Predicate;

@Service("questionnaireService")
public class QuestionnaireServiceImpl extends BaseAbstractServiceImpl implements QuestionnaireService {

    @Autowired
    private QuestionnaireRepository questionnaireRepository;

    @Autowired
    private QuestionService questionService;
    
    @Autowired
    private QuestionnaireUserService questionnaireUserService;
    
    @Override
    public Questionnaire create(Questionnaire questionnaire) {
        return questionnaireRepository.save(questionnaire);
    }

    @Transactional
    @Override
    public Long create(String name, String description, String questions, User user) {
        Questionnaire savedQuestionnaire = this.create(new Questionnaire(name, description, user.getId()));
        questionService.create(questions, savedQuestionnaire);
        return savedQuestionnaire.getId();
    }

    @Override
    public Questionnaire update(Questionnaire questionnaire) {
        Questionnaire originalQuestionnaire = questionnaireRepository.findOne(questionnaire.getId());
        BeanUtils.copyProperties(questionnaire, originalQuestionnaire);
        return questionnaireRepository.save(originalQuestionnaire);
    }

    @Override
    public long update(Long id, String name, String description, String questions) {
        Questionnaire original = questionnaireRepository.findOne(id);
        original.setName(name);
        original.setDescription(description);
        questionnaireRepository.save(original);
        return questionService.create(questions, original).size();
    }

    @Override
    @Transactional
    public long delete(Long... ids) {
    	// 删除问卷下所有问题
    	questionService.deleteByQuestionnaireIds(ids);
    	// 删除问卷用户关联信息
    	questionnaireUserService.deleteByQuestionnaireIds(ids);
    	// 删除问卷
        return questionnaireRepository.deleteByIds(ids);
    }

    @Override
    public Questionnaire findById(Long id) {
        return questionnaireRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Questionnaire> findAll() {
        return questionnaireRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Questionnaire> findAll(Pageable pageable) {
        return questionnaireRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Questionnaire> findAll(Pageable pageable, Predicate predicate) {
        return questionnaireRepository.findAll(predicate, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Questionnaire> findAllUnpublished() {
    	QQuestionnaire q = new QQuestionnaire("Questionnaire");
    	return questionnaireRepository.findByPredicate(q.published.eq(false));
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Questionnaire> findAllUnpublishedAndAvailable() {
    	QQuestionnaire q = new QQuestionnaire("Questionnaire");
    	return questionnaireRepository.findByPredicate(q.published.eq(false).and(q.available.eq(true)));
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Questionnaire> findAllPublishedAndAvailable() {
    	QQuestionnaire q = new QQuestionnaire("Questionnaire");
    	return questionnaireRepository.findByPredicate(q.published.eq(true).and(q.available.eq(true)));
    }
    
    @Override
    @Transactional
    public long publish(Long... ids) {
    	return updatePublishedStatusByIds(true,ids);
    }
    
    @Override
    @Transactional
    public long unpublish(Long... ids) {
    	return updatePublishedStatusByIds(false,ids);
    }
    
    @Override
    @Transactional
    public long available(Long... ids) {
    	return updateAvailableStatusByIds(true,ids);
    }
    
    @Override
    @Transactional
    public long unavailable(Long... ids) {
    	return updateAvailableStatusByIds(false,ids);
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean isPublished(Long... ids) {
    	QQuestionnaire questionnaire = QQuestionnaire.questionnaire;
		return questionnaireRepository.exists(questionnaire.published.eq(true).and(questionnaire.id.in(ids)));
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean isAnswered(Long... ids) {
    	return questionnaireUserService.isExistQuestionnaireUser(ids);
    }

	private long updatePublishedStatusByIds(boolean published, Long... ids) {
		QQuestionnaire questionnaire = new QQuestionnaire("Questionnaire");
		return questionnaireRepository.update(questionnaire.published, published, questionnaire.id.in(ids));
	}
	
	private long updateAvailableStatusByIds(boolean available, Long... ids) {
		QQuestionnaire questionnaire = new QQuestionnaire("Questionnaire");
		return questionnaireRepository.update(questionnaire.available, available, questionnaire.id.in(ids));
	}
	
	@Override
    @Transactional
	public boolean increaseNumberOfAnswers(Long questionnaireId) {
		QQuestionnaire questionnaire = new QQuestionnaire("Questionnaire");
		return  questionnaireRepository.update(questionnaire.numberOfAnswers, questionnaire.numberOfAnswers.add(1), questionnaire.id.eq(questionnaireId)) > 0;
	}
}
