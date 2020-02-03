package com.web.curation.dao.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.model.user.Follow;
import com.web.curation.model.user.User;

public interface FollowDao extends JpaRepository<Follow, Long> {
	List<Follow> findAllByFan(User star);
	List<Follow> findAllByStar(User star);
	Follow findByFanAndStar(User fan, User star);
	
}
