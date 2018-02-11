package com.beamofsoul.springboot.controller.frontend;

import static com.beamofsoul.springboot.management.util.JSONUtils.newInstance;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.beamofsoul.springboot.controller.BaseAbstractController;
import com.beamofsoul.springboot.service.HospitalEventService;

@Controller
@RequestMapping("/front/hospitalEvent")
public class FrontHospitalEventController extends BaseAbstractController {

	@Resource
	private HospitalEventService hospitalEventService;

	@RequestMapping(value = "index")
	public String index() {
		return "hospital_event/front_hospital_event";
	}

	@RequestMapping(value = "hospitalEvent", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject loadHospitalEvent() {
		return newInstance("all", hospitalEventService.findAll());
	}
}
