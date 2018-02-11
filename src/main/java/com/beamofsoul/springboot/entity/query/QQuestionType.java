package com.beamofsoul.springboot.entity.query;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;

import com.beamofsoul.springboot.entity.QuestionType;
import com.querydsl.core.types.Path;


/**
 * QQuestionType is a Querydsl query type for QuestionType
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QQuestionType extends EntityPathBase<QuestionType> {

    private static final long serialVersionUID = -1133344188L;

    public static final QQuestionType questionType = new QQuestionType("questionType");

    public final QBaseAbstractEntity _super = new QBaseAbstractEntity(this);

    //inherited
    public final DateTimePath<java.util.Date> createDate = _super.createDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.util.Date> modifyDate = _super.modifyDate;

    public final StringPath name = createString("name");

    public QQuestionType(String variable) {
        super(QuestionType.class, forVariable(variable));
    }

    public QQuestionType(Path<? extends QuestionType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QQuestionType(PathMetadata metadata) {
        super(QuestionType.class, metadata);
    }

}

