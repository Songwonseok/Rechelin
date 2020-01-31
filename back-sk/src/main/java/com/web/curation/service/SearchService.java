package com.web.curation.service;

import java.util.List;

import com.web.curation.model.user.Search;

public interface SearchService {
	public boolean addUser(String email, String name); 
	public List<Search> userList(String email);
}
