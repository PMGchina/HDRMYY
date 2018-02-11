package com.beamofsoul.springboot.entity.query;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;

import com.beamofsoul.springboot.entity.MessageBoard;
import com.querydsl.core.types.Path;


/**
 * QMessageBoard is a Querydsl query type for MessageBoard
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMessageBoard extends EntityPathBase<MessageBoard> {

    private static final long serialVersionUID = 2020710691L;

    public static final QMessageBoard messageBoard = new QMessageBoard("messageBoard");

    public final QBaseAbstractEntity _super = new QBaseAbstractEntity(this);

    public final BooleanPath available = createBoolean("available");

    public final StringPath content = createString("content");
    
    public final StringPath medicalCard = createString("medicalCard");

    //inherited
    public final DateTimePath<java.util.Date> createDate = _super.createDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.util.Date> modifyDate = _super.modifyDate;

    public final StringPath title = createString("title");

    public QMessageBoard(String variable) {
        super(MessageBoard.class, forVariable(variable));
    }

    public QMessageBoard(Path<? extends MessageBoard> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMessageBoard(PathMetadata metadata) {
        super(MessageBoard.class, metadata);
    }

}

