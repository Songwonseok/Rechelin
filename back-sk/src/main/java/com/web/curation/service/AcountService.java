package com.web.curation.service;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.web.curation.dao.user.UserDao;
import com.web.curation.model.BasicResponse;
import com.web.curation.model.user.User;

@Service
public class AcountService implements AcountServiceImpl {
	
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
    	
    	if(userDao.findByEmail(request.getEmail()) !=null) {
    		result.status = false;
            result.data = "생성 실패(이메일 중복)";
            return new ResponseEntity<>(result, HttpStatus.OK);
            
    	}else if(userDao.findByNickname(request.getNickname()) !=null) {
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
