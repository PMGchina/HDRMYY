package com.beamofsoul.springboot.other.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.beamofsoul.springboot.other.entity.Patient;
import com.querydsl.core.types.Predicate;

public interface PatientService {

	Patient findById(Long id);
	Patient findByMedicalCard(String medicalCard);
	List<Patient> findAll();
	Page<Patient> findAll(Pageable pageable);
	Page<Patient> findAll(Pageable pageable, Predicate predicate);
}
