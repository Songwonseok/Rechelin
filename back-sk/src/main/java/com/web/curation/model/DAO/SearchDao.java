package com.web.curation.model.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.model.DTO.Search;
public interface SearchDao extends JpaRepository<Search, Long> {
	List<Search> findAllByEmailAndCategoryOrderBySdateDesc(String email, String category);
	Search findByEmailAndSearchname(String email, String searchname);
}
