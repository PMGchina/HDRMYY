package com.beamofsoul.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.beamofsoul.springboot.entity.MessageBoard;
import com.querydsl.core.types.Predicate;

public interface MessageBoardService {

	MessageBoard create(MessageBoard messageBoard);
	MessageBoard update(MessageBoard messageBoard);
	long delete(Long... ids);

	MessageBoard findById(Long id);

	List<MessageBoard> findAll();
	Page<MessageBoard> findAll(Pageable pageable);
	Page<MessageBoard> findAll(Pageable pageable, Predicate predicate);
	List<MessageBoard> findAllAvailable();
}
