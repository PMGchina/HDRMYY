package com.beamofsoul.springboot.repository;

import org.springframework.stereotype.Repository;

import com.beamofsoul.springboot.entity.InpatientService;
import com.beamofsoul.springboot.management.repository.BaseMultielementRepository;

@Repository
public interface InpatientServiceRepository extends BaseMultielementRepository<InpatientService, Long> {

}
