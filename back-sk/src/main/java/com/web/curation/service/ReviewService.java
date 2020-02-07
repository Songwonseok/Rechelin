package com.web.curation.service;

import java.util.List;

import com.web.curation.model.DTO.Bookmark;
import com.web.curation.model.DTO.Comments;
import com.web.curation.model.DTO.Likecheck;
import com.web.curation.model.DTO.Review;

public interface ReviewService {
	public void register(Review review);
	public boolean update(Review review);
	public boolean delete(long rnum);
	public Review detail(long rnum);
	public int useful(Likecheck like);
	public void addComment(Comments com);
	public void deleteComment(long num);
	public List<Comments> getComment(long rnum);
	public boolean clickBookmark(Bookmark book);
	public List<Bookmark> getBookmark(String email);
	public List<Review> getcurrentFeed(String email);
}
