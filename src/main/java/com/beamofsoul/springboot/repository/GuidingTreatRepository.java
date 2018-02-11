package com.beamofsoul.springboot.repository;

import org.springframework.stereotype.Repository;

import com.beamofsoul.springboot.entity.GuidingTreat;
import com.beamofsoul.springboot.management.repository.BaseMultielementRepository;

@Repository
public interface GuidingTreatRepository extends BaseMultielementRepository<GuidingTreat, Long> {

}
