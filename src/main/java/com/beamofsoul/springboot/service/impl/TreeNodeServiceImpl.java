package com.beamofsoul.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.beamofsoul.springboot.entity.TreeNode;
import com.beamofsoul.springboot.repository.TreeNodeRepository;
import com.beamofsoul.springboot.service.TreeNodeService;
import com.querydsl.core.types.Predicate;

@Service("treeNodeService")
public class TreeNodeServiceImpl extends BaseAbstractServiceImpl implements TreeNodeService {

	@Autowired
	private TreeNodeRepository treeNodeRepository;

	@Override
	public TreeNode findById(Long id) {
		return treeNodeRepository.findOne(id);
	}

	@Override
	public TreeNode findByName(String name) {
		TreeNode node = treeNodeRepository.findByName(name);
		return node;
	}

	@Override
	public List<TreeNode> findAll() {
		return treeNodeRepository.findAll();
	}

	@Override
	public Page<TreeNode> findAll(Pageable pageable) {
		return treeNodeRepository.findAll(pageable);
	}

	@Override
	public Page<TreeNode> findAll(Pageable pageable, Predicate predicate) {
		return treeNodeRepository.findAll(predicate, pageable);
	}

}
