package com.web.curation.service;

import java.util.List;

import com.web.curation.model.user.Comments;
import com.web.curation.model.user.Likecheck;
import com.web.curation.model.user.Review;

public interface ReviewService {
	public void register(Review review);
	public boolean update(Review review);
	public boolean delete(long rnum);
	public Review detail(long rnum);
	public int useful(Likecheck like);
	public void addComment(Comments com);
	public void deleteComment(long num);
	public List<Comments> getComment(long rnum);
}
