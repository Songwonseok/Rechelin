package com.web.curation.dao.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.model.querydsl.CustomRepository;
import com.web.curation.model.user.Review;
import com.web.curation.model.user.Store;
import com.web.curation.model.user.User;

public interface ReviewDao extends JpaRepository<Review, Long>, CustomRepository {
	Review findByRnum(long rnum);
	Review findAllByStoreAndUser(Store store, User user);
//	List<Review> findAllByUserOrderByNumDesc(User user);
}
