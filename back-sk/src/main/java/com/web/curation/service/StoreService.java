package com.web.curation.service;

import com.web.curation.model.user.Store;

public interface StoreService {
	public Object create(Store request); 
	public Object delete(long num);
	public Object update(Store request);
	public Object selectAll();
	public Object selectOne(long num);
	public Object searchName(String sname);
	 
	 
}
