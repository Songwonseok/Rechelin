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
import com.web.curation.model.DTO.User;
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
    public Object delete(@RequestParam(required = true) final long id) {
    	final BasicResponse result = new BasicResponse();
    	if(service.delete(id)) {
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
    	System.out.println("^^^^^^^^^^^^^^"+email);
    	final BasicResponse result = new BasicResponse();
    	
		if (user != null) {
			result.status = true;
			result.data = "이메일로 유저 찾기 성공";
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
			result.data = "닉네임으로 유저찾기 성공";
			result.object = new JSONObject(user).toMap();
		} else {
			result.status = false;
			result.data = "유저가 없습니다.";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @PostMapping("/account/selectId")
    @ApiOperation(value = "id로 유저찾기")
    public Object selectId(@RequestParam(required = true) final long id) {
    	final BasicResponse result = new BasicResponse();

    	User user = service.selectId(id);
    	
		if (user != null) {
			result.status = true;
			result.data = "id로 유저찾기 성공";
			result.object = new JSONObject(user).toMap();
		} else {
			result.status = false;
			result.data = "유저가 없습니다.";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @PostMapping(value = "/account/uploadProfile")
	public Object upload(@RequestParam(required = true) final long id,
			@RequestParam(required = true) String profile) {
    	final BasicResponse result = new BasicResponse();
    	
    	if(service.uploadProfile(id, profile)) {
    		result.status = true;
    		result.data = "프로필 등록 성공";
    		User user = service.selectId(id);
    		result.object = user;
    	}else {
    		result.status = false;
    		result.data = "프로필 등록 실패 ! - 존재하지않는 유저";
    	}
    	return new ResponseEntity<>(result, HttpStatus.OK);
	}
    
	@PostMapping(value = "/account/getProfile")
	public Object getProfile(@RequestParam(required = true) final long id) {
		final BasicResponse result = new BasicResponse();
//		System.out.println("프로필 가져오기 !!!!!!!!!");
		User user = service.getProfile(id);
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
	public Object changePW(@RequestParam(required = true) final long id,
			@RequestParam(required = true) String password) {
		final BasicResponse result = new BasicResponse();
		System.out.println("비밀번호 변경 !!!!!!!!!!!!!");
		if(service.changePW(id, password)) {
			result.status = true;
			result.data = "비밀번호바꾸기 성공";
			result.object = service.selectId(id);
		}else {
			result.status = false;
			result.data = "존재하지않는 email입니다";
		}
		System.out.println(result);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}


}
