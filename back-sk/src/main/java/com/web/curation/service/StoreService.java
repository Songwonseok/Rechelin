package com.web.curation.service;

import java.util.List;

import com.web.curation.model.DTO.Hashtag;
import com.web.curation.model.DTO.Store;

public interface StoreService {
	public Store register(Store request);
	public boolean delete(long num);
	public List<Store> selectAll();
	public Store selectOne(long num);
	public List<Hashtag> storeTags(long num);
	 
	 
}
