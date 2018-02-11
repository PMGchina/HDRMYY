package com.beamofsoul.springboot.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beamofsoul.springboot.entity.InpatientService;
import com.beamofsoul.springboot.entity.query.QInpatientService;
import com.beamofsoul.springboot.repository.InpatientServiceRepository;
import com.beamofsoul.springboot.service.InpatientServiceService;
import com.querydsl.core.types.Predicate;

@Service("inpatientServiceService")
public class InpatientServiceServiceImpl extends BaseAbstractServiceImpl implements InpatientServiceService {

	@Autowired
	private InpatientServiceRepository inpatientServiceRepository;

	@Override
	public InpatientService create(InpatientService inpatientService) {
		return inpatientServiceRepository.save(inpatientService);
	}

	@Override
	public InpatientService update(InpatientService inpatientService) {
		InpatientService originalInpatientService = inpatientServiceRepository.findOne(inpatientService.getId());
		BeanUtils.copyProperties(inpatientService, originalInpatientService);
		return inpatientServiceRepository.save(originalInpatientService);
	}

	@Override
	@Transactional
	public long delete(Long... ids) {
		return inpatientServiceRepository.deleteByIds(ids);
	}

	@Override
	public InpatientService findById(Long id) {
		return inpatientServiceRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<InpatientService> findAll() {
		return inpatientServiceRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<InpatientService> findAll(Pageable pageable) {
		return inpatientServiceRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<InpatientService> findAll(Pageable pageable, Predicate predicate) {
		return inpatientServiceRepository.findAll(predicate, pageable);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<InpatientService> findAllAvailable() {
		return inpatientServiceRepository.findByPredicate(new QInpatientService("InpatientService").available.eq(true));
	}
}
