package com.beamofsoul.springboot.other.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.beamofsoul.springboot.other.entity.HealthExaminationPackage;
import com.querydsl.core.types.Predicate;

public interface HealthExaminationPackageService {

	HealthExaminationPackage create(HealthExaminationPackage healthExaminationPackage);
	HealthExaminationPackage update(HealthExaminationPackage healthExaminationPackage);
	long delete(Long... ids);

	HealthExaminationPackage findById(Long id);
	List<HealthExaminationPackage> findByIds(Long... ids);

	List<HealthExaminationPackage> findAll();
	List<HealthExaminationPackage> findAllAvailable();
	Page<HealthExaminationPackage> findAll(Pageable pageable);
	Page<HealthExaminationPackage> findAll(Pageable pageable, Predicate predicate);
}
