package com.web.curation.model.querydsl;

import java.util.List;

import com.web.curation.model.user.Review;
import com.web.curation.model.user.Storetags;
import com.web.curation.model.user.User;

public interface CustomRepository {
	List<Storetags> storeTags(long num);
	List<Review> feedList(User email);
	
}
