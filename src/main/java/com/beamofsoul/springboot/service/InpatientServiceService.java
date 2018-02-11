package com.beamofsoul.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.beamofsoul.springboot.entity.InpatientService;
import com.querydsl.core.types.Predicate;

public interface InpatientServiceService {

	InpatientService create(InpatientService inpatientService);
	InpatientService update(InpatientService inpatientService);
	long delete(Long... ids);

	InpatientService findById(Long id);

	List<InpatientService> findAll();
	Page<InpatientService> findAll(Pageable pageable);
	Page<InpatientService> findAll(Pageable pageable, Predicate predicate);
	List<InpatientService> findAllAvailable();
}
