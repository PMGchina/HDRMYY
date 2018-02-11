package com.beamofsoul.springboot.other.service.impl;

import com.beamofsoul.springboot.other.entity.CheckReport;
import com.beamofsoul.springboot.other.entity.query.QCheckReport;
import com.beamofsoul.springboot.other.repository.CheckReportRepository;
import com.beamofsoul.springboot.other.service.CheckReportService;
import com.beamofsoul.springboot.service.impl.BaseAbstractServiceImpl;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("checkReportService")
public class CheckReportServiceImpl
		extends BaseAbstractServiceImpl implements CheckReportService {

	@Autowired
	private CheckReportRepository checkReportRepository;

	@Override
	public List<CheckReport> findByMedicalCardAndOneday(Date oneday, String medicalCard) {
		QCheckReport $ = new QCheckReport("CheckReport");
		return checkReportRepository.findByPredicate($.medicalCard.eq(medicalCard).and($.inspectionDate.goe(oneday)));
	}
}
