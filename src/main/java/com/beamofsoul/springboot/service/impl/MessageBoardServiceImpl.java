package com.beamofsoul.springboot.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beamofsoul.springboot.entity.MessageBoard;
import com.beamofsoul.springboot.entity.query.QMessageBoard;
import com.beamofsoul.springboot.repository.MessageBoardRepository;
import com.beamofsoul.springboot.service.MessageBoardService;
import com.querydsl.core.types.Predicate;

@Service("messageBoardService")
public class MessageBoardServiceImpl extends BaseAbstractServiceImpl implements MessageBoardService {

	@Autowired
	private MessageBoardRepository messageBoardRepository;

	@Override
	public MessageBoard create(MessageBoard messageBoard) {
		return messageBoardRepository.save(messageBoard);
	}

	@Override
	public MessageBoard update(MessageBoard messageBoard) {
		MessageBoard originalMessageBoard = messageBoardRepository.findOne(messageBoard.getId());
		BeanUtils.copyProperties(messageBoard, originalMessageBoard);
		return messageBoardRepository.save(originalMessageBoard);
	}

	@Override
	@Transactional
	public long delete(Long... ids) {
		return messageBoardRepository.deleteByIds(ids);
	}

	@Override
	public MessageBoard findById(Long id) {
		return messageBoardRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MessageBoard> findAll() {
		QMessageBoard $ = new QMessageBoard("MessageBoard");
		return messageBoardRepository.findByPredicate($.createDate.month().eq(LocalDate.now().getMonthValue()));
	}

	@Override
	@Transactional(readOnly = true)
	public Page<MessageBoard> findAll(Pageable pageable) {
		return messageBoardRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<MessageBoard> findAll(Pageable pageable, Predicate predicate) {
		return messageBoardRepository.findAll(predicate, pageable);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MessageBoard> findAllAvailable() {
		return messageBoardRepository.findByPredicate(new QMessageBoard("MessageBoard").available.eq(true));
	}
}
