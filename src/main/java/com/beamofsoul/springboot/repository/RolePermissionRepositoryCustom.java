package com.beamofsoul.springboot.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.beamofsoul.springboot.entity.dto.RolePermissionDTO;

public interface RolePermissionRepositoryCustom<T,ID> {

	List<RolePermissionDTO> findAllRolePermissionMapping(EntityManager entityManager);
}
