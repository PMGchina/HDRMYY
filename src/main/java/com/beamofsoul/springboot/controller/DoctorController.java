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
import com.beamofsoul.springboot.other.service.DoctorService;

@Controller
@RequestMapping("/admin/doctor")
public class DoctorController extends BaseAbstractController {

	@Resource
	private DoctorService doctorService;
	
	@PreAuthorize("authenticated and hasPermission('doctor','doctor:list')")
	@RequestMapping(value = "/adminList")
	public String adminList() {
		return "doctor/admin_doctor_list";
	}
	
	@PreAuthorize("authenticated and hasPermission('doctor','doctor:list')")
	@PostResponseBody("doctorsByPage")
	public JSONObject getPageableDoctors(@RequestBody Map<String, Object> map) {
		return newInstance(doctorService.findAll(PageUtils.parsePageable(map)));
	}
}
