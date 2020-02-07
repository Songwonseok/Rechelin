package com.web.curation.model.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.model.DTO.Alarm;
import com.web.curation.model.DTO.User;

public interface AlarmDao extends JpaRepository<Alarm, Long> {
	List<Alarm> findAllByStar(User star);
	Alarm findByFanAndStar(User fan, User star);
}
