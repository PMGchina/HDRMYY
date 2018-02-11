package com.beamofsoul.springboot.other.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beamofsoul.springboot.other.entity.HealthExaminationPackage;
import com.beamofsoul.springboot.other.entity.query.QHealthExaminationPackage;
import com.beamofsoul.springboot.other.repository.HealthExaminationPackageRepository;
import com.beamofsoul.springboot.other.service.HealthExaminationPackageService;
import com.beamofsoul.springboot.service.impl.BaseAbstractServiceImpl;
import com.querydsl.core.types.Predicate;

@Service("healthExaminationPackageService")
public class HealthExaminationPackageServiceImpl extends BaseAbstractServiceImpl implements HealthExaminationPackageService {

	@Autowired
	private HealthExaminationPackageRepository healthExaminationPackageRepository;

	@Override
	public HealthExaminationPackage create(HealthExaminationPackage healthExaminationPackage) {
		return healthExaminationPackageRepository.save(healthExaminationPackage);
	}

	@Override
	public HealthExaminationPackage update(HealthExaminationPackage healthExaminationPackage) {
		HealthExaminationPackage originalHealthExaminationPackage = healthExaminationPackageRepository.findOne(healthExaminationPackage.getId());
		BeanUtils.copyProperties(healthExaminationPackage, originalHealthExaminationPackage);
		return healthExaminationPackageRepository.save(originalHealthExaminationPackage);
	}

	@Override
	@Transactional
	public long delete(Long... ids) {
		return healthExaminationPackageRepository.deleteByIds(ids);
	}

	@Override
	public HealthExaminationPackage findById(Long id) {
		return healthExaminationPackageRepository.findOne(id);
	}
	
	@Override
	public List<HealthExaminationPackage> findByIds(Long... ids) {
		return healthExaminationPackageRepository.findByIds(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<HealthExaminationPackage> findAll() {
		return healthExaminationPackageRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<HealthExaminationPackage> findAll(Pageable pageable) {
		return healthExaminationPackageRepository.findAll(pageable);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<HealthExaminationPackage> findAllAvailable() {
		QHealthExaminationPackage $ = new QHealthExaminationPackage("HealthExaminationPackage");
		return healthExaminationPackageRepository.findByPredicate($.available.eq(true));
	}

	@Override
	@Transactional(readOnly = true)
	public Page<HealthExaminationPackage> findAll(Pageable pageable, Predicate predicate) {
		return healthExaminationPackageRepository.findAll(predicate, pageable);
	}
}
