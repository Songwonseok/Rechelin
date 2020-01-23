package com.web.curation.service;

import com.web.curation.model.user.User;

public interface AcountServiceImpl {
	 public Object login(String email,String password);
	 public Object signup(User request); 
	 public Object delete(String email);
	 public Object update(User request);
	 public Object selectAll();
	 
	 
}
