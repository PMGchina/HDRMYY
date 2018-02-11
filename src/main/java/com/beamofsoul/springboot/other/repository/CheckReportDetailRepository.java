package com.beamofsoul.springboot.other.repository;

import com.beamofsoul.springboot.management.repository.BaseMultielementRepository;
import com.beamofsoul.springboot.other.entity.CheckReportDetail;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckReportDetailRepository
      extends BaseMultielementRepository<CheckReportDetail, Long> {

}
