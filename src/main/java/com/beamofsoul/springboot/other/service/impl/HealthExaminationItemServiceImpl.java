package com.beamofsoul.springboot.other.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beamofsoul.springboot.other.entity.HealthExaminationItem;
import com.beamofsoul.springboot.other.entity.query.QHealthExaminationItem;
import com.beamofsoul.springboot.other.repository.HealthExaminationItemRepository;
import com.beamofsoul.springboot.other.service.HealthExaminationItemService;
import com.beamofsoul.springboot.service.impl.BaseAbstractServiceImpl;
import com.querydsl.core.types.Predicate;

@Service("healthExaminationItemService")
public class HealthExaminationItemServiceImpl extends BaseAbstractServiceImpl implements HealthExaminationItemService {

	@Autowired
	private HealthExaminationItemRepository healthExaminationItemRepository;

	@Override
	public HealthExaminationItem create(HealthExaminationItem healthExaminationItem) {
		return healthExaminationItemRepository.save(healthExaminationItem);
	}

	@Override
	public HealthExaminationItem update(HealthExaminationItem healthExaminationItem) {
		HealthExaminationItem originalHealthExaminationItem = healthExaminationItemRepository.findOne(healthExaminationItem.getId());
		BeanUtils.copyProperties(healthExaminationItem, originalHealthExaminationItem);
		return healthExaminationItemRepository.save(originalHealthExaminationItem);
	}

	@Override
	@Transactional
	public long delete(Long... ids) {
		return healthExaminationItemRepository.deleteByIds(ids);
	}

	@Override
	public HealthExaminationItem findById(Long id) {
		return healthExaminationItemRepository.findOne(id);
	}
	
	@Override
	public List<HealthExaminationItem> findByIds(Long... ids) {
		return healthExaminationItemRepository.findByIds(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<HealthExaminationItem> findAll() {
		return healthExaminationItemRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<HealthExaminationItem> findAll(Pageable pageable) {
		return healthExaminationItemRepository.findAll(pageable);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<HealthExaminationItem> findAllAvailable() {
		QHealthExaminationItem $ = new QHealthExaminationItem("HealthExaminationItem");
		return healthExaminationItemRepository.findByPredicate($.available.eq(true));
	}

	@Override
	@Transactional(readOnly = true)
	public Page<HealthExaminationItem> findAll(Pageable pageable, Predicate predicate) {
		return healthExaminationItemRepository.findAll(predicate, pageable);
	}
}
