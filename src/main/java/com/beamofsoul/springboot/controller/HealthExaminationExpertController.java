package com.beamofsoul.springboot.controller;

import static com.beamofsoul.springboot.management.util.JSONUtils.newInstance;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.beamofsoul.springboot.entity.HealthExaminationExpert;
import com.beamofsoul.springboot.management.util.CommonConvertUtils;
import com.beamofsoul.springboot.management.util.PageUtils;
import com.beamofsoul.springboot.service.HealthExaminationExpertService;

@Controller
@RequestMapping("/admin/healthExaminationExpert")
public class HealthExaminationExpertController extends BaseAbstractController {

	@Resource
	private HealthExaminationExpertService healthExaminationExpertService;
	
	@PreAuthorize("authenticated and hasPermission('healthExaminationExpert','healthExaminationExpert:list')")
	@RequestMapping(value = "/adminList")
	public String adminList() {
		return "health_examination_expert/admin_health_examination_expert_list";
	}
	
	@PreAuthorize("authenticated and hasPermission('healthExaminationExpert','healthExaminationExpert:add')")
	@RequestMapping(value = "/singleAdd", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addSingle(@RequestBody HealthExaminationExpert healthExaminationExpert) {
		return newInstance("created",healthExaminationExpertService.create(healthExaminationExpert));
	}
	
	@PreAuthorize("authenticated and hasPermission('healthExaminationExpert','healthExaminationExpert:list')")
	@RequestMapping(value = "healthExaminationExpertsByPage", method = RequestMethod.POST, produces = PRODUCES_APPLICATION_JSON)
	@ResponseBody
	public JSONObject getPageableHealthExaminationExperts(@RequestBody Map<String, Object> map) {
		return newInstance(healthExaminationExpertService.findAll(PageUtils.parsePageable(map)));
	}
	
	@RequestMapping(value = "single", method = RequestMethod.POST, produces = PRODUCES_APPLICATION_JSON)
	@ResponseBody
	public JSONObject getSingleJSONObject(@RequestBody Map<String, Object> map) {
		return newInstance("obj",healthExaminationExpertService.findById(Long.valueOf(map.get("id").toString())));
	}
	
	@PreAuthorize("authenticated and hasPermission('healthExaminationExpert','healthExaminationExpert:update')")
	@RequestMapping(value = "singleUpdate", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject updateSingle(@RequestBody HealthExaminationExpert healthExaminationExpert) {
		return newInstance("updated",healthExaminationExpertService.update(healthExaminationExpert));
	}
	
	@PreAuthorize("authenticated and hasPermission('healthExaminationExpert','healthExaminationExpert:delete')")
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ResponseBody
	public JSONObject delete(@RequestBody String ids) {
		return newInstance("count",healthExaminationExpertService
				.delete(CommonConvertUtils.convertToLongArray(ids)));
	}
}
