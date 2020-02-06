package com.web.curation.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dao.user.StoreDao;
import com.web.curation.dao.user.StoreTagsDao;
import com.web.curation.model.user.Hashtag;
import com.web.curation.model.user.Store;
import com.web.curation.model.user.Storetags;

@Service
public class StoreServiceImpl implements StoreService {

	@Autowired
	StoreDao storeDao;
	
	@Autowired
	StoreTagsDao storeTagsDao;
	
	public boolean create(Store request) {
		if (storeDao.findByNum(request.getNum()) == null) {
			System.out.println(request.toString());
			storeDao.save(request);
			return true;
		}else {
			return false;
		}
	}

	public boolean delete(long num) {
		Store store = storeDao.findByNum(num);
		if(store != null) {
			storeDao.delete(store);
			return true;
		}
		return false;
	}


	public List<Store> selectAll() {
		return storeDao.findAll();
	}

	public Store selectOne(long num) {
		return storeDao.findByNum(num);
	}
	

	//카테고리 : food, location, with, mood, facilities
	public List<Hashtag> storeTags(long num) {
		
		List<Storetags> slist = storeTagsDao.storeTags(num);
		List<Hashtag> hlist = new ArrayList<>();
		HashMap<String,Integer> foodHash = new HashMap<>();
		HashMap<String,Integer> locHash = new HashMap<>();
		HashMap<String,Integer> withHash = new HashMap<>();
		HashMap<String,Integer> moodHash = new HashMap<>();
		HashMap<String,Integer> facHash = new HashMap<>();
		
		for(Storetags s : slist) {
			String key = s.getHashtag().getKeyword();
			String category = s.getHashtag().getCategory();
			
			if(category.equals("food")) {
				if(foodHash.containsKey(key)){
					foodHash.put(key, foodHash.get(key)+1);
				}else {
					foodHash.put(key, 1);
				}
			}else if(category.equals("location")) {
				if(locHash.containsKey(key)){
					locHash.put(key, locHash.get(key)+1);
				}else {
					locHash.put(key, 1);
				}
			}else if(category.equals("with")) {
				if(withHash.containsKey(key)){
					withHash.put(key, withHash.get(key)+1);
				}else {
					withHash.put(key, 1);
				}
			}else if(category.equals("mood")) {
				if(moodHash.containsKey(key)){
					moodHash.put(key, moodHash.get(key)+1);
				}else {
					moodHash.put(key, 1);
				}
			}else if(category.equals("facilites")){
				if(facHash.containsKey(key)){
					facHash.put(key, facHash.get(key)+1);
				}else {
					facHash.put(key, 1);
				}
			}
		}
		return hlist;
	}

}
