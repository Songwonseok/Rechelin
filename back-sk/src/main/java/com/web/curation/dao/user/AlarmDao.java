package com.web.curation.dao.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.model.user.Alarm;
import com.web.curation.model.user.User;

public interface AlarmDao extends JpaRepository<Alarm, Long> {
	List<Alarm> findAllByStar(User star);
	Alarm findByFanAndStar(User fan, User star);
}
