package com.beamofsoul.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.beamofsoul.springboot.entity.SeekingTreatStrategy;
import com.querydsl.core.types.Predicate;

public interface SeekingTreatStrategyService {

	SeekingTreatStrategy create(SeekingTreatStrategy seekingTreatStrategy);
	SeekingTreatStrategy update(SeekingTreatStrategy seekingTreatStrategy);
	long delete(Long... ids);

	SeekingTreatStrategy findById(Long id);

	List<SeekingTreatStrategy> findAll();
	Page<SeekingTreatStrategy> findAll(Pageable pageable);
	Page<SeekingTreatStrategy> findAll(Pageable pageable, Predicate predicate);
	List<SeekingTreatStrategy> findAllAvailable();
}
