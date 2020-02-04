package com.web.curation.model.querydsl;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.web.curation.model.generated.QStore;
import com.web.curation.model.generated.QStoretags;
import com.web.curation.model.user.Storetags;

@Transactional(readOnly = true)
public class CustomRepositoryImpl extends QuerydslRepositorySupport 
implements CustomRepository{

	private final JPAQueryFactory queryFactory;
	private final QStoretags storetags = QStoretags.storetags;
	private final QStore store = QStore.store;
	
	
	public CustomRepositoryImpl(JPAQueryFactory queryFactory) {
		super(Storetags.class);
		this.queryFactory = queryFactory;
	}

	public List<Storetags> storeTags(long num) {
		
		return queryFactory.selectFrom(storetags)
				.where(storetags.review.store.num.in(
						JPAExpressions.select(store.num)
						.from(store).where(store.num.eq(num)))).fetch();
	}
	

}
