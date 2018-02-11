package com.beamofsoul.springboot.controller.frontend;

import static com.beamofsoul.springboot.management.util.JSONUtils.newInstance;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;
import com.beamofsoul.springboot.controller.BaseAbstractController;
import com.beamofsoul.springboot.other.service.DoctorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/front/doctor")
public class FrontDoctorController extends BaseAbstractController {

	@Resource
	private DoctorService doctorService;

	@RequestMapping(value = "index")
	public String index() {
		return "doctor/front_doctor";
	}

	@RequestMapping(value = "doctor", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject loadDoctor() {
		return newInstance("all", doctorService.findAll());
	}
}
