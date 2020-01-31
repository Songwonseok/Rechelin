//디비 셋팅 주석 
package com.web.curation.dao.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.model.user.Store;


public interface StoreDao extends JpaRepository<Store, Long> {
	Store findByNum(long num);
	Store findBySname(String sname);
	List<Store> findAllBySnameLike(String sname);
	
}
