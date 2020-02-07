package com.web.curation.model.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.model.DTO.Hashtag;

public interface HashtagDao extends JpaRepository<Hashtag, Long> {
	Hashtag findByNum(long num);
	
}
