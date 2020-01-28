package com.web.curation.controller.account;

<<<<<<< HEAD
import javax.validation.Valid;
=======
>>>>>>> ccc43d584eee082518cddf52ac949f20a41848c3

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
<<<<<<< HEAD
=======
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
>>>>>>> ccc43d584eee082518cddf52ac949f20a41848c3
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.dao.user.UserDao;
import com.web.curation.model.BasicResponse;
import com.web.curation.model.user.User;
<<<<<<< HEAD
=======
import com.web.curation.service.AcountServiceImpl;
>>>>>>> ccc43d584eee082518cddf52ac949f20a41848c3

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
public class AccountController {
	// 디비 셋팅 후 주석을 푸세요.
	@Autowired
	UserDao userDao;

	@PostMapping("/account/login")
	@ApiOperation(value = "로그인")
	public Object login(@RequestParam(required = true) final String email,
			@RequestParam(required = true) final String password) {
		System.out.println("&&&&&&&&&&&&&&&login");
		System.out.println(email + "==========" + password);

		final BasicResponse result = new BasicResponse();
		JSONObject dummyUser = new JSONObject();

		User tmp = userDao.getUserByEmail(email);
		// 1. DB 에 값이 존재하는지 판단
		if (tmp == null) {
			System.out.println("X");
			result.status = false;
			result.data = "email이 존재하지않습니다";

		} else {
			// 2. email과 pw일치하는지 판단
			if (password.equals(tmp.getPw())) {
				result.status = true;
				result.data = "success";
			} else {
				result.status = false;
				result.data = "비밀번호가 일치하지 않습니다";
			}

			dummyUser.put("email", email);
			dummyUser.put("nickname", tmp.getNickname());
			result.object = dummyUser.toMap();

		}

		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@PostMapping("/account/signup")
	@ApiOperation(value = "가입하기")
	public Object signup(@Valid @RequestBody SignupRequest request) {
		// 이메일, 닉네임 중복처리 필수
		// 회원가입단을 생성해 보세요.

		System.out.println("Join.................");
		System.out.println(request.getEmail() + " " + request.getNickname() + " " + request.getPassword());

		JSONObject dummyUser = new JSONObject();

		dummyUser.put("uid", "test_uid");
		dummyUser.put("email", "test@test.com");
		dummyUser.put("nickname", "test_nickname");

		final BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "success";
		result.object = dummyUser;
		System.out.println(result);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

<<<<<<< HEAD
=======
	@ApiResponses(value = {
    @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
    @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
    @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
    @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class)})

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController 
public class AccountController{
		
	@Autowired
	AcountServiceImpl service;

    @Autowired
    UserDao userDao;

    @PostMapping("/account/login")
    @ApiOperation(value = "로그인")
    public Object login(@RequestParam(required = true) final String email,
                        @RequestParam(required = true) final String password){

    	System.out.println("&&&&&&&&&&&&&&&login");
        System.out.println(email+"=========="+password);
        
        final BasicResponse result = new BasicResponse();
        JSONObject dummyUser = new JSONObject();

        User tmp = userDao.findByEmail(email);
        // 1. DB 에 값이 존재하는지 판단
        if(tmp == null) {
        	System.out.println("X");
        	result.status = false;
            result.data = "email이 존재하지않습니다";
            
        }else {
        	// 2. email과 pw일치하는지 판단
        	if(password.equals(tmp.getPw())) {
        		result.status = true;
        		result.data = "success";        		
        	}else {
        		result.status = false;
        		result.data = "비밀번호가 일치하지 않습니다";
        	}
        	
        	dummyUser.put("email",email);
        	dummyUser.put("nickname",tmp.getNickname());
        	result.object = dummyUser.toMap();
        	
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    
    @PostMapping("/account/signup")
    @ApiOperation(value = "가입하기")
    public Object signup(@RequestBody User request) {
    	
    	return service.signup(request);
    }
    
    @DeleteMapping("/account/delete")
    @ApiOperation(value = "삭제하기")
    public Object delete(@RequestParam(required = true) final String email) {
        //이메일, 닉네임 중복처리 필수
        //회원가입단을 생성해 보세요.
    	return service.delete(email);
    }
    
    @PutMapping("/account/update")
    @ApiOperation(value = "수정하기")
    public Object update(@RequestBody User request) {
    	return service.update(request);
    }
    
    @GetMapping("/account/list")
    @ApiOperation(value = "유저보기")
    public Object selectAll() {
    	
        return service.selectAll();
    }
    
    
    
>>>>>>> ccc43d584eee082518cddf52ac949f20a41848c3
}
