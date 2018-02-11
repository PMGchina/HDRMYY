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
import com.beamofsoul.springboot.entity.HospitalEvent;
import com.beamofsoul.springboot.management.util.CommonConvertUtils;
import com.beamofsoul.springboot.management.util.PageUtils;
import com.beamofsoul.springboot.service.HospitalEventService;

@Controller
@RequestMapping("/admin/hospitalEvent")
public class HospitalEventController extends BaseAbstractController {

	@Resource
	private HospitalEventService hospitalEventService;
	
	@PreAuthorize("authenticated and hasPermission('hospitalEvent','hospitalEvent:list')")
	@RequestMapping(value = "/adminList")
	public String adminList() {
		return "hospital_event/admin_hospital_event_list";
	}
	
	@PreAuthorize("authenticated and hasPermission('hospitalEvent','hospitalEvent:add')")
	@RequestMapping(value = "/singleAdd", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addSingle(@RequestBody HospitalEvent hospitalEvent) {
		return newInstance("created",hospitalEventService.create(hospitalEvent));
	}
	
	@PreAuthorize("authenticated and hasPermission('hospitalEvent','hospitalEvent:list')")
	@RequestMapping(value = "hospitalEventsByPage", method = RequestMethod.POST, produces = PRODUCES_APPLICATION_JSON)
	@ResponseBody
	public JSONObject getPageableHospitalEvents(@RequestBody Map<String, Object> map) {
		return newInstance(hospitalEventService.findAll(PageUtils.parsePageable(map)));
	}
	
	@RequestMapping(value = "single", method = RequestMethod.POST, produces = PRODUCES_APPLICATION_JSON)
	@ResponseBody
	public JSONObject getSingleJSONObject(@RequestBody Map<String, Object> map) {
		return newInstance("obj",hospitalEventService.findById(Long.valueOf(map.get("id").toString())));
	}
	
	@PreAuthorize("authenticated and hasPermission('hospitalEvent','hospitalEvent:update')")
	@RequestMapping(value = "singleUpdate", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject updateSingle(@RequestBody HospitalEvent hospitalEvent) {
		return newInstance("updated",hospitalEventService.update(hospitalEvent));
	}
	
	@PreAuthorize("authenticated and hasPermission('hospitalEvent','hospitalEvent:delete')")
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ResponseBody
	public JSONObject delete(@RequestBody String ids) {
		return newInstance("count",hospitalEventService
				.delete(CommonConvertUtils.convertToLongArray(ids)));
	}
}
