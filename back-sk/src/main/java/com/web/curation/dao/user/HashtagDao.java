package com.web.curation.dao.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.model.user.Hashtag;

public interface HashtagDao extends JpaRepository<Hashtag, Long> {
	
}
