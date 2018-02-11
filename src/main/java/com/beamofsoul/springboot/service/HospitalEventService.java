package com.beamofsoul.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.beamofsoul.springboot.entity.HospitalEvent;
import com.querydsl.core.types.Predicate;

public interface HospitalEventService {

	HospitalEvent create(HospitalEvent hospitalEvent);
	HospitalEvent update(HospitalEvent hospitalEvent);
	long delete(Long... ids);

	HospitalEvent findById(Long id);

	List<HospitalEvent> findAll();
	Page<HospitalEvent> findAll(Pageable pageable);
	Page<HospitalEvent> findAll(Pageable pageable, Predicate predicate);
	List<HospitalEvent> findAllAvailable();
}
