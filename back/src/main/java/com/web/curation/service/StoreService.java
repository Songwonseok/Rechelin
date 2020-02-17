package com.web.curation.service;

import java.util.List;

import com.web.curation.model.DTO.Hashtag;
import com.web.curation.model.DTO.Store;
import com.web.curation.model.DTO.Storelike;

public interface StoreService {
	public Store register(Store request);
	public boolean delete(long num);
	public List<Store> selectAll();
	public Store selectOne(long num);
	public List<Hashtag> storeTags(long num);
	public List<Store> random(String keyword);
	public boolean addBook(long id, long snum);
	public boolean removeBook(long id, long snum);
	public Storelike selectBook(long id, long snum);
}
