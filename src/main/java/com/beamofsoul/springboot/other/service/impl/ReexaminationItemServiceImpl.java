package com.beamofsoul.springboot.other.service.impl;

import com.beamofsoul.springboot.other.entity.query.QReexaminationItem;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beamofsoul.springboot.other.entity.ReexaminationItem;
import com.beamofsoul.springboot.other.repository.ReexaminationItemRepository;
import com.beamofsoul.springboot.other.service.ReexaminationItemService;
import com.beamofsoul.springboot.service.impl.BaseAbstractServiceImpl;
import com.querydsl.core.types.Predicate;

@Service("reexaminationItemService")
public class ReexaminationItemServiceImpl extends BaseAbstractServiceImpl implements ReexaminationItemService {

	@Autowired
	private ReexaminationItemRepository reexaminationItemRepository;

	@Override
	public ReexaminationItem create(ReexaminationItem reexaminationItem) {
		return reexaminationItemRepository.save(reexaminationItem);
	}

	@Override
	public ReexaminationItem update(ReexaminationItem reexaminationItem) {
		ReexaminationItem originalReexaminationItem = reexaminationItemRepository.findOne(reexaminationItem.getId());
		BeanUtils.copyProperties(reexaminationItem, originalReexaminationItem);
		return reexaminationItemRepository.save(originalReexaminationItem);
	}

	@Override
	@Transactional
	public long delete(Long... ids) {
		return reexaminationItemRepository.deleteByIds(ids);
	}

	@Override
	public ReexaminationItem findById(Long id) {
		return reexaminationItemRepository.findOne(id);
	}

	@Override
	public List<ReexaminationItem> findByIds(Long... ids) {
		return reexaminationItemRepository.findByIds(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ReexaminationItem> findAll() {
		return reexaminationItemRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<ReexaminationItem> findAllAvailable() {
		QReexaminationItem $ = new QReexaminationItem("ReexaminationItem");
		return reexaminationItemRepository.findByPredicate($.available.eq(true));
	}

	@Override
	@Transactional(readOnly = true)
	public Page<ReexaminationItem> findAll(Pageable pageable) {
		return reexaminationItemRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<ReexaminationItem> findAll(Pageable pageable, Predicate predicate) {
		return reexaminationItemRepository.findAll(predicate, pageable);
	}
}
