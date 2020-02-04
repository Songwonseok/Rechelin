package com.web.curation.model.generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.StringPath;
import com.web.curation.model.user.Review;


/**
 * QReview is a Querydsl query type for Review
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QReview extends EntityPathBase<Review> {

    private static final long serialVersionUID = -1108264028L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReview review = new QReview("review");

    public final StringPath hashtag = createString("hashtag");

    public final StringPath picture = createString("picture");

    public final NumberPath<Long> review_num = createNumber("review_num", Long.class);

    public final NumberPath<Integer> scorekindness = createNumber("scorekindness", Integer.class);

    public final NumberPath<Integer> scoreprice = createNumber("scoreprice", Integer.class);

    public final NumberPath<Integer> scoretaste = createNumber("scoretaste", Integer.class);

    public final NumberPath<Integer> scoretotal = createNumber("scoretotal", Integer.class);

    public final QStore store;

    public final StringPath str = createString("str");

    public final StringPath title = createString("title");

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

