package com.beamofsoul.springboot.controller.frontend;

import static com.beamofsoul.springboot.management.util.JSONUtils.newInstance;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;
import com.beamofsoul.springboot.controller.BaseAbstractController;
import com.beamofsoul.springboot.entity.User;
import com.beamofsoul.springboot.management.mvc.CurrentUser;
import com.beamofsoul.springboot.other.service.PatientService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/front/patient")
public class FrontPatientController extends BaseAbstractController {

	@Resource
	private PatientService patientService;

	@PreAuthorize("authenticated")
	@RequestMapping(value = "index")
	public String index() {
		return "patient/front_patient";
	}

	@RequestMapping(value = "patient", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject loadPatient(@CurrentUser User user) {
		return newInstance("patient", patientService.findByMedicalCard(user.getUsername()));
	}
}
