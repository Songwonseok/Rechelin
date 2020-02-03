package com.web.curation.dao.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.web.curation.model.user.Search;
public interface SearchDao extends JpaRepository<Search, Long> {
	List<Search> findAllByEmailOrderBySdateDesc(String email);
	Search findByEmailAndSearchname(String email, String searchname);
//	@Modifying
//	@Transactional
//	@Query("select s.searchname from Search s where s.email = ?1")
//	List<String> searchList(String email);
}
