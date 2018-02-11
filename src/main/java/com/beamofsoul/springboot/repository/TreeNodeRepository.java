package com.beamofsoul.springboot.repository;

import org.springframework.stereotype.Repository;

import com.beamofsoul.springboot.entity.TreeNode;
import com.beamofsoul.springboot.management.repository.BaseMultielementRepository;

@Repository
public interface TreeNodeRepository extends BaseMultielementRepository<TreeNode,Long> {
	
	TreeNode findByName(String name);
}
