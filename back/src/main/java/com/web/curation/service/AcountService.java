package com.web.curation.service;

import java.util.List;

import com.web.curation.model.DTO.Storelike;
import com.web.curation.model.DTO.User;

public interface AcountService {
	public User login(String email,String password);
	public User signup(User request); 
	public boolean delete(long id);
	public boolean update(User request);
	public boolean changePW(long id,String password);
	public List<User> selectAll();
	public User selectEmail(String email);
	public User selectName(String nickname);
	public User selectId(long id);
	public boolean uploadProfile(long id,String profile);
	public User getProfile(long id);
	public User NaverLogin(String code, String state);
	public List<Storelike> bookmarks(long id);
	public List<User> userTop();
}
