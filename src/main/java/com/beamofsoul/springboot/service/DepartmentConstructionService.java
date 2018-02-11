package com.beamofsoul.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.beamofsoul.springboot.entity.DepartmentConstruction;
import com.querydsl.core.types.Predicate;

public interface DepartmentConstructionService {

	DepartmentConstruction create(DepartmentConstruction departmentConstruction);
	DepartmentConstruction update(DepartmentConstruction departmentConstruction);
	long delete(Long... ids);

	DepartmentConstruction findById(Long id);

	List<DepartmentConstruction> findAll();
	Page<DepartmentConstruction> findAll(Pageable pageable);
	Page<DepartmentConstruction> findAll(Pageable pageable, Predicate predicate);
}
