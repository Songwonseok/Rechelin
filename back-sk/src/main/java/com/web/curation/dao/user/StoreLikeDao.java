package com.web.curation.dao.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.model.user.Likecheck;
import com.web.curation.model.user.Storelike;

public interface StoreLikeDao extends JpaRepository<Storelike, Long> {
}
