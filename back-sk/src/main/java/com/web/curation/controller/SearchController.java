package com.web.curation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.model.BasicResponse;
import com.web.curation.service.SearchService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


	@ApiResponses(value = {
    @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
    @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
    @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
    @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class)})

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController 
public class SearchController{
		
	@Autowired
	SearchService service;

    @PostMapping("/search/user")
    @ApiOperation(value = "검색 리스트 등록")
    public Object searchUser(@RequestParam(required = true) final String email,
			@RequestParam(required = true) final String searchname) {
    	
    	System.out.println(email + " "+ searchname);
    	final BasicResponse result = new BasicResponse();
    	if(service.addUser(email, searchname)) {
    		result.status = true;
    		result.data = "등록 성공";    		
    	}else {
    		result.status = false;
    		result.data = "등록 실패";
    	}
		return new ResponseEntity<>(result, HttpStatus.OK);
    	
    }
    
    @PostMapping("/search/recentUser")
    @ApiOperation(value = "최근 유저 검색 리스트")
    public Object userList(@RequestParam(required = true) final String email) {
    	System.out.println(email);
    	System.out.println(email);
    	System.out.println(email);
    	final BasicResponse result = new BasicResponse();
    	result.status = true;
		result.data = "성공"; 
		result.object = service.userList(email);;
		return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    
    
    
}
