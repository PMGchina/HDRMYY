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
import com.beamofsoul.springboot.entity.Director;
import com.beamofsoul.springboot.management.util.CommonConvertUtils;
import com.beamofsoul.springboot.management.util.PageUtils;
import com.beamofsoul.springboot.service.DirectorService;

@Controller
@RequestMapping("/admin/director")
public class DirectorController extends BaseAbstractController {

	@Resource
	private DirectorService directorService;
	
	@PreAuthorize("authenticated and hasPermission('director','director:list')")
	@RequestMapping(value = "/adminList")
	public String adminList() {
		return "director/admin_director_list";
	}
	
	@PreAuthorize("authenticated and hasPermission('director','director:add')")
	@RequestMapping(value = "/singleAdd", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addSingle(@RequestBody Director director) {
		return newInstance("created",directorService.create(director));
	}
	
	@PreAuthorize("authenticated and hasPermission('director','director:list')")
	@RequestMapping(value = "directorsByPage", method = RequestMethod.POST, produces = PRODUCES_APPLICATION_JSON)
	@ResponseBody
	public JSONObject getPageableDirectors(@RequestBody Map<String, Object> map) {
		return newInstance(directorService.findAll(PageUtils.parsePageable(map)));
	}
	
	@RequestMapping(value = "single", method = RequestMethod.POST, produces = PRODUCES_APPLICATION_JSON)
	@ResponseBody
	public JSONObject getSingleJSONObject(@RequestBody Map<String, Object> map) {
		return newInstance("obj",directorService.findById(Long.valueOf(map.get("id").toString())));
	}
	
	@PreAuthorize("authenticated and hasPermission('director','director:update')")
	@RequestMapping(value = "singleUpdate", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject updateSingle(@RequestBody Director director) {
		return newInstance("updated",directorService.update(director));
	}
	
	@PreAuthorize("authenticated and hasPermission('director','director:delete')")
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ResponseBody
	public JSONObject delete(@RequestBody String ids) {
		return newInstance("count",directorService
				.delete(CommonConvertUtils.convertToLongArray(ids)));
	}
}
