package com.beamofsoul.springboot.repository;

import org.springframework.stereotype.Repository;

import com.beamofsoul.springboot.entity.Director;
import com.beamofsoul.springboot.management.repository.BaseMultielementRepository;

@Repository
public interface DirectorRepository extends BaseMultielementRepository<Director, Long> {

}
