package com.beamofsoul.springboot.service;

import java.util.Collection;
import java.util.List;

import com.beamofsoul.springboot.entity.RolePermission;
import com.beamofsoul.springboot.entity.dto.RolePermissionDTO;
import com.beamofsoul.springboot.entity.dto.RolePermissionMappingDTO;

public interface RolePermissionService {

	List<RolePermissionDTO> findAllRolePermissionMapping();
	boolean updateRolePermissionMapping(RolePermissionMappingDTO rolePermissionMappingDTO);
	Collection<RolePermission> bulkCreate(Collection<RolePermission> rolePermissions);
	Long deleteByRoleIdAndPermissionIds(Long roleId, Collection<Long> permissionIds);
	void refreshRolePermissionMapping();
}
