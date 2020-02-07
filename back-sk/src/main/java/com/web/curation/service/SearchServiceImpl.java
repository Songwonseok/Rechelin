package com.web.curation.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.model.DAO.SearchDao;
import com.web.curation.model.DAO.UserDao;
import com.web.curation.model.DTO.Search;
import com.web.curation.model.DTO.User;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	SearchDao searchDao;

	@Autowired
	UserDao userDao;
	public boolean addUser(String email, String searchname) {
		
		User user = userDao.findByEmail(email);
		if(user != null) {
			Search search = new Search(email, searchname, "user");
			searchDao.save(search);
			return true;
		}else
			return false;
	}

	public List<Search> userList(String email) {
		
		List<Search> allList = searchDao.findAllByEmailAndCategoryOrderBySdateDesc(email,"user");
		List<Search> list = new ArrayList<>();
		HashSet<String> set = new HashSet<>();
		for(Search s : allList) {
			if(set.contains(s.getSearchname()))
					continue;
			else {
				set.add(s.getSearchname());
				list.add(s);
			}
		}
		
		return list;
	}

}
