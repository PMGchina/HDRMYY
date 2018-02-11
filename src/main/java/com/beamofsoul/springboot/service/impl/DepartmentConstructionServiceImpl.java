package com.beamofsoul.springboot.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beamofsoul.springboot.entity.DepartmentConstruction;
import com.beamofsoul.springboot.repository.DepartmentConstructionRepository;
import com.beamofsoul.springboot.service.DepartmentConstructionService;
import com.querydsl.core.types.Predicate;

@Service("departmentConstructionService")
public class DepartmentConstructionServiceImpl extends BaseAbstractServiceImpl implements DepartmentConstructionService {

	@Autowired
	private DepartmentConstructionRepository departmentConstructionRepository;

	@Override
	public DepartmentConstruction create(DepartmentConstruction departmentConstruction) {
		return departmentConstructionRepository.save(departmentConstruction);
	}

	@Override
	public DepartmentConstruction update(DepartmentConstruction departmentConstruction) {
		DepartmentConstruction originalDepartmentConstruction = departmentConstructionRepository.findOne(departmentConstruction.getId());
		BeanUtils.copyProperties(departmentConstruction, originalDepartmentConstruction);
		return departmentConstructionRepository.save(originalDepartmentConstruction);
	}

	@Override
	@Transactional
	public long delete(Long... ids) {
		return departmentConstructionRepository.deleteByIds(ids);
	}

	@Override
	public DepartmentConstruction findById(Long id) {
		return departmentConstructionRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<DepartmentConstruction> findAll() {
		return departmentConstructionRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<DepartmentConstruction> findAll(Pageable pageable) {
		return departmentConstructionRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<DepartmentConstruction> findAll(Pageable pageable, Predicate predicate) {
		return departmentConstructionRepository.findAll(predicate, pageable);
	}
}
