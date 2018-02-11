package com.beamofsoul.springboot.other.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beamofsoul.springboot.other.entity.Patient;
import com.beamofsoul.springboot.other.entity.query.QPatient;
import com.beamofsoul.springboot.other.repository.PatientRepository;
import com.beamofsoul.springboot.other.service.PatientService;
import com.beamofsoul.springboot.service.impl.BaseAbstractServiceImpl;
import com.querydsl.core.types.Predicate;

@Service("patientService")
public class PatientServiceImpl extends BaseAbstractServiceImpl implements PatientService {

	@Autowired
	private PatientRepository patientRepository;

	@Override
	public Patient findById(Long id) {
		return patientRepository.findOne(id);
	}
	
	@Override
	public Patient findByMedicalCard(String medicalCard) {
		QPatient p = new QPatient("Patient");
		return patientRepository.findOneByPredicate(p.medicalCard.eq(medicalCard));
	}

	@Override
	@Transactional(readOnly = true)
	public List<Patient> findAll() {
		return patientRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Patient> findAll(Pageable pageable) {
		return patientRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Patient> findAll(Pageable pageable, Predicate predicate) {
		return patientRepository.findAll(predicate, pageable);
	}
}
