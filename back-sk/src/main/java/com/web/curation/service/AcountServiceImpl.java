package com.web.curation.service;

import java.io.InputStream;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.web.curation.dao.user.UserDao;
import com.web.curation.model.BasicResponse;
import com.web.curation.model.FileInfo;
import com.web.curation.model.user.User;

@Service
public class AcountServiceImpl implements AcountService {

	@Autowired
	UserDao userDao;

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
		userDao.save(request);

		return request;
	}

	public boolean delete(String email) {
		User user = userDao.findByEmail(email);
		if (user != null) {
			userDao.delete(user);
			return true;
		} else
			return false;
	}

	public boolean update(User request) {
		User user = userDao.findByEmail(request.getEmail());
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

	public Object uploadImage(FileInfo fileInfo, boolean check) {
		final BasicResponse result = new BasicResponse();
		if (check) {
			result.status = true;
			result.data = "업로드 성공";
			result.object = new JSONObject(fileInfo).toMap();
		} else {
			result.status = false;
			result.data = "업로드 실패";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@Override
	public InputStream getProfile(String email) {
		User user = userDao.findByEmail(email);
		InputStream in = getClass().getResourceAsStream(user.getProfile());
		// 사진이 없으면 defalut 이미지 경로 넣음
		// if(....)
		return in;
	}

	@Override
	public String EncodePW(String Pw) {
		return passwordEncoder.encode(Pw);
	}

}
