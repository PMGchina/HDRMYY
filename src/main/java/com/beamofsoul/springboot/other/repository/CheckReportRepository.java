package com.beamofsoul.springboot.other.repository;

import com.beamofsoul.springboot.management.repository.BaseMultielementRepository;
import com.beamofsoul.springboot.other.entity.CheckReport;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckReportRepository
      extends BaseMultielementRepository<CheckReport, Long> {

}
