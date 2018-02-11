package com.beamofsoul.springboot.service.impl.support;

import java.util.List;

import com.beamofsoul.springboot.entity.Question;
import com.beamofsoul.springboot.entity.dto.SingleAnswerStatistics;
import com.beamofsoul.springboot.entity.query.QAnswer;
import com.beamofsoul.springboot.repository.AnswerRepository;

public interface AnswerAnalyzer {
	
	static final String SPLITTER = "#@#";
	static final QAnswer answer = QAnswer.answer;
	
	default long getTotal(List<SingleAnswerStatistics> statistics, boolean isRequired, long numberOfAnswers, Long count) {
		return isRequired ? numberOfAnswers : (count == null ? statistics.stream().mapToLong(e -> e.getCount()).sum() : count.longValue());
	}
	
	static AnswerAnalyzer create(Question question) {
		String questionType = question.getType().getName();
		AnswerAnalyzer analyzer = null;
		if (questionType.equals(Question.Type.SINGLE_CHOICE.getValue())) {
			analyzer = new SingleChoiceQuestionAnswerAnalyzer();
		} else if (questionType.equals(Question.Type.MULTIPLE_CHOICE.getValue())) {
			analyzer = new MultipleChoiceQuestionAnswerAnalyzer();
		} else if (questionType.equals(Question.Type.ESSAY.getValue())) {
			analyzer = new EassyQuestionAnswerAnalyzer();
		} else if (questionType.equals(Question.Type.SCORE.getValue())) {
			analyzer = new ScoreQuestionAnswerAnalyzer();
		} else if (questionType.equals(Question.Type.MATRIX.getValue())) {
			analyzer = new MatrixQuestionAnswerAnalyzer();
		}
		return analyzer;
	}

	List<SingleAnswerStatistics> analysis(Question question, long numberOfAnswers, AnswerRepository answerRepository);
}
