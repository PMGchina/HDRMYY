package com.beamofsoul.springboot.repository;

import org.springframework.stereotype.Repository;

import com.beamofsoul.springboot.entity.HealthExaminationExpert;
import com.beamofsoul.springboot.management.repository.BaseMultielementRepository;

@Repository
public interface HealthExaminationExpertRepository extends BaseMultielementRepository<HealthExaminationExpert, Long> {

}
