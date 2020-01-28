package com.web.curation.service;

import com.web.curation.model.user.User;

public interface AcountService {
	 public User login(String email,String password);
	 public User signup(User request); 
	 public Object delete(String email);
	 public Object update(User request);
	 public Object selectAll();
	 
	 
}
