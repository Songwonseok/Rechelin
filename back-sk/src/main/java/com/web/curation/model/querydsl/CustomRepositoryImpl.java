package com.web.curation.model.querydsl;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.web.curation.model.DTO.Hashtag;
import com.web.curation.model.DTO.Review;
import com.web.curation.model.DTO.Storetags;
import com.web.curation.model.DTO.User;
import com.web.curation.model.generated.QFollow;
import com.web.curation.model.generated.QHashtag;
import com.web.curation.model.generated.QReview;
import com.web.curation.model.generated.QStore;
import com.web.curation.model.generated.QStoretags;

@Transactional(readOnly = true)
public class CustomRepositoryImpl extends QuerydslRepositorySupport 
implements CustomRepository{

	private final JPAQueryFactory queryFactory;
	private final QStoretags storetags = QStoretags.storetags;
	private final QStore store = QStore.store;
	private final QReview review = QReview.review;
	private final QFollow follow = QFollow.follow;
	private final QHashtag hashtag = QHashtag.hashtag;
	
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
	
	public List<Review> feedList(User email){
		return queryFactory.selectFrom(review)
				.where(review.user.in(
						JPAExpressions.select(follow.star)
						.from(follow).where(follow.fan.eq(email)))).fetch();
	}
	
	//식당의 카테고리별 태그 구하기
	public List<Hashtag> foodtags(long num){
		return queryFactory.select(storetags.hashtag)
				.from(storetags)
				.where(storetags.hashtag.category.eq("food"),
						storetags.review.store.num.in(
						JPAExpressions.select(store.num)
						.from(store).where(store.num.eq(num)))).fetch();
	}
	public List<Hashtag> loctags(long num){
		return queryFactory.select(storetags.hashtag)
				.from(storetags)
				.where(storetags.hashtag.category.eq("location"),
						storetags.review.store.num.in(
						JPAExpressions.select(store.num)
						.from(store).where(store.num.eq(num)))).fetch();
	}
	public List<Hashtag> withtags(long num){
		return queryFactory.select(storetags.hashtag)
				.from(storetags)
				.where(storetags.hashtag.category.eq("with"),
						storetags.review.store.num.in(
						JPAExpressions.select(store.num)
						.from(store).where(store.num.eq(num)))).fetch();
	}
	public List<Hashtag> moodtags(long num){
		return queryFactory.select(storetags.hashtag)
				.from(storetags)
				.where(storetags.hashtag.category.eq("mood"),
						storetags.review.store.num.in(
						JPAExpressions.select(store.num)
						.from(store).where(store.num.eq(num)))).fetch();
	}
	public List<Hashtag> factags(long num){
		return queryFactory.select(storetags.hashtag)
				.from(storetags)
				.where(storetags.hashtag.category.eq("facilities"),
						storetags.review.store.num.in(
						JPAExpressions.select(store.num)
						.from(store).where(store.num.eq(num)))).fetch();
	}
	
	
}
