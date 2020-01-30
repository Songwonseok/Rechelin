package com.web.curation.dao.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.model.user.Follow;

public interface FollowDao extends JpaRepository<Follow, Long> {
	List<Follow> findAllByFan(String fan);
	List<Follow> findAllByStar(String star);
	Follow findByFanAndStar(String fan, String star);
}
