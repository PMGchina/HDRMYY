package com.beamofsoul.springboot.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.beamofsoul.springboot.entity.Question;
import com.beamofsoul.springboot.entity.Questionnaire;
import com.beamofsoul.springboot.entity.dto.SingleAnswerStatistics;
import com.beamofsoul.springboot.entity.query.QAnswer;
import com.beamofsoul.springboot.entity.query.QQuestion;
import com.beamofsoul.springboot.management.util.CollectionUtils;
import com.beamofsoul.springboot.repository.AnswerRepository;
import com.beamofsoul.springboot.repository.QuestionRepository;
import com.beamofsoul.springboot.service.QuestionService;
import com.beamofsoul.springboot.service.impl.support.AnswerAnalyzer;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;

@Service("questionService")
public class QuestionServiceImpl extends BaseAbstractServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private AnswerRepository answerRepository;

	@Override
	public Question create(Question question) {
		return questionRepository.save(question);
	}

	@Override
	public List<Question> create(String questions, Questionnaire savedQuestionnaire) {
		List<Question> questionList = JSON.parseArray(questions, Question.class);
		questionList.forEach(e -> e.setQuestionnaire(savedQuestionnaire));
		return questionRepository.save(questionList);
	}

	@Override
	public Question update(Question question) {
		Question originalQuestion = questionRepository.findOne(question.getId());
		BeanUtils.copyProperties(question, originalQuestion);
		return questionRepository.save(originalQuestion);
	}

	@Override
	@Transactional
	public long deleteByQuestionnaireIds(Long... ids) {
		QQuestion question = new QQuestion("Question");
		QAnswer answer = new QAnswer("Answer");
		// 根据输入参数问卷ID数组获取要被删除的问题ID集合
		QueryResults<?> queryResult = questionRepository.findSpecificDataByPredicate(question.questionnaire.id.in(ids), question.id);
		List<?> results = queryResult.getResults();
		if (CollectionUtils.isNotBlank(results)) {
			Long[] questionIds = results.stream().map(e -> ((Tuple) e).get(question.id)).collect(Collectors.toList()).toArray(new Long[]{});
			// 删除问题对应的答案记录
			answerRepository.deleteByPredicate(answer.questionId.in(questionIds));
			// 删除问题记录并返回删除的条数
			return questionRepository.deleteByIds(questionIds);
		} else {
			return 0L;
		}
	}

	@Override
	@Transactional
	public long delete(Long... ids) {
		return questionRepository.deleteByIds(ids);
	}

	@Override
	public Question findById(Long id) {
		return questionRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Question> findAll() {
		return questionRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Question> findAll(Long questionnaireId) {
		QQuestion question = new QQuestion("Question");
		return questionRepository.findByPredicate(question.questionnaire.id.eq(questionnaireId));
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Question> findAll(Pageable pageable) {
		return questionRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Question> findAll(Pageable pageable, Predicate predicate) {
		return questionRepository.findAll(predicate, pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean isPublished(Long... ids) {
		QQuestion question = QQuestion.question;
		return questionRepository.exists(question.questionnaire.published.eq(true).and(question.id.in(ids)));
	}

	@Override
	@Transactional
	public List<List<SingleAnswerStatistics>> getChartData(List<Question> questions, Questionnaire questionnaire) {
		List<List<SingleAnswerStatistics>> chartData = new ArrayList<>(questions.size());
		long numberOfAnswers = questionnaire.getNumberOfAnswers().longValue();
		questions.stream().forEach(e -> chartData.add(AnswerAnalyzer.create(e).analysis(e, numberOfAnswers, answerRepository)));
		return chartData;
	}
}
