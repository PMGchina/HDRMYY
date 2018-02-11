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
import com.beamofsoul.springboot.entity.SeekingTreatStrategy;
import com.beamofsoul.springboot.management.util.CommonConvertUtils;
import com.beamofsoul.springboot.management.util.PageUtils;
import com.beamofsoul.springboot.service.SeekingTreatStrategyService;

@Controller
@RequestMapping("/admin/seekingTreatStrategy")
public class SeekingTreatStrategyController extends BaseAbstractController {

	@Resource
	private SeekingTreatStrategyService seekingTreatStrategyService;
	
	@PreAuthorize("authenticated and hasPermission('seekingTreatStrategy','seekingTreatStrategy:list')")
	@RequestMapping(value = "/adminList")
	public String adminList() {
		return "seeking_treat_strategy/admin_seeking_treat_strategy_list";
	}
	
	@PreAuthorize("authenticated and hasPermission('seekingTreatStrategy','seekingTreatStrategy:add')")
	@RequestMapping(value = "/singleAdd", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addSingle(@RequestBody SeekingTreatStrategy seekingTreatStrategy) {
		return newInstance("created",seekingTreatStrategyService.create(seekingTreatStrategy));
	}
	
	@PreAuthorize("authenticated and hasPermission('seekingTreatStrategy','seekingTreatStrategy:list')")
	@RequestMapping(value = "seekingTreatStrategysByPage", method = RequestMethod.POST, produces = PRODUCES_APPLICATION_JSON)
	@ResponseBody
	public JSONObject getPageableSeekingTreatStrategys(@RequestBody Map<String, Object> map) {
		return newInstance(seekingTreatStrategyService.findAll(PageUtils.parsePageable(map)));
	}
	
	@RequestMapping(value = "single", method = RequestMethod.POST, produces = PRODUCES_APPLICATION_JSON)
	@ResponseBody
	public JSONObject getSingleJSONObject(@RequestBody Map<String, Object> map) {
		return newInstance("obj",seekingTreatStrategyService.findById(Long.valueOf(map.get("id").toString())));
	}
	
	@PreAuthorize("authenticated and hasPermission('seekingTreatStrategy','seekingTreatStrategy:update')")
	@RequestMapping(value = "singleUpdate", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject updateSingle(@RequestBody SeekingTreatStrategy seekingTreatStrategy) {
		return newInstance("updated",seekingTreatStrategyService.update(seekingTreatStrategy));
	}
	
	@PreAuthorize("authenticated and hasPermission('seekingTreatStrategy','seekingTreatStrategy:delete')")
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ResponseBody
	public JSONObject delete(@RequestBody String ids) {
		return newInstance("count",seekingTreatStrategyService
				.delete(CommonConvertUtils.convertToLongArray(ids)));
	}
}
