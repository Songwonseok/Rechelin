package com.web.curation.model.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.model.DTO.Follow;
import com.web.curation.model.DTO.User;

public interface FollowDao extends JpaRepository<Follow, Long> {
	List<Follow> findAllByFan(User fan);
	List<Follow> findAllByStar(User star);
	Follow findByFanAndStar(User fan, User star);
	
}
