package com.web.curation.model.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.model.DTO.Comments;
import com.web.curation.model.DTO.Review;
import com.web.curation.model.DTO.User;

public interface CommentDao extends JpaRepository<Comments, Long> {
	Comments findByNum(long num);
	List<Comments> findAllByReview (Review review);
	int countByReview(Review review);
	Comments findTopByUserOrderByWdateDesc(User user);
}
