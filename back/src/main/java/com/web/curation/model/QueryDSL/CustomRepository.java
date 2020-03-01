package com.web.curation.model.QueryDSL;

import java.util.List;

import com.web.curation.model.DTO.Hashtag;
import com.web.curation.model.DTO.Review;
import com.web.curation.model.DTO.Store;
import com.web.curation.model.DTO.Storetags;
import com.web.curation.model.DTO.User;

public interface CustomRepository {
	List<Storetags> storeTags(long num);
	List<Review> feedList(User email);
	List<Review> likeBest(); 
	
	List<Hashtag> foodtags(long num);
	List<Hashtag> loctags(long num);
	List<Hashtag> withtags(long num);
	List<Hashtag> moodtags(long num);
	List<Hashtag> factags(long num);
	List<Hashtag> agetags(long num);
	
	List<Store> random(long num);
	
	List<User> topUser();
}
