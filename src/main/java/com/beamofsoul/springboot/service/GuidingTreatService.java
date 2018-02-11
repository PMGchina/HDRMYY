package com.beamofsoul.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.beamofsoul.springboot.entity.GuidingTreat;
import com.querydsl.core.types.Predicate;

public interface GuidingTreatService {

	GuidingTreat create(GuidingTreat guidingTreat);
	GuidingTreat update(GuidingTreat guidingTreat);
	long delete(Long... ids);

	GuidingTreat findById(Long id);

	List<GuidingTreat> findAll();
	Page<GuidingTreat> findAll(Pageable pageable);
	Page<GuidingTreat> findAll(Pageable pageable, Predicate predicate);
	List<GuidingTreat> findAllAvailable();
}
