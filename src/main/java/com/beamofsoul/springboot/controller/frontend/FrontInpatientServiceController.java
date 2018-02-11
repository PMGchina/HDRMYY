package com.beamofsoul.springboot.controller.frontend;

import static com.beamofsoul.springboot.management.util.JSONUtils.newInstance;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;
import com.beamofsoul.springboot.controller.BaseAbstractController;
import com.beamofsoul.springboot.service.InpatientServiceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/front/inpatientService")
public class FrontInpatientServiceController extends BaseAbstractController {

	@Resource
	private InpatientServiceService inpatientServiceService;

	@RequestMapping(value = "index")
	public String index() {
		return "inpatient_service/front_inpatient_service";
	}

	@RequestMapping(value = "load", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject loadInpatientService() {
		return newInstance("all", inpatientServiceService.findAllAvailable());
	}
}
