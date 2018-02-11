package com.beamofsoul.springboot.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beamofsoul.springboot.entity.SeekingTreatStrategy;
import com.beamofsoul.springboot.entity.query.QSeekingTreatStrategy;
import com.beamofsoul.springboot.repository.SeekingTreatStrategyRepository;
import com.beamofsoul.springboot.service.SeekingTreatStrategyService;
import com.querydsl.core.types.Predicate;

@Service("seekingTreatStrategyService")
public class SeekingTreatStrategyServiceImpl extends BaseAbstractServiceImpl implements SeekingTreatStrategyService {

	@Autowired
	private SeekingTreatStrategyRepository seekingTreatStrategyRepository;

	@Override
	public SeekingTreatStrategy create(SeekingTreatStrategy seekingTreatStrategy) {
		return seekingTreatStrategyRepository.save(seekingTreatStrategy);
	}

	@Override
	public SeekingTreatStrategy update(SeekingTreatStrategy seekingTreatStrategy) {
		SeekingTreatStrategy originalSeekingTreatStrategy = seekingTreatStrategyRepository.findOne(seekingTreatStrategy.getId());
		BeanUtils.copyProperties(seekingTreatStrategy, originalSeekingTreatStrategy);
		return seekingTreatStrategyRepository.save(originalSeekingTreatStrategy);
	}

	@Override
	@Transactional
	public long delete(Long... ids) {
		return seekingTreatStrategyRepository.deleteByIds(ids);
	}

	@Override
	public SeekingTreatStrategy findById(Long id) {
		return seekingTreatStrategyRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<SeekingTreatStrategy> findAll() {
		return seekingTreatStrategyRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<SeekingTreatStrategy> findAll(Pageable pageable) {
		return seekingTreatStrategyRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<SeekingTreatStrategy> findAll(Pageable pageable, Predicate predicate) {
		return seekingTreatStrategyRepository.findAll(predicate, pageable);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<SeekingTreatStrategy> findAllAvailable() {
		return seekingTreatStrategyRepository.findByPredicate(new QSeekingTreatStrategy("SeekingTreatStrategy").available.eq(true));
	}
}
