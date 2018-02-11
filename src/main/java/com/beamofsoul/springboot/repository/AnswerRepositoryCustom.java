package com.beamofsoul.springboot.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.beamofsoul.springboot.entity.dto.SingleAnswerStatistics;

public interface AnswerRepositoryCustom<T,ID> {
	
	List<SingleAnswerStatistics> groupByAnswerStatistics(EntityManager entityManager, Long questionId);
}
