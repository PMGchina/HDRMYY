package com.beamofsoul.springboot.other.service.impl;

import com.beamofsoul.springboot.other.entity.CheckReportRequest;
import com.beamofsoul.springboot.other.repository.CheckReportRequestRepository;
import com.beamofsoul.springboot.other.service.CheckReportRequestService;
import com.beamofsoul.springboot.service.impl.BaseAbstractServiceImpl;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("checkReportRequestService")
public class CheckReportRequestServiceImpl
		extends BaseAbstractServiceImpl implements CheckReportRequestService {

	@Autowired
	private CheckReportRequestRepository crRequestRepository;

	@Override
	public CheckReportRequest findByMedicalCard(String medicalCard) {
		return null;
	}

	@Override
	public CheckReportRequest updateByMedicalCard(String medicalCard) {
		CheckReportRequest crRequest = new CheckReportRequest();
		CheckReportRequest $ = crRequestRepository.findBymedicalCard(medicalCard);
		if ($ != null) {
			crRequest.setId($.getId());
		}
		crRequest.setMedicalCard(medicalCard);
		Date date = new Date();
		crRequest.setRequestDate(date);
		crRequest.setIsInto(Boolean.FALSE);
		return crRequestRepository.saveAndFlush(crRequest);
	}
}
