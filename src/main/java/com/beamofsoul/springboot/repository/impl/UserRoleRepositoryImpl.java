package com.beamofsoul.springboot.repository.impl;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

import com.beamofsoul.springboot.entity.UserRole;
import com.beamofsoul.springboot.entity.dto.UserRoleDTO;
import com.beamofsoul.springboot.entity.query.QUserRole;
import com.beamofsoul.springboot.management.util.PageImpl;
import com.beamofsoul.springboot.repository.UserRoleRepositoryCustom;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;

import lombok.NonNull;

@NoRepositoryBean
public class UserRoleRepositoryImpl implements UserRoleRepositoryCustom {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public Page<UserRoleDTO> findAllUserRoleMapping(Pageable pageable) {
		JPAQuery<UserRoleDTO> query = new JPAQuery<UserRoleDTO>(entityManager);
		QUserRole userRole = QUserRole.userRole;
		QueryResults<UserRoleDTO> qr = 
				query.select(Projections.constructor(UserRoleDTO.class,
						userRole.id,
						userRole.user.id,
						userRole.user.username,
						userRole.user.nickname,
						userRole.role.id,
						userRole.role.name))
					.from(userRole)
					.offset(pageable.getOffset()).limit(pageable.getPageSize())
					.orderBy(userRole.user.id.asc(),userRole.role.id.asc())
					.fetchResults();
		return new PageImpl<>(qr.getResults(),pageable,qr.getTotal());
	}
	
	@Override
	public Page<UserRoleDTO> findUserRoleMappingByCondition(Pageable pageable, @NonNull Object condition) {
		JPAQuery<UserRole> query = new JPAQuery<UserRole>(entityManager);
		QUserRole userRole = QUserRole.userRole;
		Predicate predicate = userRole.role.id.eq(Long.valueOf(condition.toString()));
		QueryResults<UserRoleDTO> qr =  
				query.select(Projections.constructor(UserRoleDTO.class,
						userRole.id,
						userRole.user.id,
						userRole.user.username,
						userRole.user.nickname,
						userRole.role.id,
						userRole.role.name))
					.from(userRole)
					.where(predicate)
					.offset(pageable.getOffset()).limit(pageable.getPageSize())
					.orderBy(userRole.user.id.asc(),userRole.role.id.asc())
					.fetchResults();
		return new PageImpl<>(qr.getResults(),pageable,qr.getTotal());
	}
}
