package com.beamofsoul.springboot.service.impl.support;

import java.util.List;

import com.beamofsoul.springboot.entity.Question;
import com.beamofsoul.springboot.entity.dto.SingleAnswerStatistics;
import com.beamofsoul.springboot.repository.AnswerRepository;

public class MultipleChoiceQuestionAnswerAnalyzer implements AnswerAnalyzer {

	@Override
	public List<SingleAnswerStatistics> analysis(Question question, long numberOfAnswers, AnswerRepository answerRepository) {
		List<SingleAnswerStatistics> statistics = answerRepository.groupByAnswerStatistics(answerRepository.getEntityManager(), question.getId());
		long total = getTotal(statistics, question.getRequired(), numberOfAnswers, null);
		statistics.stream().forEach(e -> {
			e.setContent(e.getContent().replaceAll(SPLITTER, " 和 "));
			e.setTotal(total);
		});
		return statistics;
	}
}
