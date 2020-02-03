package com.web.curation.dao.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.model.user.Review;

public interface ReviewDao extends JpaRepository<Review, Long> {
	Review findByRnum(long rnum);
	List<Review> findAllBySnumAndEmail(long snum, String email);
}
