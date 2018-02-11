package com.beamofsoul.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.beamofsoul.springboot.entity.HealthEducation;
import com.querydsl.core.types.Predicate;

public interface HealthEducationService {

	HealthEducation create(HealthEducation healthEducation);
	HealthEducation update(HealthEducation healthEducation);
	long delete(Long... ids);

	HealthEducation findById(Long id);

	List<HealthEducation> findAll();
	Page<HealthEducation> findAll(Pageable pageable);
	Page<HealthEducation> findAll(Pageable pageable, Predicate predicate);
	List<HealthEducation> findAllAvailable();
}
