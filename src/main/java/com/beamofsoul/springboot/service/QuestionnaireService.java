package com.beamofsoul.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.beamofsoul.springboot.entity.Questionnaire;
import com.beamofsoul.springboot.entity.User;
import com.querydsl.core.types.Predicate;

public interface QuestionnaireService {

    Questionnaire create(Questionnaire questionnaire);
    Long create(String name, String description, String questions, User user);
    Questionnaire update(Questionnaire questionnaire);
    long update(Long id, String name, String description, String questions);
    long delete(Long... ids);

    Questionnaire findById(Long id);
    List<Questionnaire> findAll();
    Page<Questionnaire> findAll(Pageable pageable);
    Page<Questionnaire> findAll(Pageable pageable, Predicate predicate);
    List<Questionnaire> findAllUnpublished();
    List<Questionnaire> findAllUnpublishedAndAvailable();
    List<Questionnaire> findAllPublishedAndAvailable();
    
    long publish(Long... ids);
    long unpublish(Long... ids);
    long available(Long... ids);
    long unavailable(Long... ids);
    boolean isPublished(Long... ids);
    boolean isAnswered(Long... ids);
    boolean increaseNumberOfAnswers(Long questionnaireId);
}
