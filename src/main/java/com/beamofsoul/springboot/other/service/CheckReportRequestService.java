package com.beamofsoul.springboot.other.service;

import com.beamofsoul.springboot.other.entity.CheckReportRequest;

public interface CheckReportRequestService {

   CheckReportRequest findByMedicalCard(String medicalCard);

   CheckReportRequest updateByMedicalCard(String medicalCard);
}
