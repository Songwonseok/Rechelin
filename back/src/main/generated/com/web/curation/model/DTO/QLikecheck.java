package com.web.curation.model.DTO;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLikecheck is a Querydsl query type for Likecheck
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLikecheck extends EntityPathBase<Likecheck> {

    private static final long serialVersionUID = -1759520597L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLikecheck likecheck = new QLikecheck("likecheck");

    public final NumberPath<Long> num = createNumber("num", Long.class);

    public final QReview review;

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public final QUser user;

    public QLikecheck(String variable) {
        this(Likecheck.class, forVariable(variable), INITS);
    }

    public QLikecheck(Path<? extends Likecheck> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLikecheck(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLikecheck(PathMetadata metadata, PathInits inits) {
        this(Likecheck.class, metadata, inits);
    }

    public QLikecheck(Class<? extends Likecheck> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.review = inits.isInitialized("review") ? new QReview(forProperty("review"), inits.get("review")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

