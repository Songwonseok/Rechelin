package com.web.curation.service;

import java.util.List;

import org.json.JSONObject;

import com.web.curation.model.ReviewlistResponse;
import com.web.curation.model.DTO.Bookmark;
import com.web.curation.model.DTO.Comments;
import com.web.curation.model.DTO.Likecheck;
import com.web.curation.model.DTO.Review;

public interface ReviewService {
	public void register(Review review);
	public boolean update(Review review);
	public boolean delete(long rnum);
	public Review detail(long rnum);
	public List<ReviewlistResponse> getReview(long snum);
	public int useful(Likecheck like);
	public void addComment(Comments com);
	public void deleteComment(long num);
	public List<Comments> getComment(long rnum);
	public boolean clickBookmark(Bookmark book);
	public List<Bookmark> getBookmark(long num);
	public List<Review> getcurrentFeed(long num);
	public List<Review> getMyReview(long num);
	public List<Review> getCurReview();
	public List<Review> getviewsTop();
	public List<Review> getlikeTop();
	public List<Review> getscoreTop();
	public JSONObject countLike(long rnum);
	
}
