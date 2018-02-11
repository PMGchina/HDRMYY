package com.beamofsoul.springboot.other.repository;

import org.springframework.stereotype.Repository;

import com.beamofsoul.springboot.other.entity.Scheduling;
import com.beamofsoul.springboot.management.repository.BaseMultielementRepository;

@Repository
public interface SchedulingRepository extends BaseMultielementRepository<Scheduling, Long> {

}
