package com.web.curation.service;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.web.curation.model.NaverLogin;
import com.web.curation.model.DAO.RoleDao;
import com.web.curation.model.DAO.StoreLikeDao;
import com.web.curation.model.DAO.UserDao;
import com.web.curation.model.DTO.Role;
import com.web.curation.model.DTO.RoleName;
import com.web.curation.model.DTO.Storelike;
import com.web.curation.model.DTO.User;

@Service
public class AcountServiceImpl implements AcountService {

	@Autowired
	UserDao userDao;
	@Autowired
	RoleDao roleDao;
	
	@Autowired
	StoreLikeDao storeLikeDao;
	
	@Autowired
	PasswordEncoder passwordEncoder; // 비밀번호 암호화


	
	public User login(String email, String password) {
		
		User tmp = userDao.findByEmail(email);
		if (tmp != null) {
			// 鍮꾨�踰덊샇 �씪移� X

			System.out.println(password + " " + tmp.getPw());
			
			if (!passwordEncoder.matches(password, tmp.getPw())) {
				System.out.println("鍮꾨�踰덊샇 ��由�");
				tmp.setPw("");
			}
			return tmp;
		}
		System.out.println("�쑀�� 議댁옱X");
		// DB�뿉 議댁옱 X
		return null;

	}

	public User signup(User request) {
		if (userDao.findByEmail(request.getEmail()) != null) {
			// �씠硫붿씪 以묐났
			request.setEmail("");
		} else if (userDao.findByNickname(request.getNickname()) != null) {
			// �땳�꽕�엫 以묐났
			request.setNickname("");
		}
		request.setPw(EncodePW(request.getPw())); // �븫�샇�솕
		request.setProfile(null); // 泥섏쓬 �봽濡쒗븘�쓣 留뚮뱾�뼱二쇱� �븡�뒗�떎
		Role userRole = roleDao.findByName(RoleName.ROLE_USER);
		request.setRole(Collections.singleton(userRole));
		userDao.save(request);
		return request;
	}

	public boolean delete(long id) {
		User user = userDao.findById(id);
		if (user != null) {
			userDao.deleteById(id);;
			return true;
		} else
			return false;
	}

	public boolean update(User request) {
		User user = userDao.findById(request.getId());
		if(user!=null) {			
			user.updateUser(request);
			userDao.save(user);
			return true;
		}
		return false;
	}

	public List<User> selectAll() {
		return userDao.findAll();
	}

	public User selectEmail(String email) {
		User user = userDao.findByEmail(email);
		return user;
	}

	public User selectName(String nickname) {
		return userDao.findByNickname(nickname);
	}

	public boolean uploadProfile(long id,String profile) {
		// 1) email 濡�  �쑀�� 李얘린
		User user = userDao.findById(id);
		
		if(user!=null) {
			// 2) User�젙蹂� �뾽�뜲�씠�듃
			user.setProfile(profile);
			// 3) userDao.save()
			userDao.save(user);
			System.out.println(user.toString());
			return true;
		}
		return false;
	}

	@Override
	public User getProfile(long id) {
		User user = userDao.findById(id);
		return user;
	}

	public String EncodePW(String Pw) {
		return passwordEncoder.encode(Pw);
	}

	

	@Override
	public boolean changePW(long id, String password) {
		User user = userDao.findById(id);
		if(user!=null) {
			user.setPw(EncodePW(password));
			userDao.save(user);
			return true;
		}else return false;
	}

	@Override
	public User selectId(long id) {
		User user = userDao.findById(id);
		return user;
	}
	
	public List<Storelike> bookmarks(long id){
		User user = userDao.findById(id);
		List<Storelike> list = storeLikeDao.findAllByUser(user);
		return list;
	}

	@Override
	public List<User> userTop() {
		List<User> list = userDao.topUser();
		return list;
	}

}
