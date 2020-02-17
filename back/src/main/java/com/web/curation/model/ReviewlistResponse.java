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
	public Review getReview() {
		return review;
	}
	public void setReview(Review review) {
		this.review = review;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	public int getDislike() {
		return dislike;
	}
	public void setDislike(int dislike) {
		this.dislike = dislike;
	}
	public int getComments() {
		return comments;
	}
	public void setComments(int comments) {
		this.comments = comments;
	}
	
	
	

}
