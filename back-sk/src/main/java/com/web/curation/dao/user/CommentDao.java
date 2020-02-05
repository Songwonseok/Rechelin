package com.web.curation.dao.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.model.user.Comments;
import com.web.curation.model.user.Review;

public interface CommentDao extends JpaRepository<Comments, Long> {
	Comments findByNum(long num);
	List<Comments> findAllByReview (Review review);
}
