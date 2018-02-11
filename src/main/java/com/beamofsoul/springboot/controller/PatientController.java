package com.beamofsoul.springboot.controller;

import static com.beamofsoul.springboot.management.util.JSONUtils.newInstance;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.beamofsoul.springboot.management.mvc.PostResponseBody;
import com.beamofsoul.springboot.management.util.PageUtils;
import com.beamofsoul.springboot.other.service.PatientService;

@Controller
@RequestMapping("/admin/patient")
public class PatientController extends BaseAbstractController {

	@Resource
	private PatientService patientService;
	
	@PreAuthorize("authenticated and hasPermission('patient','patient:list')")
	@RequestMapping(value = "/adminList")
	public String adminList() {
		return "patient/admin_patient_list";
	}
	
	@PreAuthorize("authenticated and hasPermission('patient','patient:list')")
	@PostResponseBody("patientsByPage")
	public JSONObject getPageablePatients(@RequestBody Map<String, Object> map) {
		return newInstance(patientService.findAll(PageUtils.parsePageable(map)));
	}
}
