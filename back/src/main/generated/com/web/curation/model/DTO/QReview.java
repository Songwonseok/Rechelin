package com.web.curation.model.DTO;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReview is a Querydsl query type for Review
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QReview extends EntityPathBase<Review> {

    private static final long serialVersionUID = 1648113854L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReview review = new QReview("review");

    public final StringPath hashtag = createString("hashtag");

    public final NumberPath<Integer> kindness = createNumber("kindness", Integer.class);

    public final StringPath picture = createString("picture");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final NumberPath<Long> rnum = createNumber("rnum", Long.class);

    public final QStore store;

    public final StringPath str = createString("str");

    public final NumberPath<Integer> taste = createNumber("taste", Integer.class);

    public final StringPath title = createString("title");

    public final NumberPath<Integer> total = createNumber("total", Integer.class);

    public final QUser user;

    public final NumberPath<Integer> views = createNumber("views", Integer.class);

    public final StringPath wdate = createString("wdate");

    public final StringPath weak = createString("weak");

    public QReview(String variable) {
        this(Review.class, forVariable(variable), INITS);
    }

    public QReview(Path<? extends Review> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReview(PathMetadata metadata, PathInits inits) {
        this(Review.class, metadata, inits);
    }

    public QReview(Class<? extends Review> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.store = inits.isInitialized("store") ? new QStore(forProperty("store")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

