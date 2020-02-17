package com.web.curation.service;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.web.curation.model.NaverLogin;
import com.web.curation.model.DAO.RoleDao;
import com.web.curation.model.DAO.UserDao;
import com.web.curation.model.DTO.Role;
import com.web.curation.model.DTO.RoleName;
import com.web.curation.model.DTO.User;

@Service
public class AcountServiceImpl implements AcountService {

	@Autowired
	UserDao userDao;
	@Autowired
	RoleDao roleDao;
	
	@Autowired
	PasswordEncoder passwordEncoder; // 비밀번호 암호화

	
	public User login(String email, String password) {
		
		User tmp = userDao.findByEmail(email);
		if (tmp != null) {
			// 비밀번호 일치 X

			System.out.println(password + " " + tmp.getPw());
			
			if (!passwordEncoder.matches(password, tmp.getPw())) {
				System.out.println("비밀번호 틀림");
				tmp.setPw("");
			}
			return tmp;
		}
		System.out.println("유저 존재X");
		// DB에 존재 X
		return null;

	}

	public User signup(User request) {
		if (userDao.findByEmail(request.getEmail()) != null) {
			// 이메일 중복
			request.setEmail("");
		} else if (userDao.findByNickname(request.getNickname()) != null) {
			// 닉네임 중복
			request.setNickname("");
		}
		request.setPw(EncodePW(request.getPw())); // 암호화
		request.setProfile(null); // 처음 프로필을 만들어주지 않는다
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
		User user = userDao.findByEmail(request.getEmail());
		if(user!=null) {
			user.updateUser(request);
			// profile과 비밀번호 제외하고 업데이트
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
		// 1) email 로  유저 찾기
		User user = userDao.findById(id);
		
		if(user!=null) {
			// 2) User정보 업데이트
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

	@Override
	public List<User> userTop() {
		List<User> list = userDao.topUser();
		return list;
	}


}
