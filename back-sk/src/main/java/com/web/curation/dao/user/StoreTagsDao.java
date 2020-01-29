package com.web.curation.dao.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.model.user.Storetags;

public interface StoreTagsDao extends JpaRepository<Storetags, Long> {
}
