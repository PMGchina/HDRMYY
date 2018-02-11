package com.beamofsoul.springboot.other.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beamofsoul.springboot.other.entity.Department;
import com.beamofsoul.springboot.other.entity.query.QDepartment;
import com.beamofsoul.springboot.other.repository.DepartmentRepository;
import com.beamofsoul.springboot.other.service.DepartmentService;
import com.beamofsoul.springboot.service.impl.BaseAbstractServiceImpl;
import com.querydsl.core.types.Predicate;

@Service("departmentService")
public class DepartmentServiceImpl extends BaseAbstractServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public Department findById(Long id) {
		return departmentRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Department> findAll() {
		return departmentRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Department> findAll(Pageable pageable) {
		return departmentRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Department> findAllAvailable() {
		QDepartment $ = new QDepartment("Department");
		return departmentRepository.findByPredicate($.available.eq(true));
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Department> findAll(Pageable pageable, Predicate predicate) {
		return departmentRepository.findAll(predicate, pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Department> findByIds(Long... ids) {
		return departmentRepository.findByIds(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Department> findByCodes(String... codes) {
		QDepartment $ = new QDepartment("Department");
		return departmentRepository.findByPredicate($.code.in(codes));
	}
}
