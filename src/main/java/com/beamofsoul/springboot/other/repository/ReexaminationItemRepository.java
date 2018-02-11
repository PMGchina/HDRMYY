package com.beamofsoul.springboot.other.repository;

import org.springframework.stereotype.Repository;

import com.beamofsoul.springboot.other.entity.ReexaminationItem;
import com.beamofsoul.springboot.management.repository.BaseMultielementRepository;

@Repository
public interface ReexaminationItemRepository extends BaseMultielementRepository<ReexaminationItem, Long> {

}
