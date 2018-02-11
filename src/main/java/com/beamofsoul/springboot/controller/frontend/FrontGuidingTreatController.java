package com.beamofsoul.springboot.controller.frontend;

import static com.beamofsoul.springboot.management.util.JSONUtils.newInstance;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;
import com.beamofsoul.springboot.controller.BaseAbstractController;
import com.beamofsoul.springboot.service.GuidingTreatService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/front/guidingTreat")
public class FrontGuidingTreatController extends BaseAbstractController {

	@Resource
	private GuidingTreatService guidingTreatService;

	@RequestMapping(value = "index")
	public String index() {
		return "guiding_treat/front_guiding_treat";
	}

	@RequestMapping(value = "load", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject loadGuidingTreat() {
		return newInstance("all", guidingTreatService.findAllAvailable());
	}
}
