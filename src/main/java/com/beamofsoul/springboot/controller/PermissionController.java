package com.beamofsoul.springboot.controller;

import static com.beamofsoul.springboot.management.util.JSONUtils.formatAndParseObject;
import static com.beamofsoul.springboot.management.util.JSONUtils.newInstance;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.beamofsoul.springboot.entity.Permission;
import com.beamofsoul.springboot.management.control.JSON;
import com.beamofsoul.springboot.management.security.CustomPermissionEvaluator;
import com.beamofsoul.springboot.management.util.CommonConvertUtils;
import com.beamofsoul.springboot.management.util.PageUtils;
import com.beamofsoul.springboot.service.PermissionService;

@Controller
@RequestMapping("/admin/permission")
public class PermissionController extends BaseAbstractController {

	@Resource
	private PermissionService permissionService;
	
	@Resource
	private CustomPermissionEvaluator customPermissionEvaluator;
	
	@PreAuthorize("authenticated and hasPermission('permission','permission:list')")
	@RequestMapping(value = "/adminList")
	public String adminList() {
		return "permission/admin_permission_list";
	}
	
	@PreAuthorize("authenticated and hasPermission('permission','permission:add')")
	@RequestMapping(value = "/add")
	public String add() {
		return "role/admin_permission_add";
	}
	
	@PreAuthorize("authenticated and hasPermission('permission','permission:add')")
	@RequestMapping(value = "/singleAdd", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addSingle(@RequestBody Permission permission) {
		return newInstance("created",permissionService.createPermission(permission));
	}
	
	@PreAuthorize("authenticated and hasPermission('permission','permission:list')")
	@RequestMapping(value = "permissionsByPage", method = RequestMethod.POST, produces = PRODUCES_APPLICATION_JSON)
	@ResponseBody
	public JSONObject getPageableData(@RequestBody Map<String, Object> map) {
		Object condition = map.get("condition");
		Pageable pageable = PageUtils.parsePageable(map);
		return newInstance(permissionService.findAll(pageable, 
				condition == null ? null : 
					permissionService.onSearch(formatAndParseObject(condition.toString()))));
	}
	
	@RequestMapping(value = "allAvailable", method = RequestMethod.GET, produces = PRODUCES_APPLICATION_JSON)
	@ResponseBody
	@JSON(type=Permission.class, include="id,name,group")
	public JSONObject getAllAvailableData() {
		return newInstance("all",permissionService.findAllAvailableData());
	}
	
	@RequestMapping(value = "single", method = RequestMethod.POST, produces = PRODUCES_APPLICATION_JSON)
	@ResponseBody
	public JSONObject getSingleJSONObject(@RequestBody Map<String, Object> map) {
		return newInstance("obj",permissionService.findById(Long.valueOf(map.get("id").toString())));
	}
	
	@PreAuthorize("authenticated and hasPermission('permission','permission:update')")
	@RequestMapping(value = "singleUpdate", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject updateSingle(@RequestBody Permission permission) {
		return newInstance("updated",permissionService.update(permission));
	}
	
	@PreAuthorize("authenticated and hasPermission('permission','permission:delete')")
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ResponseBody
	public JSONObject delete(@RequestBody String ids) {
		return newInstance("count",permissionService
				.deletePermissions(CommonConvertUtils.convertToLongArray(ids)));
	}
	
	@RequestMapping(value = "/checkPermissionNameUnique", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject checkRoleNameUnique(@RequestBody Map<String, Object> map) {
		String permissionName = map.get("data").toString();
		Long permissionId = map.containsKey("id") ? Long.valueOf(map.get("id").toString()) : null;
		return newInstance("isUnique", permissionService.checkPermissionNameUnique(permissionName, permissionId));
	}
	
	@RequestMapping(value = "/isUsedPermissions", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject isUsedRoles(@RequestBody String permissionIds) {
		return newInstance("isUsed", permissionService.isUsedPermissions(permissionIds));
	}
	
	@RequestMapping(value = "/hasPermission", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject hasPermission(@RequestBody Object action) {
		return newInstance("isPermissive", customPermissionEvaluator.hasPermission(SecurityContextHolder.getContext().getAuthentication(), null, action));
	}
}
