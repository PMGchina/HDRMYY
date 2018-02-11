package com.beamofsoul.springboot.repository;

import org.springframework.stereotype.Repository;

import com.beamofsoul.springboot.entity.MessageBoard;
import com.beamofsoul.springboot.management.repository.BaseMultielementRepository;

@Repository
public interface MessageBoardRepository extends BaseMultielementRepository<MessageBoard, Long> {

}
