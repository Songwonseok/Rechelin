package com.web.curation.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

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
		list.addAll(topThree(storeTagsDao.foodtags(num)));
		list.addAll(topThree(storeTagsDao.loctags(num)));
		list.addAll(topThree(storeTagsDao.withtags(num)));
		list.addAll(topThree(storeTagsDao.moodtags(num)));
		list.addAll(topThree(storeTagsDao.factags(num)));
		
		return list;
	}
	
	public List<Hashtag> topThree(List<Hashtag> list){
		List<Hashtag> hlist = new ArrayList<>();
		HashMap<Long,Integer> map = new HashMap<>();
		int[][] arr;
		int index = 0;
		for(Hashtag h : list) {
			if(!map.containsKey(h.getNum()))
				map.put(h.getNum(), index++);
		}
		
		arr = new int[map.size()][2];
		
		for(int i=0;i<arr.length;i++) {
			arr[i][0] = i;
		}
		
		for(Hashtag h : list) {
			arr[map.get(h.getNum())][0] = (int)h.getNum(); 
			arr[map.get(h.getNum())][1]++; 
		}
		
		//정렬
		Arrays.sort(arr, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o2[1], o1[1]);
			}
		});
		
		//태그가 3개가 안되는지 체크
		int n =3;
		if(map.size() <3) {
			n = map.size();
		}
	
		for(int i=0;i<n;i++) {
			hlist.add(hashTagDao.findByNum(arr[i][0]));
		}
		return hlist;
	}
}
