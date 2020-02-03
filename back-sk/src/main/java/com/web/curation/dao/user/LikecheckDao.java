package com.web.curation.dao.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.model.user.Likecheck;

public interface LikecheckDao extends JpaRepository<Likecheck, Long> {
	Likecheck findByRnumAndEmail(long rnum, String email);
}
