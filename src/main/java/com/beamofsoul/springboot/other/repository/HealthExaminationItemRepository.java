package com.beamofsoul.springboot.other.repository;

import org.springframework.stereotype.Repository;

import com.beamofsoul.springboot.management.repository.BaseMultielementRepository;
import com.beamofsoul.springboot.other.entity.HealthExaminationItem;

@Repository
public interface HealthExaminationItemRepository extends BaseMultielementRepository<HealthExaminationItem, Long> {

}
