package com.beamofsoul.springboot.entity.query;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;

import com.beamofsoul.springboot.entity.Questionnaire;
import com.querydsl.core.types.Path;


/**
 * QQuestionnaire is a Querydsl query type for Questionnaire
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QQuestionnaire extends EntityPathBase<Questionnaire> {

    private static final long serialVersionUID = -750641121L;

    public static final QQuestionnaire questionnaire = new QQuestionnaire("questionnaire");

    public final QBaseAbstractEntity _super = new QBaseAbstractEntity(this);

    //inherited
    public final DateTimePath<java.util.Date> createDate = _super.createDate;

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath available = createBoolean("available");

    //inherited
    public final DateTimePath<java.util.Date> modifyDate = _super.modifyDate;

    public final StringPath name = createString("name");

    public final NumberPath<Integer> numberOfAnswers = createNumber("numberOfAnswers", Integer.class);

    public final BooleanPath published = createBoolean("published");

    public QQuestionnaire(String variable) {
        super(Questionnaire.class, forVariable(variable));
    }

    public QQuestionnaire(Path<? extends Questionnaire> path) {
        super(path.getType(), path.getMetadata());
    }

    public QQuestionnaire(PathMetadata metadata) {
        super(Questionnaire.class, metadata);
    }

}

