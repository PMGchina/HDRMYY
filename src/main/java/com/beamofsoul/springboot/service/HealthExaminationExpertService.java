package com.beamofsoul.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.beamofsoul.springboot.entity.HealthExaminationExpert;
import com.querydsl.core.types.Predicate;

public interface HealthExaminationExpertService {

	HealthExaminationExpert create(HealthExaminationExpert healthExaminationExpert);
	HealthExaminationExpert update(HealthExaminationExpert healthExaminationExpert);
	long delete(Long... ids);

	HealthExaminationExpert findById(Long id);

	List<HealthExaminationExpert> findAll();
	List<HealthExaminationExpert> findAllAvailable();
	Page<HealthExaminationExpert> findAll(Pageable pageable);
	Page<HealthExaminationExpert> findAll(Pageable pageable, Predicate predicate);
}
