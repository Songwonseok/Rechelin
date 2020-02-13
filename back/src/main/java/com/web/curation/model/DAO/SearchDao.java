package com.web.curation.model.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.model.DTO.Search;
import com.web.curation.model.DTO.User;
public interface SearchDao extends JpaRepository<Search, Long> {
	List<Search> findAllByUserAndCategoryOrderBySdateDesc(User user, String category);
	Search findByUserAndSearchname(User user, String searchname);
}
