package com.web.curation.model.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.model.DTO.Hashtag;
import com.web.curation.model.DTO.Storetags;
import com.web.curation.model.querydsl.CustomRepository;

public interface StoreTagsDao extends JpaRepository<Storetags, Long>,CustomRepository{
	List<Storetags> findAllByHashtag(Hashtag hashtag);
}
