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
    @ApiOperation(value = "�궘�젣�븯湲�")
    public Object delete(@RequestParam(required = true) final long id) {
    	final BasicResponse result = new BasicResponse();
    	if(service.delete(id)) {
    		result.status = true;
    		result.data = "�궘�젣 �꽦怨�";    		
    	}else {
    		result.status = false;
    		result.data = "email�씠 議댁옱�븯吏� �븡�뒿�땲�떎.";
    	}
		return new ResponseEntity<>(result, HttpStatus.OK);
    }
	
	@PutMapping("/account/update")
    @ApiOperation(value = "�닔�젙�븯湲�")
    public Object update(@RequestBody User request) {
    	final BasicResponse result = new BasicResponse();
    	
    	if(service.update(request)) {
    		result.status = true;
    		result.data = "�뾽�뜲�씠�듃 �꽦怨�";
    		result.object = new JSONObject(request).toMap();    		
    	}else {
    		result.status = false;
    		result.data = "email�씠 議댁옱�븯吏��븡�븘�슂";    		
    	}
		

		return new ResponseEntity<>(result, HttpStatus.OK);
    }
	

	
    @GetMapping("/account/list")
    @ApiOperation(value = "�쑀��蹂닿린")
    public Object selectAll() {
    	final BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "success";
		List<User> ulist = service.selectAll();
		result.object = ulist;

		return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @GetMapping("account/token")
	@ApiOperation(value = "�넗�겙媛� �솗�씤")
	public Object token(@RequestParam String token) {
		final BasicResponse result = new BasicResponse();
		
		try {
			int status = jwtService.checkJwt(token);
			switch (status) {
			case 0:
				result.status = true;
				result.data = "�넗�겙�씠 �쑀�슚�빀�땲�떎";
				break;

			case 1:
				result.status = false;
				result.data = "�넗�겙 留뚮즺";
				break;
			case 2:
				result.status = false;
				result.data = "�넗�겙 蹂�議�";
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
    
    @PostMapping("/account/selectEmail")
    @ApiOperation(value = "�씠硫붿씪濡� �쑀��李얘린")
    public Object selectEmail(@RequestParam(required = true) final String email) {
    	User user = service.selectEmail(email);
    	System.out.println("^^^^^^^^^^^^^^"+email);
    	final BasicResponse result = new BasicResponse();
    	
		if (user != null) {
			result.status = true;
			result.data = "�씠硫붿씪濡� �쑀�� 李얘린 �꽦怨�";
			result.object = new JSONObject(user).toMap();
		} else {
			result.status = false;
			result.data = "�쑀��媛� �뾾�뒿�땲�떎.";
			result.object = email;
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @PostMapping("/account/selectName")
    @ApiOperation(value = "�땳�꽕�엫�쑝濡� �쑀��李얘린")
    public Object selectName(@RequestParam(required = true) final String nickname) {
    	final BasicResponse result = new BasicResponse();

    	User user = service.selectName(nickname);
    	
		if (user != null) {
			result.status = true;
			result.data = "�땳�꽕�엫�쑝濡� �쑀��李얘린 �꽦怨�";
			result.object = new JSONObject(user).toMap();
		} else {
			result.status = false;
			result.data = "�쑀��媛� �뾾�뒿�땲�떎.";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @PostMapping("/account/selectId")
    @ApiOperation(value = "id濡� �쑀��李얘린")
    public Object selectId(@RequestParam(required = true) final long id) {
    	final BasicResponse result = new BasicResponse();

    	User user = service.selectId(id);
    	
		if (user != null) {
			result.status = true;
			result.data = "id濡� �쑀��李얘린 �꽦怨�";
			result.object = new JSONObject(user).toMap();
		} else {
			result.status = false;
			result.data = "�쑀��媛� �뾾�뒿�땲�떎.";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @PostMapping(value = "/account/uploadProfile")
	public Object upload(@RequestParam(required = true) final long id,
			@RequestParam(required = true) String profile) {
    	final BasicResponse result = new BasicResponse();
    	
    	if(service.uploadProfile(id, profile)) {
    		result.status = true;
    		result.data = "�봽濡쒗븘 �벑濡� �꽦怨�";
    		User user = service.selectId(id);
    		result.object = user;
    	}else {
    		result.status = false;
    		result.data = "�봽濡쒗븘 �벑濡� �떎�뙣 ! - 議댁옱�븯吏��븡�뒗 �쑀��";
    	}
    	return new ResponseEntity<>(result, HttpStatus.OK);
	}
    
	@PostMapping(value = "/account/getProfile")
	public Object getProfile(@RequestParam(required = true) final long id) {
		final BasicResponse result = new BasicResponse();
//		System.out.println("�봽濡쒗븘 媛��졇�삤湲� !!!!!!!!!");
		User user = service.getProfile(id);
		if(user!=null) {
			// profile �씠 �뾾�쓬
			if(user.getProfile()==null) {
				result.status = true;
				result.data = "profile 議댁옱 X";
			}else {
				result.status = true;
				result.data = "profile 媛��졇�삤湲� �꽦怨�";
				result.object = user;
			}
		}else {
			// 議댁옱�븯吏� �븡�뒗 �쑀��
			result.status = false;
			result.data = "email 議댁옱 X";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
    
	
	@PostMapping("/account/changePW")
	@ApiOperation(value = "鍮꾨�踰덊샇 蹂�寃�")
	public Object changePW(@RequestParam(required = true) final long id,
			@RequestParam(required = true) String password) {
		final BasicResponse result = new BasicResponse();
		System.out.println("鍮꾨�踰덊샇 蹂�寃� !!!!!!!!!!!!!");
		if(service.changePW(id, password)) {
			result.status = true;
			result.data = "鍮꾨�踰덊샇諛붽씀湲� �꽦怨�";
			result.object = service.selectId(id);
		}else {
			result.status = false;
			result.data = "議댁옱�븯吏��븡�뒗 email�엯�땲�떎";
		}
		System.out.println(result);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/account/userTop")
	@ApiOperation(value ="리뷰 작성순 랭킹")
	public Object userTop() {
		final BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "리뷰 작성순 랭킹 가져오기 성공";
		result.object = service.userTop();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
