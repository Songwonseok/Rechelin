package com.web.curation.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.model.TagsResponse;
import com.web.curation.model.DAO.SearchDao;
import com.web.curation.model.DAO.StoreDao;
import com.web.curation.model.DAO.UserDao;
import com.web.curation.model.DTO.Hashtag;
import com.web.curation.model.DTO.Search;
import com.web.curation.model.DTO.Store;
import com.web.curation.model.DTO.User;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	SearchDao searchDao;
	
	@Autowired
	StoreDao storeDao;
	
	@Autowired
	StoreService service;
	
	@Autowired
	UserDao userDao;
	public boolean addUser(String email, String searchname) {
		
		User user = userDao.findByEmail(email);
		if(user != null) {
			Search search = new Search(user, searchname, "user");
			searchDao.save(search);
			return true;
		}else
			return false;
	}

	public List<Search> userList(String email) {
		User user = userDao.findByEmail(email);
		List<Search> allList = searchDao.findAllByUserAndCategoryOrderBySdateDesc(user,"user");
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

	public boolean addStore(String email, String searchname) {
		User user = userDao.findByEmail(email);
		if(user != null) {
			Search search = new Search(user, searchname, "store");
			searchDao.save(search);
			return true;
		}else
			return false;
	}

	public List<Search> storeList(String email) {
		User user = userDao.findByEmail(email);
		List<Search> allList = searchDao.findAllByUserAndCategoryOrderBySdateDesc(user,"store");
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

	public List<TagsResponse> tagSearch(){

		List<Store> slist = storeDao.findAll();
		List<TagsResponse> list = new ArrayList<>();
		
		for(Store s : slist) {
			List<Hashtag> hlist = service.storeTags(s.getNum());
			if(hlist.size() != 0) {
				list.add(new TagsResponse(s,hlist));
			}
		}
		
		return list;
	}


}
