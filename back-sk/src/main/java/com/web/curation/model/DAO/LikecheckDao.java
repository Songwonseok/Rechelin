package com.web.curation.model.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.model.DTO.Likecheck;
import com.web.curation.model.DTO.Review;
import com.web.curation.model.DTO.User;

public interface LikecheckDao extends JpaRepository<Likecheck, Long> {
	Likecheck findByReviewAndUser(Review review, User user);
	int countByReviewAndStatus(Review review,int status);
	List<Likecheck> findByReview(Review review);
	List<Likecheck> findByUser(User user);
	
}
