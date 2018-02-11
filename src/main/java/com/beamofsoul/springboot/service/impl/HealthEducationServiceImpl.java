package com.beamofsoul.springboot.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beamofsoul.springboot.entity.HealthEducation;
import com.beamofsoul.springboot.entity.query.QHealthEducation;
import com.beamofsoul.springboot.repository.HealthEducationRepository;
import com.beamofsoul.springboot.service.HealthEducationService;
import com.querydsl.core.types.Predicate;

@Service("healthEducationService")
public class HealthEducationServiceImpl extends BaseAbstractServiceImpl implements HealthEducationService {

	@Autowired
	private HealthEducationRepository healthEducationRepository;

	@Override
	public HealthEducation create(HealthEducation healthEducation) {
		return healthEducationRepository.save(healthEducation);
	}

	@Override
	public HealthEducation update(HealthEducation healthEducation) {
		HealthEducation originalHealthEducation = healthEducationRepository.findOne(healthEducation.getId());
		BeanUtils.copyProperties(healthEducation, originalHealthEducation);
		return healthEducationRepository.save(originalHealthEducation);
	}

	@Override
	@Transactional
	public long delete(Long... ids) {
		return healthEducationRepository.deleteByIds(ids);
	}

	@Override
	public HealthEducation findById(Long id) {
		return healthEducationRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<HealthEducation> findAll() {
		return healthEducationRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<HealthEducation> findAll(Pageable pageable) {
		return healthEducationRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<HealthEducation> findAll(Pageable pageable, Predicate predicate) {
		return healthEducationRepository.findAll(predicate, pageable);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<HealthEducation> findAllAvailable() {
		return healthEducationRepository.findByPredicate(new QHealthEducation("HealthEducation").available.eq(true));
	}
}
