package com.web.curation.model.generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.StringPath;
import com.web.curation.model.user.Subreview;


/**
 * QSubreview is a Querydsl query type for Subreview
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSubreview extends EntityPathBase<Subreview> {

    private static final long serialVersionUID = -884814388L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSubreview subreview = new QSubreview("subreview");

    public final StringPath content = createString("content");

    public final NumberPath<Long> num = createNumber("num", Long.class);

    public final QReview review;

    public final QUser user;

    public final StringPath wdate = createString("wdate");

    public QSubreview(String variable) {
        this(Subreview.class, forVariable(variable), INITS);
    }

    public QSubreview(Path<? extends Subreview> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSubreview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSubreview(PathMetadata metadata, PathInits inits) {
        this(Subreview.class, metadata, inits);
    }

    public QSubreview(Class<? extends Subreview> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.review = inits.isInitialized("review") ? new QReview(forProperty("review"), inits.get("review")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

