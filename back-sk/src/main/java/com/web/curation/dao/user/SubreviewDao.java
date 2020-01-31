package com.web.curation.dao.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.model.user.Review;
import com.web.curation.model.user.Subreview;

public interface SubreviewDao extends JpaRepository<Subreview, Long> {
}
