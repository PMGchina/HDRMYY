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
import com.beamofsoul.springboot.entity.GuidingTreat;
import com.beamofsoul.springboot.management.util.CommonConvertUtils;
import com.beamofsoul.springboot.management.util.PageUtils;
import com.beamofsoul.springboot.service.GuidingTreatService;

@Controller
@RequestMapping("/admin/guidingTreat")
public class GuidingTreatController extends BaseAbstractController {

	@Resource
	private GuidingTreatService guidingTreatService;
	
	@PreAuthorize("authenticated and hasPermission('guidingTreat','guidingTreat:list')")
	@RequestMapping(value = "/adminList")
	public String adminList() {
		return "guiding_treat/admin_guiding_treat_list";
	}
	
	@PreAuthorize("authenticated and hasPermission('guidingTreat','guidingTreat:add')")
	@RequestMapping(value = "/singleAdd", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addSingle(@RequestBody GuidingTreat guidingTreat) {
		return newInstance("created",guidingTreatService.create(guidingTreat));
	}
	
	@PreAuthorize("authenticated and hasPermission('guidingTreat','guidingTreat:list')")
	@RequestMapping(value = "guidingTreatsByPage", method = RequestMethod.POST, produces = PRODUCES_APPLICATION_JSON)
	@ResponseBody
	public JSONObject getPageableGuidingTreats(@RequestBody Map<String, Object> map) {
		return newInstance(guidingTreatService.findAll(PageUtils.parsePageable(map)));
	}
	
	@RequestMapping(value = "single", method = RequestMethod.POST, produces = PRODUCES_APPLICATION_JSON)
	@ResponseBody
	public JSONObject getSingleJSONObject(@RequestBody Map<String, Object> map) {
		return newInstance("obj",guidingTreatService.findById(Long.valueOf(map.get("id").toString())));
	}
	
	@PreAuthorize("authenticated and hasPermission('guidingTreat','guidingTreat:update')")
	@RequestMapping(value = "singleUpdate", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject updateSingle(@RequestBody GuidingTreat guidingTreat) {
		return newInstance("updated",guidingTreatService.update(guidingTreat));
	}
	
	@PreAuthorize("authenticated and hasPermission('guidingTreat','guidingTreat:delete')")
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ResponseBody
	public JSONObject delete(@RequestBody String ids) {
		return newInstance("count",guidingTreatService
				.delete(CommonConvertUtils.convertToLongArray(ids)));
	}
}
