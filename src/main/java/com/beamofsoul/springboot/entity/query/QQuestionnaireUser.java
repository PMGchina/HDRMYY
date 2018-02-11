package com.beamofsoul.springboot.entity.query;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;

import com.beamofsoul.springboot.entity.QuestionnaireUser;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQuestionnaireUser is a Querydsl query type for QuestionnaireUser
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QQuestionnaireUser extends EntityPathBase<QuestionnaireUser> {

    private static final long serialVersionUID = 655317130L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuestionnaireUser questionnaireUser = new QQuestionnaireUser("questionnaireUser");

    public final QBaseAbstractEntity _super = new QBaseAbstractEntity(this);

    //inherited
    public final DateTimePath<java.util.Date> createDate = _super.createDate;

    public final StringPath departmentName = createString("departmentName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.util.Date> modifyDate = _super.modifyDate;

    public final QQuestionnaire questionnaire;

    public final QUser user;

    public QQuestionnaireUser(String variable) {
        this(QuestionnaireUser.class, forVariable(variable), INITS);
    }

    public QQuestionnaireUser(Path<? extends QuestionnaireUser> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQuestionnaireUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQuestionnaireUser(PathMetadata metadata, PathInits inits) {
        this(QuestionnaireUser.class, metadata, inits);
    }

    public QQuestionnaireUser(Class<? extends QuestionnaireUser> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.questionnaire = inits.isInitialized("questionnaire") ? new QQuestionnaire(forProperty("questionnaire")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}
