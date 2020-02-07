package com.web.curation.model.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.model.DTO.Likecheck;
import com.web.curation.model.DTO.Storelike;

public interface StoreLikeDao extends JpaRepository<Storelike, Long> {
}
