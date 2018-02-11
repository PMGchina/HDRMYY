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
import com.beamofsoul.springboot.management.control.JSON;
import com.beamofsoul.springboot.management.mvc.GetResponseBody;
import com.beamofsoul.springboot.management.util.CommonConvertUtils;
import com.beamofsoul.springboot.management.util.PageUtils;
import com.beamofsoul.springboot.other.entity.HealthExaminationItem;
import com.beamofsoul.springboot.other.service.HealthExaminationItemService;

@Controller
@RequestMapping("/admin/healthExaminationItem")
public class HealthExaminationItemController extends BaseAbstractController {

	@Resource
	private HealthExaminationItemService healthExaminationItemService;
	
	@PreAuthorize("authenticated and hasPermission('healthExaminationItem','healthExaminationItem:list')")
	@RequestMapping(value = "/adminList")
	public String adminList() {
		return "health_examination_item/admin_health_examination_item_list";
	}
	
	@PreAuthorize("authenticated and hasPermission('healthExaminationItem','healthExaminationItem:add')")
	@RequestMapping(value = "/singleAdd", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addSingle(@RequestBody HealthExaminationItem healthExaminationItem) {
		return newInstance("created",healthExaminationItemService.create(healthExaminationItem));
	}
	
	@PreAuthorize("authenticated and hasPermission('healthExaminationItem','healthExaminationItem:list')")
	@RequestMapping(value = "healthExaminationItemsByPage", method = RequestMethod.POST, produces = PRODUCES_APPLICATION_JSON)
	@ResponseBody
	public JSONObject getPageableHealthExaminationItems(@RequestBody Map<String, Object> map) {
		return newInstance(healthExaminationItemService.findAll(PageUtils.parsePageable(map)));
	}
	
	@RequestMapping(value = "single", method = RequestMethod.POST, produces = PRODUCES_APPLICATION_JSON)
	@ResponseBody
	public JSONObject getSingleJSONObject(@RequestBody Map<String, Object> map) {
		return newInstance("obj",healthExaminationItemService.findById(Long.valueOf(map.get("id").toString())));
	}
	
	@PreAuthorize("authenticated and hasPermission('healthExaminationItem','healthExaminationItem:update')")
	@RequestMapping(value = "singleUpdate", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject updateSingle(@RequestBody HealthExaminationItem healthExaminationItem) {
		return newInstance("updated",healthExaminationItemService.update(healthExaminationItem));
	}
	
	@PreAuthorize("authenticated and hasPermission('healthExaminationItem','healthExaminationItem:delete')")
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ResponseBody
	public JSONObject delete(@RequestBody String ids) {
		return newInstance("count",healthExaminationItemService
				.delete(CommonConvertUtils.convertToLongArray(ids)));
	}
	
	@GetResponseBody("all4select")
	@JSON(include = "id,name", type = HealthExaminationItem.class)
	public JSONObject getAll4select() {
		return newInstance("all",healthExaminationItemService.findAll());
	}
}
