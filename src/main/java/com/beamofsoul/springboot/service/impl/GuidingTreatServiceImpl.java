package com.beamofsoul.springboot.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beamofsoul.springboot.entity.GuidingTreat;
import com.beamofsoul.springboot.entity.query.QGuidingTreat;
import com.beamofsoul.springboot.repository.GuidingTreatRepository;
import com.beamofsoul.springboot.service.GuidingTreatService;
import com.querydsl.core.types.Predicate;

@Service("guidingTreatService")
public class GuidingTreatServiceImpl extends BaseAbstractServiceImpl implements GuidingTreatService {

	@Autowired
	private GuidingTreatRepository guidingTreatRepository;

	@Override
	public GuidingTreat create(GuidingTreat guidingTreat) {
		return guidingTreatRepository.save(guidingTreat);
	}

	@Override
	public GuidingTreat update(GuidingTreat guidingTreat) {
		GuidingTreat originalGuidingTreat = guidingTreatRepository.findOne(guidingTreat.getId());
		BeanUtils.copyProperties(guidingTreat, originalGuidingTreat);
		return guidingTreatRepository.save(originalGuidingTreat);
	}

	@Override
	@Transactional
	public long delete(Long... ids) {
		return guidingTreatRepository.deleteByIds(ids);
	}

	@Override
	public GuidingTreat findById(Long id) {
		return guidingTreatRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<GuidingTreat> findAll() {
		return guidingTreatRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<GuidingTreat> findAll(Pageable pageable) {
		return guidingTreatRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<GuidingTreat> findAll(Pageable pageable, Predicate predicate) {
		return guidingTreatRepository.findAll(predicate, pageable);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<GuidingTreat> findAllAvailable() {
		return guidingTreatRepository.findByPredicate(new QGuidingTreat("GuidingTreat").available.eq(true));
	}
}
