package com.beamofsoul.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.fastjson.JSONObject;
import com.beamofsoul.springboot.entity.Permission;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;

public interface PermissionService {

	Permission createPermission(Permission permission);
	Permission update(Permission permission);
	void deletePermission(Long permissionId);
	long deletePermissions(Long[] ids);
	
	List<Permission> findAll();
	Page<Permission> findAll(Pageable pageable);
	Page<Permission> findAll(Pageable pageable, Predicate predicate);
	BooleanExpression onSearch(JSONObject content);
	List<Permission> findAllAvailableData();
	Permission findByName(String name);
	Permission findById(Long id);

	boolean checkPermissionNameUnique(String permissionName, Long permissionId);
	boolean isUsedPermissions(String permissionIds);

}
