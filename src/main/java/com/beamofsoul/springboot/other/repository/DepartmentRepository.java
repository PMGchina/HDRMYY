package com.beamofsoul.springboot.other.repository;

import org.springframework.stereotype.Repository;

import com.beamofsoul.springboot.other.entity.Department;
import com.beamofsoul.springboot.management.repository.BaseMultielementRepository;

@Repository
public interface DepartmentRepository extends BaseMultielementRepository<Department, Long> {

}
