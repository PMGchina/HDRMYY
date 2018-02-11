package com.beamofsoul.springboot.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beamofsoul.springboot.entity.HealthExaminationExpert;
import com.beamofsoul.springboot.entity.query.QHealthExaminationExpert;
import com.beamofsoul.springboot.repository.HealthExaminationExpertRepository;
import com.beamofsoul.springboot.service.HealthExaminationExpertService;
import com.querydsl.core.types.Predicate;

@Service("healthExaminationExpertService")
public class HealthExaminationExpertServiceImpl extends BaseAbstractServiceImpl implements HealthExaminationExpertService {

	@Autowired
	private HealthExaminationExpertRepository healthExaminationExpertRepository;

	@Override
	public HealthExaminationExpert create(HealthExaminationExpert healthExaminationExpert) {
		return healthExaminationExpertRepository.save(healthExaminationExpert);
	}

	@Override
	public HealthExaminationExpert update(HealthExaminationExpert healthExaminationExpert) {
		HealthExaminationExpert originalHealthExaminationExpert = healthExaminationExpertRepository.findOne(healthExaminationExpert.getId());
		BeanUtils.copyProperties(healthExaminationExpert, originalHealthExaminationExpert);
		return healthExaminationExpertRepository.save(originalHealthExaminationExpert);
	}

	@Override
	@Transactional
	public long delete(Long... ids) {
		return healthExaminationExpertRepository.deleteByIds(ids);
	}

	@Override
	public HealthExaminationExpert findById(Long id) {
		return healthExaminationExpertRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<HealthExaminationExpert> findAll() {
		return healthExaminationExpertRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<HealthExaminationExpert> findAllAvailable() {
		QHealthExaminationExpert $ = new QHealthExaminationExpert("HealthExaminationExpert"); 
		return healthExaminationExpertRepository.findByPredicate($.available.eq(true));
	}

	@Override
	@Transactional(readOnly = true)
	public Page<HealthExaminationExpert> findAll(Pageable pageable) {
		return healthExaminationExpertRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<HealthExaminationExpert> findAll(Pageable pageable, Predicate predicate) {
		return healthExaminationExpertRepository.findAll(predicate, pageable);
	}
}
