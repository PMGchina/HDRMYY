package com.beamofsoul.springboot.repository;

import org.springframework.stereotype.Repository;

import com.beamofsoul.springboot.entity.DepartmentConstruction;
import com.beamofsoul.springboot.management.repository.BaseMultielementRepository;

@Repository
public interface DepartmentConstructionRepository extends BaseMultielementRepository<DepartmentConstruction, Long> {

}
