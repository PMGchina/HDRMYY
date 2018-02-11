package com.beamofsoul.springboot.other.repository;

import org.springframework.stereotype.Repository;

import com.beamofsoul.springboot.management.repository.BaseMultielementRepository;
import com.beamofsoul.springboot.other.entity.HealthExaminationPackage;

@Repository
public interface HealthExaminationPackageRepository extends BaseMultielementRepository<HealthExaminationPackage, Long> {

}
