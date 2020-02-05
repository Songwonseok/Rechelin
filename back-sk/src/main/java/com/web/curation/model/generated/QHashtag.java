package com.web.curation.model.generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import com.web.curation.model.user.Hashtag;


/**
 * QHashtag is a Querydsl query type for Hashtag
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QHashtag extends EntityPathBase<Hashtag> {

    private static final long serialVersionUID = -398851840L;

    public static final QHashtag hashtag = new QHashtag("hashtag");

    public final StringPath category = createString("category");

    public final StringPath keyword = createString("keyword");

    public final NumberPath<Long> num = createNumber("num", Long.class);

    public QHashtag(String variable) {
        super(Hashtag.class, forVariable(variable));
    }

    public QHashtag(Path<? extends Hashtag> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHashtag(PathMetadata metadata) {
        super(Hashtag.class, metadata);
    }

}

