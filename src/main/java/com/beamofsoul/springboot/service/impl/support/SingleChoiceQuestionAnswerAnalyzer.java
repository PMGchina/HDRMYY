package com.beamofsoul.springboot.service.impl.support;

import java.util.ArrayList;
import java.util.List;

import com.beamofsoul.springboot.entity.Question;
import com.beamofsoul.springboot.entity.dto.SingleAnswerStatistics;
import com.beamofsoul.springboot.repository.AnswerRepository;

public class SingleChoiceQuestionAnswerAnalyzer implements AnswerAnalyzer {

	@Override
	public List<SingleAnswerStatistics> analysis(Question question, long numberOfAnswers, AnswerRepository answerRepository) {		
		String content = question.getContent().replaceAll(SPLITTER, null);
		String[] contentArray = content.split(",");
		List<SingleAnswerStatistics> statistics = new ArrayList<>(contentArray.length);
		for (String option : contentArray)
			statistics.add(new SingleAnswerStatistics(option,answerRepository.count(answer.questionId.eq(question.getId()).and(answer.content.eq(option)))));
		long total = getTotal(statistics, question.getRequired(), numberOfAnswers, null);
		statistics.stream().forEach(e -> e.setTotal(total));
		return statistics;
	}
}
