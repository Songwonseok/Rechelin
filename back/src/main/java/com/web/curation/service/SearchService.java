package com.web.curation.service;

import java.util.List;

import com.web.curation.model.TagsResponse;
import com.web.curation.model.DTO.Search;

public interface SearchService {
	public boolean addUser(String email, String name); 
	public boolean addStore(String email, String name); 
	public List<Search> userList(String email);
	public List<Search> storeList(String email);
	public List<TagsResponse> tagSearch();
}
