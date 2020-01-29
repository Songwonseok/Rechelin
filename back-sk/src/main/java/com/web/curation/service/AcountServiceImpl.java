package com.web.curation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.web.curation.dao.user.UserDao;
import com.web.curation.model.BasicResponse;
import com.web.curation.model.user.User;

@Service
public class AcountServiceImpl implements AcountService {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	PasswordEncoder passwordEncoder; // 비밀번호 암호화
	
	public User login(String email, String password) {
		
		User tmp = userDao.findByEmail(email);
		if(tmp!=null) {
			// 비밀번호 일치 X
			
			System.out.println(password+" "+tmp.getPw());
			if(!passwordEncoder.matches(password, tmp.getPw())) {
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
		if( userDao.findByEmail(request.getEmail()) !=null) {
			// 이메일 중복
			request.setEmail("");
		} else if(userDao.findByNickname(request.getNickname()) !=null) {
			// 닉네임 중복
			request.setNickname("");
		}
		
		request.setPw(passwordEncoder.encode(request.getPw())); // 암호화
		userDao.save(request);
		
		return request;
	}

	public Object delete(String email) {
    	User user = userDao.getOne(email);
        userDao.delete(user);
    	final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "삭제 성공";
        
        return new ResponseEntity<>(result, HttpStatus.OK);
	}

	public Object update(User request) {
//		userDao.updateUserInfo(request);
		final BasicResponse result = new BasicResponse();
		
        result.status = true;
        result.data = "업데이트 성공";
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	public Object selectAll() {
		List<User> ulist = userDao.findAll();
    	final BasicResponse result = new BasicResponse();
    	result.status = true;
        result.data = "success";
        //일단 걍 리스트 보냄
        result.object = ulist; 
        
        return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
