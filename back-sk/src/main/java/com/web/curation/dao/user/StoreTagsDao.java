package com.web.curation.dao.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.web.curation.model.user.Storetags;

public interface StoreTagsDao extends JpaRepository<Storetags, Long> {
	
//	@Modifying
//	@Transactional
//	@Query("select s "
//			+ "from storetags s "
//			+ "where s.reviewnum in "
//			+ "(select r.reviewnum "
//			+ "from review r "
//			+ "where r.storenum = ?1)")
//	List<Storetags> storeTags(String snum);
}
