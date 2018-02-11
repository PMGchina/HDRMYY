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
import com.beamofsoul.springboot.management.util.CommonConvertUtils;
import com.beamofsoul.springboot.management.util.PageUtils;
import com.beamofsoul.springboot.other.entity.HealthExaminationPackage;
import com.beamofsoul.springboot.other.service.HealthExaminationPackageService;

@Controller
@RequestMapping("/admin/healthExaminationPackage")
public class HealthExaminationPackageController extends BaseAbstractController {

	@Resource
	private HealthExaminationPackageService healthExaminationPackageService;
	
	@PreAuthorize("authenticated and hasPermission('healthExaminationPackage','healthExaminationPackage:list')")
	@RequestMapping(value = "/adminList")
	public String adminList() {
		return "health_examination_package/admin_health_examination_package_list";
	}
	
	@PreAuthorize("authenticated and hasPermission('healthExaminationPackage','healthExaminationPackage:add')")
	@RequestMapping(value = "/singleAdd", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addSingle(@RequestBody HealthExaminationPackage healthExaminationPackage) {
		return newInstance("created",healthExaminationPackageService.create(healthExaminationPackage));
	}
	
	@PreAuthorize("authenticated and hasPermission('healthExaminationPackage','healthExaminationPackage:list')")
	@RequestMapping(value = "healthExaminationPackagesByPage", method = RequestMethod.POST, produces = PRODUCES_APPLICATION_JSON)
	@ResponseBody
	public JSONObject getPageableHealthExaminationPackages(@RequestBody Map<String, Object> map) {
		return newInstance(healthExaminationPackageService.findAll(PageUtils.parsePageable(map)));
	}
	
	@RequestMapping(value = "single", method = RequestMethod.POST, produces = PRODUCES_APPLICATION_JSON)
	@ResponseBody
	public JSONObject getSingleJSONObject(@RequestBody Map<String, Object> map) {
		return newInstance("obj",healthExaminationPackageService.findById(Long.valueOf(map.get("id").toString())));
	}
	
	@PreAuthorize("authenticated and hasPermission('healthExaminationPackage','healthExaminationPackage:update')")
	@RequestMapping(value = "singleUpdate", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject updateSingle(@RequestBody HealthExaminationPackage healthExaminationPackage) {
		return newInstance("updated",healthExaminationPackageService.update(healthExaminationPackage));
	}
	
	@PreAuthorize("authenticated and hasPermission('healthExaminationPackage','healthExaminationPackage:delete')")
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ResponseBody
	public JSONObject delete(@RequestBody String ids) {
		return newInstance("count",healthExaminationPackageService
				.delete(CommonConvertUtils.convertToLongArray(ids)));
	}
}
