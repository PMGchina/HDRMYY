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
import com.beamofsoul.springboot.entity.HealthEducation;
import com.beamofsoul.springboot.management.util.CommonConvertUtils;
import com.beamofsoul.springboot.management.util.PageUtils;
import com.beamofsoul.springboot.service.HealthEducationService;

@Controller
@RequestMapping("/admin/healthEducation")
public class HealthEducationController extends BaseAbstractController {

	@Resource
	private HealthEducationService healthEducationService;
	
	@PreAuthorize("authenticated and hasPermission('healthEducation','healthEducation:list')")
	@RequestMapping(value = "/adminList")
	public String adminList() {
		return "health_education/admin_health_education_list";
	}
	
	@PreAuthorize("authenticated and hasPermission('healthEducation','healthEducation:add')")
	@RequestMapping(value = "/singleAdd", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addSingle(@RequestBody HealthEducation healthEducation) {
		return newInstance("created",healthEducationService.create(healthEducation));
	}
	
	@PreAuthorize("authenticated and hasPermission('healthEducation','healthEducation:list')")
	@RequestMapping(value = "healthEducationsByPage", method = RequestMethod.POST, produces = PRODUCES_APPLICATION_JSON)
	@ResponseBody
	public JSONObject getPageableHealthEducations(@RequestBody Map<String, Object> map) {
		return newInstance(healthEducationService.findAll(PageUtils.parsePageable(map)));
	}
	
	@RequestMapping(value = "single", method = RequestMethod.POST, produces = PRODUCES_APPLICATION_JSON)
	@ResponseBody
	public JSONObject getSingleJSONObject(@RequestBody Map<String, Object> map) {
		return newInstance("obj",healthEducationService.findById(Long.valueOf(map.get("id").toString())));
	}
	
	@PreAuthorize("authenticated and hasPermission('healthEducation','healthEducation:update')")
	@RequestMapping(value = "singleUpdate", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject updateSingle(@RequestBody HealthEducation healthEducation) {
		return newInstance("updated",healthEducationService.update(healthEducation));
	}
	
	@PreAuthorize("authenticated and hasPermission('healthEducation','healthEducation:delete')")
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ResponseBody
	public JSONObject delete(@RequestBody String ids) {
		return newInstance("count",healthEducationService
				.delete(CommonConvertUtils.convertToLongArray(ids)));
	}
}
