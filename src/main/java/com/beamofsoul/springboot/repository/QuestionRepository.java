package com.beamofsoul.springboot.repository;

import org.springframework.stereotype.Repository;

import com.beamofsoul.springboot.entity.Question;
import com.beamofsoul.springboot.management.repository.BaseMultielementRepository;

@Repository
public interface QuestionRepository extends BaseMultielementRepository<Question, Long> {

}
