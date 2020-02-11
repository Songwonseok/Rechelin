package com.web.curation.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.model.DAO.HashtagDao;
import com.web.curation.model.DAO.StoreDao;
import com.web.curation.model.DAO.StoreTagsDao;
import com.web.curation.model.DTO.Hashtag;
import com.web.curation.model.DTO.Store;

@Service
public class StoreServiceImpl implements StoreService {

	@Autowired
	StoreDao storeDao;
	
	@Autowired
	StoreTagsDao storeTagsDao;
	
	@Autowired
	HashtagDao hashTagDao;
	
	public Store register(Store request) {
		System.out.println(request.getSname());
		Store store = storeDao.findBySnameAndAddress(request.getSname(), request.getAddress());
		if(store == null) {
			storeDao.save(request);
			return storeDao.findBySnameAndAddress(request.getSname(), request.getAddress());
		}else {
			return store; 
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
	

	public List<Hashtag> storeTags(long num) {
		
		List<Hashtag> list = new ArrayList<>();
		list.addAll(storeTagsDao.foodtags(num));
		list.addAll(storeTagsDao.loctags(num));
		list.addAll(storeTagsDao.withtags(num));
		list.addAll(storeTagsDao.moodtags(num));
		list.addAll(storeTagsDao.factags(num));
		
		return list;
	}

	@Override
	public List<Store> random(String keyword) {
		// 1) 키워드 찾고
		Hashtag tag = hashTagDao.findByKeyword(keyword);
		System.out.println(tag.toString());
		// 2) 리스트 뽑아오기
		List<Store> Alllist = storeDao.random(tag.getNum());
		System.out.println("________________");
		for (Store store : Alllist) {
			System.out.println(store);
		}
		// 3) 랜덤으로 10개만 가져오기
		int r, size = Alllist.size(), limit = 10;
		List<Store> list = new ArrayList<Store>();
		boolean[] dul = new boolean[size];
		if(size < limit) limit = size;
		
		
		while(list.size() < limit) {
			r = (int) (Math.random()*size);
//			System.out.print(r+" ");
			if(dul[r]) continue;
			
			list.add(Alllist.get(r));
			dul[r] = true;
		}
		
		
		return list;
	}
	
	
	
	
	
	
	
	
}
