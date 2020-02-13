//디비 셋팅 주석 
package com.web.curation.model.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.model.DTO.Store;
import com.web.curation.model.QueryDSL.CustomRepository;


public interface StoreDao extends JpaRepository<Store, Long>,CustomRepository {
	Store findByNum(long num);
	Store findBySname(String sname);
	Store findBySnameAndAddress(String sname, String address);
	List<Store> findAllBySnameLike(String sname);
	
}
