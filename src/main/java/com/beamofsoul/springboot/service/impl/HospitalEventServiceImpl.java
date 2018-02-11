package com.beamofsoul.springboot.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beamofsoul.springboot.entity.HospitalEvent;
import com.beamofsoul.springboot.entity.query.QHospitalEvent;
import com.beamofsoul.springboot.repository.HospitalEventRepository;
import com.beamofsoul.springboot.service.HospitalEventService;
import com.querydsl.core.types.Predicate;

@Service("hospitalEventService")
public class HospitalEventServiceImpl extends BaseAbstractServiceImpl implements HospitalEventService {

	@Autowired
	private HospitalEventRepository hospitalEventRepository;

	@Override
	public HospitalEvent create(HospitalEvent hospitalEvent) {
		return hospitalEventRepository.save(hospitalEvent);
	}

	@Override
	public HospitalEvent update(HospitalEvent hospitalEvent) {
		HospitalEvent originalHospitalEvent = hospitalEventRepository.findOne(hospitalEvent.getId());
		BeanUtils.copyProperties(hospitalEvent, originalHospitalEvent);
		return hospitalEventRepository.save(originalHospitalEvent);
	}

	@Override
	@Transactional
	public long delete(Long... ids) {
		return hospitalEventRepository.deleteByIds(ids);
	}

	@Override
	public HospitalEvent findById(Long id) {
		return hospitalEventRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<HospitalEvent> findAll() {
		return hospitalEventRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<HospitalEvent> findAll(Pageable pageable) {
		return hospitalEventRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<HospitalEvent> findAll(Pageable pageable, Predicate predicate) {
		return hospitalEventRepository.findAll(predicate, pageable);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<HospitalEvent> findAllAvailable() {
		return hospitalEventRepository.findByPredicate(new QHospitalEvent("HospitalEvent").available.eq(true));
	}
}
