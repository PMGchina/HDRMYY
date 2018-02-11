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
import com.beamofsoul.springboot.other.service.DepartmentService;

@Controller
@RequestMapping("/admin/department")
public class DepartmentController extends BaseAbstractController {

	@Resource
	private DepartmentService departmentService;
	
	@PreAuthorize("authenticated and hasPermission('department','department:list')")
	@RequestMapping(value = "/adminList")
	public String adminList() {
		return "department/admin_department_list";
	}
	
	@PreAuthorize("authenticated and hasPermission('department','department:list')")
	@PostResponseBody("departmentsByPage")
	public JSONObject getPageableDepartments(@RequestBody Map<String, Object> map) {
		return newInstance(departmentService.findAll(PageUtils.parsePageable(map)));
	}
}
