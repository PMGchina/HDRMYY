package com.beamofsoul.springboot.controller.frontend;

import static com.beamofsoul.springboot.management.util.JSONUtils.newInstance;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;
import com.beamofsoul.springboot.controller.BaseAbstractController;
import com.beamofsoul.springboot.service.SeekingTreatStrategyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/front/seekingTreatStrategy")
public class FrontSeekingTreatStrategyController extends BaseAbstractController {

	@Resource
	private SeekingTreatStrategyService seekingTreatStrategyService;

	@RequestMapping(value = "index")
	public String index() {
		return "seeking_treat_strategy/front_seeking_treat_strategy";
	}

	@RequestMapping(value = "load", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject loadSeekingTreatStrategy() {
		return newInstance("all", seekingTreatStrategyService.findAllAvailable());
	}
}
