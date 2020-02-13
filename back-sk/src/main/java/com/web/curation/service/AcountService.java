package com.web.curation.service;

import java.util.List;

import com.web.curation.model.DTO.User;

public interface AcountService {
	public User login(String email,String password);
	public User signup(User request); 
	public boolean delete(String email);
	public boolean update(User request);
	public boolean changePW(String email,String password);
	public List<User> selectAll();
	public User selectEmail(String email);
	public User selectName(String nickname);
	public User selectId(long id);
	public boolean uploadProfile(String email,String profile);
	public User getProfile(String email);
	public String EncodePW(String Pw) ;
	public User NaverLogin(String code, String state);
}
