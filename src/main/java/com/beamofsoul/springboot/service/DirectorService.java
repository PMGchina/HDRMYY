package com.beamofsoul.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.beamofsoul.springboot.entity.Director;
import com.querydsl.core.types.Predicate;

public interface DirectorService {

	Director create(Director director);
	Director update(Director director);
	long delete(Long... ids);

	Director findById(Long id);

	List<Director> findAll();
	Page<Director> findAll(Pageable pageable);
	Page<Director> findAll(Pageable pageable, Predicate predicate);
}
