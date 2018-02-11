package com.beamofsoul.springboot.service.impl.support;

import java.util.ArrayList;
import java.util.List;

import com.beamofsoul.springboot.entity.Question;
import com.beamofsoul.springboot.entity.dto.SingleAnswerStatistics;
import com.beamofsoul.springboot.repository.AnswerRepository;

public class MatrixQuestionAnswerAnalyzer implements AnswerAnalyzer {

	@Override
	public List<SingleAnswerStatistics> analysis(Question question, long numberOfAnswers, AnswerRepository answerRepository) {
		// 矩阵题暂时不需要统计
		return new ArrayList<>(1);
	}
}