package com.beamofsoul.springboot.controller;

import static com.beamofsoul.springboot.management.util.JSONUtils.newInstance;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.beamofsoul.springboot.management.mvc.PostResponseBody;
import com.beamofsoul.springboot.management.util.PageUtils;
import com.beamofsoul.springboot.other.service.SchedulingService;

@Controller
@RequestMapping("/admin/scheduling")
public class SchedulingController extends BaseAbstractController {

	@Resource
	private SchedulingService schedulingService;
	
	@PreAuthorize("authenticated and hasPermission('scheduling','scheduling:list')")
	@RequestMapping(value = "/adminList")
	public String adminList() {
		return "scheduling/admin_scheduling_list";
	}
	
	@PreAuthorize("authenticated and hasPermission('scheduling','scheduling:list')")
	@PostResponseBody("schedulingsByPage")
	public JSONObject getPageableSchedulings(@RequestBody Map<String, Object> map) {
		return newInstance(schedulingService.findAll(PageUtils.parsePageable(map)));
	}
}
