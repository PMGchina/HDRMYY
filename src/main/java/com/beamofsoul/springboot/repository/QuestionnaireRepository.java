package com.beamofsoul.springboot.repository;

import org.springframework.stereotype.Repository;

import com.beamofsoul.springboot.entity.Questionnaire;
import com.beamofsoul.springboot.management.repository.BaseMultielementRepository;

@Repository
public interface QuestionnaireRepository extends BaseMultielementRepository<Questionnaire, Long> {

}
