package com.web.curation.model.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.model.DTO.Bookmark;
import com.web.curation.model.DTO.Review;
import com.web.curation.model.DTO.User;

public interface BookmarkDao extends JpaRepository<Bookmark, Long> {
	List<Bookmark> findAllByUser(User user);
	Bookmark findByUserAndReview(User user, Review review);
}
