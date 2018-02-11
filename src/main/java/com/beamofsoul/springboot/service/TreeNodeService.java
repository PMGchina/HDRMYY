package com.beamofsoul.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.beamofsoul.springboot.entity.TreeNode;
import com.querydsl.core.types.Predicate;

public interface TreeNodeService {
	
	public TreeNode findById(Long userId);
	public TreeNode findByName(String name);
	 
	public List<TreeNode> findAll();
	public Page<TreeNode> findAll(Pageable pageable);
	public Page<TreeNode> findAll(Pageable pageable, Predicate predicate);
}
