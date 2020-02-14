package com.web.curation.service;

import java.util.List;

import com.web.curation.model.TagsResponse;
import com.web.curation.model.DTO.Search;

public interface SearchService {
	public boolean addUser(long id, String name); 
	public boolean addStore(long id, String name); 
	public List<Search> userList(long id);
	public List<Search> storeList(long id);
	public List<TagsResponse> tagSearch();
}
