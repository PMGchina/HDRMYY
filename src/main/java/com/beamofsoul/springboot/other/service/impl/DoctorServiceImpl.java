package com.beamofsoul.springboot.other.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beamofsoul.springboot.other.entity.Doctor;
import com.beamofsoul.springboot.other.entity.query.QDoctor;
import com.beamofsoul.springboot.other.repository.DoctorRepository;
import com.beamofsoul.springboot.other.service.DoctorService;
import com.beamofsoul.springboot.service.impl.BaseAbstractServiceImpl;
import com.querydsl.core.types.Predicate;

@Service("doctorService")
public class DoctorServiceImpl extends BaseAbstractServiceImpl implements DoctorService {

	@Autowired
	private DoctorRepository doctorRepository;

	@Override
	public Doctor findById(Long id) {
		return doctorRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Doctor> findAll() {
		return doctorRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Doctor> findAll(Pageable pageable) {
		return doctorRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Doctor> findAll(Pageable pageable, Predicate predicate) {
		return doctorRepository.findAll(predicate, pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Doctor> findAllNormalDoctorsByDepartmentCode(String departmentCode) {
		return findAllByExpertAndDepartmentCode(false, departmentCode);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Doctor> findAllExpertDoctorsByDepartmentCode(String departmentCode) {
		return findAllByExpertAndDepartmentCode(true, departmentCode);
	}

	private List<Doctor> findAllByExpertAndDepartmentCode(boolean expert, String departmentCode) {
		QDoctor doctor = new QDoctor("Doctor");
		return doctorRepository.findByPredicate(doctor.departmentCode.eq(departmentCode).and(doctor.available.eq(true)).and(doctor.expert.eq(expert)));
	}

	@Override
	@Transactional(readOnly = true)
	public List<Doctor> findByIds(Long... ids) {
		return doctorRepository.findByIds(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Doctor> findByCodes(String... codes) {
		QDoctor doctor = new QDoctor("Doctor");
		return doctorRepository.findByPredicate(doctor.code.in(codes));
	}

	@Override
	@Transactional(readOnly = true)
	public List<Doctor> findAllAvailable() {
		QDoctor doctor = new QDoctor("Doctor");
		return doctorRepository.findByPredicate(doctor.available.eq(true));
	}
}
