package com.beamofsoul.springboot.other.repository;

import org.springframework.stereotype.Repository;

import com.beamofsoul.springboot.other.entity.Doctor;
import com.beamofsoul.springboot.management.repository.BaseMultielementRepository;

@Repository
public interface DoctorRepository extends BaseMultielementRepository<Doctor, Long> {

}
