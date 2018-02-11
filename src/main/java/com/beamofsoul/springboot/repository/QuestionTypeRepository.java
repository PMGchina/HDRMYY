package com.beamofsoul.springboot.repository;

import org.springframework.stereotype.Repository;

import com.beamofsoul.springboot.entity.QuestionType;
import com.beamofsoul.springboot.management.repository.BaseMultielementRepository;

@Repository
public interface QuestionTypeRepository extends BaseMultielementRepository<QuestionType, Long> {

}
