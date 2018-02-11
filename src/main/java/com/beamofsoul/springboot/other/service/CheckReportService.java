package com.beamofsoul.springboot.other.service;

import com.beamofsoul.springboot.other.entity.CheckReport;
import java.util.Date;
import java.util.List;

public interface CheckReportService {

	List<CheckReport> findByMedicalCardAndOneday(Date oneday, String medicalCard);
}
