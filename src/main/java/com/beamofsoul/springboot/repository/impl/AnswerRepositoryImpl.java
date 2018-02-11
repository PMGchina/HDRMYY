package com.beamofsoul.springboot.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.repository.NoRepositoryBean;

import com.beamofsoul.springboot.entity.dto.SingleAnswerStatistics;
import com.beamofsoul.springboot.entity.query.QAnswer;
import com.beamofsoul.springboot.repository.AnswerRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;

import lombok.NonNull;

@NoRepositoryBean
public class AnswerRepositoryImpl<Answer> implements AnswerRepositoryCustom<Answer, Long> {

	@Override
	public List<SingleAnswerStatistics> groupByAnswerStatistics(@NonNull EntityManager entityManager, @NonNull Long questionId) {
		JPAQuery<SingleAnswerStatistics> query = new JPAQuery<SingleAnswerStatistics>(entityManager);
		QAnswer answer = QAnswer.answer;
		return query.select(Projections.constructor(SingleAnswerStatistics.class, answer.content, answer.id.count()))
					.from(answer)
					.where(answer.questionId.eq(questionId))
					.groupBy(answer.content)
					.fetch();
	}
}
