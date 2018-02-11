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
import com.beamofsoul.springboot.entity.InpatientService;
import com.beamofsoul.springboot.management.util.CommonConvertUtils;
import com.beamofsoul.springboot.management.util.PageUtils;
import com.beamofsoul.springboot.service.InpatientServiceService;

@Controller
@RequestMapping("/admin/inpatientService")
public class InpatientServiceController extends BaseAbstractController {

	@Resource
	private InpatientServiceService inpatientServiceService;
	
	@PreAuthorize("authenticated and hasPermission('inpatientService','inpatientService:list')")
	@RequestMapping(value = "/adminList")
	public String adminList() {
		return "inpatient_service/admin_inpatient_service_list";
	}
	
	@PreAuthorize("authenticated and hasPermission('inpatientService','inpatientService:add')")
	@RequestMapping(value = "/singleAdd", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addSingle(@RequestBody InpatientService inpatientService) {
		return newInstance("created",inpatientServiceService.create(inpatientService));
	}
	
	@PreAuthorize("authenticated and hasPermission('inpatientService','inpatientService:list')")
	@RequestMapping(value = "inpatientServicesByPage", method = RequestMethod.POST, produces = PRODUCES_APPLICATION_JSON)
	@ResponseBody
	public JSONObject getPageableInpatientServices(@RequestBody Map<String, Object> map) {
		return newInstance(inpatientServiceService.findAll(PageUtils.parsePageable(map)));
	}
	
	@RequestMapping(value = "single", method = RequestMethod.POST, produces = PRODUCES_APPLICATION_JSON)
	@ResponseBody
	public JSONObject getSingleJSONObject(@RequestBody Map<String, Object> map) {
		return newInstance("obj",inpatientServiceService.findById(Long.valueOf(map.get("id").toString())));
	}
	
	@PreAuthorize("authenticated and hasPermission('inpatientService','inpatientService:update')")
	@RequestMapping(value = "singleUpdate", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject updateSingle(@RequestBody InpatientService inpatientService) {
		return newInstance("updated",inpatientServiceService.update(inpatientService));
	}
	
	@PreAuthorize("authenticated and hasPermission('inpatientService','inpatientService:delete')")
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ResponseBody
	public JSONObject delete(@RequestBody String ids) {
		return newInstance("count",inpatientServiceService
				.delete(CommonConvertUtils.convertToLongArray(ids)));
	}
}
