package com.web.curation.service;

import java.io.InputStream;

import com.web.curation.model.FileInfo;
import com.web.curation.model.user.User;

public interface AcountService {
	public User login(String email,String password);
	public User signup(User request); 
	public Object delete(String email);
	public void update(User request);
	public Object selectAll();
	public User selectEmail(String email);
	public Object selectName(String nickname);
	public Object uploadImage(FileInfo fileInfo,boolean check);
	public InputStream getProfile(String email);
	public String EncodePW(String Pw) ;
	 
}
