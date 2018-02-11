package com.beamofsoul.springboot.repository;

import org.springframework.stereotype.Repository;

import com.beamofsoul.springboot.entity.Answer;
import com.beamofsoul.springboot.management.repository.BaseMultielementRepository;

@Repository
public interface AnswerRepository extends BaseMultielementRepository<Answer, Long>, AnswerRepositoryCustom<Answer, Long> {

}
