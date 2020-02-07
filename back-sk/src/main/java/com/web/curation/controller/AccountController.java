package com.web.curation.controller;


import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.model.BasicResponse;
import com.web.curation.model.user.User;
import com.web.curation.service.AcountService;
import com.web.curation.service.JwtService;

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

	@Autowired
	private AcountService service;


	@Autowired
	private JwtService jwtService;
 
	@DeleteMapping("/account/delete")
    @ApiOperation(value = "삭제하기")
    public Object delete(@RequestParam(required = true) final String email) {
    	final BasicResponse result = new BasicResponse();
    	if(service.delete(email)) {
    		result.status = true;
    		result.data = "삭제 성공";    		
    	}else {
    		result.status = false;
    		result.data = "email이 존재하지 않습니다.";
    	}
		return new ResponseEntity<>(result, HttpStatus.OK);
    }
	
	@PutMapping("/account/update")
    @ApiOperation(value = "수정하기")
    public Object update(@RequestBody User request) {
    	final BasicResponse result = new BasicResponse();
    	
    	if(service.update(request)) {
    		result.status = true;
    		result.data = "업데이트 성공";
    		result.object = new JSONObject(request).toMap();    		
    	}else {
    		result.status = false;
    		result.data = "email이 존재하지않아요";    		
    	}
		

		return new ResponseEntity<>(result, HttpStatus.OK);
    }
	
	@PostMapping("/account/login")
	@ApiOperation(value = "로그인")
	public Object login(@RequestParam(required = true) final String email,
			@RequestParam(required = true) final String password) {

		System.out.println("&&&&&&&&&&&&&&&login");
		System.out.println(email + "==========" + password);

		final BasicResponse result = new BasicResponse();
		JSONObject dummyToken = new JSONObject();
		JSONObject dummyUser = new JSONObject();

		User tmp = service.login(email, password);

		// 1. DB 에 값이 존재하는지 판단
		if (tmp == null) {
			System.out.println("X");
			result.status = false;
			result.data = "email이 존재하지않습니다";

		} else {
			// 2. email과 pw일치하는지 판단
			dummyUser.put("email", email);
			dummyUser.put("nickname", tmp.getNickname());

			if (tmp.getPw() == "") {
				result.status = false;
				result.data = "비밀번호가 일치하지 않습니다";
				result.object = dummyUser.toMap();
			} else {
				result.status = true;
				result.data = "success";
				dummyToken.put("token", jwtService.makeJwt(tmp));
				dummyToken.put("user", dummyUser);

				result.object = dummyToken.toMap();
			}

		}
		System.out.println(result);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/account/signup")
	@ApiOperation(value = "가입하기")
	public Object signup(@RequestBody User request) {

		final BasicResponse result = new BasicResponse();
		System.out.println("###########가입하기"+request.toString());
		User tmp = service.signup(request);
		
		if (tmp.getEmail().equals("")) {
			result.status = false;
			result.data = "생성 실패(이메일 중복)";

		} else if (tmp.getNickname().equals("")) {
			result.status = false;
			result.data = "생성 실패(닉네임 중복)";
		}
		else {
			result.status = true;
			result.data = "회원 가입 성공";
			result.object = new JSONObject(request).toMap();
		}

		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	
    @GetMapping("/account/list")
    @ApiOperation(value = "유저보기")
    public Object selectAll() {
    	final BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "success";
		List<User> ulist = service.selectAll();
		result.object = ulist;

		return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @GetMapping("account/token")
	@ApiOperation(value = "토큰값 확인")
	public Object token(@RequestParam String token) {
		final BasicResponse result = new BasicResponse();
		
		try {
			int status = jwtService.checkJwt(token);
			switch (status) {
			case 0:
				result.status = true;
				result.data = "토큰이 유효합니다";
				break;

			case 1:
				result.status = false;
				result.data = "토큰 만료";
				break;
			case 2:
				result.status = false;
				result.data = "토큰 변조";
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
    
    @PostMapping("/account/selectEmail")
    @ApiOperation(value = "이메일로 유저찾기")
    public Object selectEmail(@RequestParam(required = true) final String email) {
    	User user = service.selectEmail(email);
    	System.out.println(email);
    	final BasicResponse result = new BasicResponse();
    	
		if (user != null) {
			result.status = true;
			result.data = "성공";
			result.object = new JSONObject(user).toMap();
		} else {
			result.status = false;
			result.data = "유저가 없습니다.";
			result.object = email;
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @PostMapping("/account/selectName")
    @ApiOperation(value = "닉네임으로 유저찾기")
    public Object selectName(@RequestParam(required = true) final String nickname) {
    	final BasicResponse result = new BasicResponse();

    	User user = service.selectName(nickname);
    	
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
    
    @PostMapping(value = "/account/uploadProfile")
	public Object upload(@RequestParam(required = true) final String email,
			@RequestParam(required = true) String profile) {
    	final BasicResponse result = new BasicResponse();
    	JSONObject dummyUser = new JSONObject();
    	
    	System.out.println(email+" "+profile);
    	if(service.uploadProfile(email, profile)) {
    		result.status = true;
    		result.data = "프로필 등록 성공";
    		dummyUser.put("email", email);
    		dummyUser.put("profile", profile);
    		result.object = dummyUser.toMap();
    	}else {
    		result.status = false;
    		result.data = "프로필 등록 실패 ! - 존재하지않는 유저";
    	}
    	return new ResponseEntity<>(result, HttpStatus.OK);
	}
    
	@PostMapping(value = "/account/getProfile")
	public Object getProfile(@RequestParam(required = true) final String email) {
		final BasicResponse result = new BasicResponse();
//		System.out.println("프로필 가져오기 !!!!!!!!!");
		User user = service.getProfile(email);
		if(user!=null) {
			// profile 이 없음
			if(user.getProfile()==null) {
				result.status = true;
				result.data = "profile 존재 X";
			}else {
				result.status = true;
				result.data = "profile 가져오기 성공";
				result.object = user;
			}
		}else {
			// 존재하지 않는 유저
			result.status = false;
			result.data = "email 존재 X";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
    
	
	@PostMapping("/account/changePW")
	@ApiOperation(value = "비밀번호 변경")
	public Object changePW(@RequestParam(required = true) final String email,
			@RequestParam(required = true) String password) {
		final BasicResponse result = new BasicResponse();
		System.out.println("비밀번호 변경 !!!!!!!!!!!!!");
		User user = service.selectEmail(email);
		if(user!=null) {
			// update 호출
			user.setPw(service.EncodePW(password));
			if(service.update(user)) {
				result.status = true;
				result.data = "비밀번호바꾸기 성공";
				result.object = new JSONObject(user).toMap();				
			}else {
				result.status = false;
				result.data = "비밀번호바꾸기 실패";
			}
			
		}else {
			result.status = false;
			result.data = "존재하지않는 email입니다";
		}
		System.out.println(result);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	

}
