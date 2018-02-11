package com.beamofsoul.springboot.repository;

import org.springframework.stereotype.Repository;

import com.beamofsoul.springboot.entity.HospitalEvent;
import com.beamofsoul.springboot.management.repository.BaseMultielementRepository;

@Repository
public interface HospitalEventRepository extends BaseMultielementRepository<HospitalEvent, Long> {

}
