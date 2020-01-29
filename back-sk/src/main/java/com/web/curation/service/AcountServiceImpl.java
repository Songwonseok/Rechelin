package com.web.curation.service;

import java.io.InputStream;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.web.curation.dao.user.UserDao;
import com.web.curation.model.BasicResponse;
import com.web.curation.model.FileInfo;
import com.web.curation.model.user.User;

@Service
public class AcountServiceImpl implements AcountService {

	@Autowired
	UserDao userDao;

	public Object login(String email, String password) {
		JSONObject dummyUser = new JSONObject();
		final BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "success";
		result.object = dummyUser.toMap();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	public Object signup(User request) {
		final BasicResponse result = new BasicResponse();

		if (userDao.findByEmail(request.getEmail()) != null) {
			result.status = false;
			result.data = "생성 실패(이메일 중복)";
			return new ResponseEntity<>(result, HttpStatus.OK);

		} else if (userDao.findByNickname(request.getNickname()) != null) {
			result.status = false;
			result.data = "생성 실패(닉네임 중복)";
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
		userDao.save(request);
		result.status = true;
		result.data = "회원 가입 성공";
		result.object = new JSONObject(request).toMap();

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	public Object delete(String email) {
		User user = userDao.findByEmail(email);
		userDao.delete(user);
		final BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "삭제 성공";

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	public Object update(User request) {
		final BasicResponse result = new BasicResponse();
		User user = userDao.findByEmail(request.getEmail());
		user.updateUser(request);
		userDao.save(user);

		result.status = true;
		result.data = "업데이트 성공";
		result.object = new JSONObject(user).toMap();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	public Object selectAll() {
		List<User> ulist = userDao.findAll();
		final BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "success";
		result.object = ulist;

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	public Object selectEmail(String email) {
		User user = userDao.findByEmail(email);
		final BasicResponse result = new BasicResponse();
		if (user != null) {
			result.status = true;
			result.data = "성공";
			result.object = new JSONObject(user).toMap();
		} else {
			result.status = false;
			result.data = "유저가 없습니다.";
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	public Object selectName(String nickname) {
		User user = userDao.findByNickname(nickname);
		final BasicResponse result = new BasicResponse();

		if (user != null) {
			result.status = true;
			result.data = "성공";
			result.object = new JSONObject(user).toMap();
		} else {
			result.status = false;
			result.data = "유저가 없습니다.";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	public Object uploadImage(FileInfo fileInfo, boolean check) {
		final BasicResponse result = new BasicResponse();
		if(check) {
			result.status = true;
			result.data = "업로드 성공";
			result.object = new JSONObject(fileInfo).toMap();
		}else {
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

}
