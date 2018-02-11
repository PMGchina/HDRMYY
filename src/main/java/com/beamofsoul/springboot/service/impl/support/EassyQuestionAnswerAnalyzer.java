package com.beamofsoul.springboot.service.impl.support;

import java.util.ArrayList;
import java.util.List;

import com.beamofsoul.springboot.entity.Question;
import com.beamofsoul.springboot.entity.dto.SingleAnswerStatistics;
import com.beamofsoul.springboot.repository.AnswerRepository;

public class EassyQuestionAnswerAnalyzer implements AnswerAnalyzer {

	@Override
	public List<SingleAnswerStatistics> analysis(Question question, long numberOfAnswers, AnswerRepository answerRepository) {
		List<SingleAnswerStatistics> statistics = new ArrayList<>(1);
		long total = getTotal(null, question.getRequired(), numberOfAnswers, answerRepository.count(answer.questionId.eq(question.getId())));
		statistics.add(new SingleAnswerStatistics(null, total, total));
		return statistics;
	}
}