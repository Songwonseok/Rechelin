package com.web.curation.service;

import java.io.InputStream;
import java.util.List;

import com.web.curation.model.FileInfo;
import com.web.curation.model.user.User;

public interface AcountService {
	public User login(String email,String password);
	public User signup(User request); 
	public boolean delete(String email);
	public boolean update(User request);
	public List<User> selectAll();
	public User selectEmail(String email);
	public User selectName(String nickname);
	public Object uploadImage(FileInfo fileInfo,boolean check);
	public InputStream getProfile(String email);
	public String EncodePW(String Pw) ;
	public User NaverLogin(String code, String state);
}
