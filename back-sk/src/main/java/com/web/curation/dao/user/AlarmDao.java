package com.web.curation.dao.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.model.user.Alarm;

public interface AlarmDao extends JpaRepository<Alarm, Long> {
	List<Alarm> findAllByStar(String star);
	Alarm findByFanAndStar(String fan, String star);
}
