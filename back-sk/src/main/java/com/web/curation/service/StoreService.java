package com.web.curation.service;

import java.util.List;

import com.web.curation.model.user.Hashtag;
import com.web.curation.model.user.Store;

public interface StoreService {
	public boolean create(Store request); 
	public boolean delete(long num);
	public List<Store> selectAll();
	public Store selectOne(long num);
	public List<Hashtag> storeTags(long num);
	 
	 
}
