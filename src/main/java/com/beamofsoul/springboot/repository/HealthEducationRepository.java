package com.beamofsoul.springboot.repository;

import org.springframework.stereotype.Repository;

import com.beamofsoul.springboot.entity.HealthEducation;
import com.beamofsoul.springboot.management.repository.BaseMultielementRepository;

@Repository
public interface HealthEducationRepository extends BaseMultielementRepository<HealthEducation, Long> {

}
