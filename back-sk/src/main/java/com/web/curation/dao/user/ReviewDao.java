package com.web.curation.dao.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.model.user.Review;

public interface ReviewDao extends JpaRepository<Review, Long> {
}