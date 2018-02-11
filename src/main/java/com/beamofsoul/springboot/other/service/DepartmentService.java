package com.beamofsoul.springboot.other.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.beamofsoul.springboot.other.entity.Department;
import com.querydsl.core.types.Predicate;

public interface DepartmentService {

	Department findById(Long id);

	List<Department> findAll();

	Page<Department> findAll(Pageable pageable);

	Page<Department> findAll(Pageable pageable, Predicate predicate);

	List<Department> findAllAvailable();

	List<Department> findByIds(Long... ids);

	List<Department> findByCodes(String... codes);
}
