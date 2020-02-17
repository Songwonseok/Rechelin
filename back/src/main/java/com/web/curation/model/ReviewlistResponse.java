package com.web.curation.model;

import com.web.curation.model.DTO.Review;

public class ReviewlistResponse {
	Review review;
	int like;
	int dislike;
	int comments;
	
	public ReviewlistResponse() {}
	public ReviewlistResponse(Review review, int like, int dislike, int comments) {
		this.review = review;
		this.like = like;
		this.dislike = dislike;
		this.comments = comments;
		
	}
	

}
