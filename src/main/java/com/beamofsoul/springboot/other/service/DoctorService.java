package com.beamofsoul.springboot.other.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.beamofsoul.springboot.other.entity.Doctor;
import com.querydsl.core.types.Predicate;

public interface DoctorService {

	Doctor findById(Long id);
	List<Doctor> findAll();
	Page<Doctor> findAll(Pageable pageable);
	Page<Doctor> findAll(Pageable pageable, Predicate predicate);
	List<Doctor> findAllNormalDoctorsByDepartmentCode(String departmentCode);
	List<Doctor> findAllExpertDoctorsByDepartmentCode(String departmentCode);
	
	List<Doctor> findByIds(Long... ids);
	List<Doctor> findByCodes(String... codes);
	List<Doctor> findAllAvailable();
}

