package com.web.curation.service;

import com.web.curation.model.user.Likecheck;
import com.web.curation.model.user.Review;

public interface ReviewService {
	public boolean register(Review review);
	public boolean update(Review review);
	public boolean delete(long rnum);
	public Review detail(long rnum);
	public int useful(Likecheck like);
}
