package com.beamofsoul.springboot.other.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.beamofsoul.springboot.other.entity.ReexaminationItem;
import com.querydsl.core.types.Predicate;

public interface ReexaminationItemService {

	ReexaminationItem create(ReexaminationItem reexaminationItem);
	ReexaminationItem update(ReexaminationItem reexaminationItem);
	long delete(Long... ids);

	ReexaminationItem findById(Long id);
	List<ReexaminationItem> findByIds(Long... ids);

	List<ReexaminationItem> findAll();
	List<ReexaminationItem> findAllAvailable();
	Page<ReexaminationItem> findAll(Pageable pageable);
	Page<ReexaminationItem> findAll(Pageable pageable, Predicate predicate);
}
