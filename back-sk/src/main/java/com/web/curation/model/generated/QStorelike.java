package com.web.curation.model.generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathInits;
import com.web.curation.model.DTO.Storelike;


/**
 * QStorelike is a Querydsl query type for Storelike
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStorelike extends EntityPathBase<Storelike> {

    private static final long serialVersionUID = 93441234L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStorelike storelike = new QStorelike("storelike");

    public final NumberPath<Long> num = createNumber("num", Long.class);

    public final QStore store;

    public final QUser user;

    public QStorelike(String variable) {
        this(Storelike.class, forVariable(variable), INITS);
    }

    public QStorelike(Path<? extends Storelike> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStorelike(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStorelike(PathMetadata metadata, PathInits inits) {
        this(Storelike.class, metadata, inits);
    }

    public QStorelike(Class<? extends Storelike> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.store = inits.isInitialized("store") ? new QStore(forProperty("store")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

