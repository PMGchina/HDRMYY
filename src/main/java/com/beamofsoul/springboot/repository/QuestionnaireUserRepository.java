package com.beamofsoul.springboot.repository;

import org.springframework.stereotype.Repository;

import com.beamofsoul.springboot.entity.QuestionnaireUser;
import com.beamofsoul.springboot.management.repository.BaseMultielementRepository;

@Repository
public interface QuestionnaireUserRepository extends BaseMultielementRepository<QuestionnaireUser, Long> {
	
}
