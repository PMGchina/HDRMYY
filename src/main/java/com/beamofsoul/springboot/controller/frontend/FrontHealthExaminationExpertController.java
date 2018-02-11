package com.beamofsoul.springboot.controller.frontend;

import static com.beamofsoul.springboot.management.util.JSONUtils.newInstance;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.beamofsoul.springboot.controller.BaseAbstractController;
import com.beamofsoul.springboot.service.HealthExaminationExpertService;

@Controller
@RequestMapping("/front/healthExaminationExpert")
public class FrontHealthExaminationExpertController extends BaseAbstractController {

	@Resource
	private HealthExaminationExpertService healthExaminationExpertService;

	@RequestMapping(value = "index")
	public String index() {
		return "health_examination_expert/front_health_examination_expert";
	}

	@RequestMapping(value = "expert", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject loadHealthExaminationExpert() {
		return newInstance("all", healthExaminationExpertService.findAllAvailable());
	}
}
