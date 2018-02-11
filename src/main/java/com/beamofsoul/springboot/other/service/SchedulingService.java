package com.beamofsoul.springboot.other.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.beamofsoul.springboot.other.entity.Scheduling;
import com.querydsl.core.types.Predicate;

public interface SchedulingService {

	Scheduling findById(Long id);
	List<Scheduling> findAll();
	List<Scheduling> findAllByDoctorCode(String code);
	Page<Scheduling> findAll(Pageable pageable);
	Page<Scheduling> findAll(Pageable pageable, Predicate predicate);
	List<Scheduling> findAllByDate(Date date);
}
