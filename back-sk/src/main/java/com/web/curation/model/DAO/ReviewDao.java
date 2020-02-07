package com.web.curation.model.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.model.DTO.Review;
import com.web.curation.model.DTO.Store;
import com.web.curation.model.DTO.User;
import com.web.curation.model.querydsl.CustomRepository;

public interface ReviewDao extends JpaRepository<Review, Long>, CustomRepository {
	Review findByRnum(long rnum);
	Review findAllByStoreAndUser(Store store, User user);
	List<Review> findAllByStore(Store store);
	List<Review> findAllByUser(User user);
	List<Review> findTop30ByOrderByRnumDesc();
}
