package com.web.curation.model.DTO;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QStore is a Querydsl query type for Store
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStore extends EntityPathBase<Store> {

    private static final long serialVersionUID = 1994191547L;

    public static final QStore store = new QStore("store");

    public final StringPath address = createString("address");

    public final StringPath img = createString("img");

    public final StringPath lat = createString("lat");

    public final StringPath lng = createString("lng");

    public final NumberPath<Long> num = createNumber("num", Long.class);

    public final StringPath sname = createString("sname");

    public QStore(String variable) {
        super(Store.class, forVariable(variable));
    }

    public QStore(Path<? extends Store> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStore(PathMetadata metadata) {
        super(Store.class, metadata);
    }

}

