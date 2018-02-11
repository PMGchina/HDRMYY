package com.beamofsoul.springboot.other.repository;

import com.beamofsoul.springboot.management.repository.BaseMultielementRepository;
import com.beamofsoul.springboot.other.entity.CheckReportRequest;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckReportRequestRepository
      extends BaseMultielementRepository<CheckReportRequest, Long> {

   CheckReportRequest findBymedicalCard(String medicalCard);
}
