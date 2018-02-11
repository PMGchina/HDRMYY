package com.beamofsoul.springboot.other.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.beamofsoul.springboot.other.entity.HealthExaminationItem;
import com.querydsl.core.types.Predicate;

public interface HealthExaminationItemService {

	HealthExaminationItem create(HealthExaminationItem healthExaminationItem);
	HealthExaminationItem update(HealthExaminationItem healthExaminationItem);
	long delete(Long... ids);

	HealthExaminationItem findById(Long id);
	List<HealthExaminationItem> findByIds(Long... ids);

	List<HealthExaminationItem> findAll();
	List<HealthExaminationItem> findAllAvailable();
	Page<HealthExaminationItem> findAll(Pageable pageable);
	Page<HealthExaminationItem> findAll(Pageable pageable, Predicate predicate);
}
