package com.web.curation.model.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.model.DTO.Store;
import com.web.curation.model.DTO.Storelike;
import com.web.curation.model.DTO.User;

public interface StoreLikeDao extends JpaRepository<Storelike, Long> {
	Storelike findByUserAndStore(User user, Store store);
	List<Storelike> findAllByUser(User user);
}
