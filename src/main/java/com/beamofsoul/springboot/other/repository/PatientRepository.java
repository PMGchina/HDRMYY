package com.beamofsoul.springboot.other.repository;

import org.springframework.stereotype.Repository;

import com.beamofsoul.springboot.other.entity.Patient;
import com.beamofsoul.springboot.management.repository.BaseMultielementRepository;

@Repository
public interface PatientRepository extends BaseMultielementRepository<Patient, Long> {

}
