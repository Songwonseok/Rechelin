package com.web.curation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dao.user.SearchDao;
import com.web.curation.model.user.Search;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	SearchDao searchDao;

	public boolean addUser(String email, String searchname) {
		Search search = new Search(email, searchname, "user");
		searchDao.save(search);
		return true;
	}

	public List<Search> userList(String email) {
		
		return searchDao.findAllByEmailOrderBySdateDesc(email);
	}

}
