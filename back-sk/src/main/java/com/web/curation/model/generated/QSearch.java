package com.web.curation.model.generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import com.web.curation.model.DTO.Search;


/**
 * QSearch is a Querydsl query type for Search
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSearch extends EntityPathBase<Search> {

    private static final long serialVersionUID = -1080251916L;

    public static final QSearch search = new QSearch("search");

    public final StringPath category = createString("category");

    public final StringPath email = createString("email");

    public final NumberPath<Long> num = createNumber("num", Long.class);

    public final StringPath sdate = createString("sdate");

    public final StringPath searchname = createString("searchname");

    public QSearch(String variable) {
        super(Search.class, forVariable(variable));
    }

    public QSearch(Path<? extends Search> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSearch(PathMetadata metadata) {
        super(Search.class, metadata);
    }

}

