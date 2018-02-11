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
import com.beamofsoul.springboot.entity.DepartmentConstruction;
import com.beamofsoul.springboot.management.util.CommonConvertUtils;
import com.beamofsoul.springboot.management.util.PageUtils;
import com.beamofsoul.springboot.service.DepartmentConstructionService;

@Controller
@RequestMapping("/admin/departmentConstruction")
public class DepartmentConstructionController extends BaseAbstractController {

	@Resource
	private DepartmentConstructionService departmentConstructionService;
	
	@PreAuthorize("authenticated and hasPermission('departmentConstruction','departmentConstruction:list')")
	@RequestMapping(value = "/adminList")
	public String adminList() {
		return "department_construction/admin_department_construction_list";
	}
	
	@PreAuthorize("authenticated and hasPermission('departmentConstruction','departmentConstruction:add')")
	@RequestMapping(value = "/singleAdd", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addSingle(@RequestBody DepartmentConstruction departmentConstruction) {
		return newInstance("created",departmentConstructionService.create(departmentConstruction));
	}
	
	@PreAuthorize("authenticated and hasPermission('departmentConstruction','departmentConstruction:list')")
	@RequestMapping(value = "departmentConstructionsByPage", method = RequestMethod.POST, produces = PRODUCES_APPLICATION_JSON)
	@ResponseBody
	public JSONObject getPageableDepartmentConstructions(@RequestBody Map<String, Object> map) {
		return newInstance(departmentConstructionService.findAll(PageUtils.parsePageable(map)));
	}
	
	@RequestMapping(value = "single", method = RequestMethod.POST, produces = PRODUCES_APPLICATION_JSON)
	@ResponseBody
	public JSONObject getSingleJSONObject(@RequestBody Map<String, Object> map) {
		return newInstance("obj",departmentConstructionService.findById(Long.valueOf(map.get("id").toString())));
	}
	
	@PreAuthorize("authenticated and hasPermission('departmentConstruction','departmentConstruction:update')")
	@RequestMapping(value = "singleUpdate", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject updateSingle(@RequestBody DepartmentConstruction departmentConstruction) {
		return newInstance("updated",departmentConstructionService.update(departmentConstruction));
	}
	
	@PreAuthorize("authenticated and hasPermission('departmentConstruction','departmentConstruction:delete')")
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ResponseBody
	public JSONObject delete(@RequestBody String ids) {
		return newInstance("count",departmentConstructionService
				.delete(CommonConvertUtils.convertToLongArray(ids)));
	}
}
