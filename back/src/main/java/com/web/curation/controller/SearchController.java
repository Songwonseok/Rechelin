package com.web.curation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.model.BasicResponse;
import com.web.curation.model.TagsResponse;
import com.web.curation.service.SearchService;
import com.web.curation.service.StoreService;

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
	
	@Autowired
	StoreService storeservice;

    @PostMapping("/search/user")
    @ApiOperation(value = "유저 검색 리스트에 등록")
    public Object searchUser(@RequestParam(required = true) final long id,
			@RequestParam(required = true) final String searchname) {
    	
    	final BasicResponse result = new BasicResponse();
    	if(service.addUser(id, searchname)) {
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
    public Object userList(@RequestParam(required = true) final long id) {
    	final BasicResponse result = new BasicResponse();
    	result.status = true;
		result.data = "성공"; 
		result.object = service.userList(id);;
		return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @PostMapping("/search/store")
    @ApiOperation(value = "식당 검색 리스트에 등록")
    public Object searchStore(@RequestParam(required = true) final long id,
			@RequestParam(required = true) final String searchname) {
    	
    	System.out.println(id + " "+ searchname);
    	final BasicResponse result = new BasicResponse();
    	if(service.addStore(id, searchname)) {
    		result.status = true;
    		result.data = "등록 성공";    		
    	}else {
    		result.status = false;
    		result.data = "등록 실패";
    	}
		return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @PostMapping("/search/recentStore")
    @ApiOperation(value = "최근 식당검색")
    public Object storeList(@RequestParam(required = true) final long id) {
    	final BasicResponse result = new BasicResponse();
    	result.status = true;
		result.data = "성공"; 
		result.object = service.storeList(id);;
		return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @GetMapping("/search/tagSearch")
	@ApiOperation(value = "스토어태그 리스트")
	public Object test() {
		final BasicResponse result = new BasicResponse();
		List<TagsResponse> list = service.tagSearch();
		if (list != null) {
			result.status = true;
			result.data = "성공";
			result.object = list;
		} else {
			result.status = false;
			result.data = "결과가 없습니다.";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
    
    
}
