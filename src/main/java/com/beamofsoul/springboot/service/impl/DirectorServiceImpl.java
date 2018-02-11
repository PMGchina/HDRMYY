package com.beamofsoul.springboot.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beamofsoul.springboot.entity.Director;
import com.beamofsoul.springboot.repository.DirectorRepository;
import com.beamofsoul.springboot.service.DirectorService;
import com.querydsl.core.types.Predicate;

@Service("directorService")
public class DirectorServiceImpl extends BaseAbstractServiceImpl implements DirectorService {

	@Autowired
	private DirectorRepository directorRepository;

	@Override
	public Director create(Director director) {
		return directorRepository.save(director);
	}

	@Override
	public Director update(Director director) {
		Director originalDirector = directorRepository.findOne(director.getId());
		BeanUtils.copyProperties(director, originalDirector);
		return directorRepository.save(originalDirector);
	}

	@Override
	@Transactional
	public long delete(Long... ids) {
		return directorRepository.deleteByIds(ids);
	}

	@Override
	public Director findById(Long id) {
		return directorRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Director> findAll() {
		return directorRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Director> findAll(Pageable pageable) {
		return directorRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Director> findAll(Pageable pageable, Predicate predicate) {
		return directorRepository.findAll(predicate, pageable);
	}
}
