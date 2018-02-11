package com.beamofsoul.springboot.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.beamofsoul.springboot.entity.dto.UserRoleDTO;

public interface UserRoleRepositoryCustom {
	
	Page<UserRoleDTO> findAllUserRoleMapping(Pageable pageable);
	Page<UserRoleDTO> findUserRoleMappingByCondition(Pageable pageable, Object condition);
}
