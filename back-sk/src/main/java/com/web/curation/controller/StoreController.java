package com.web.curation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.dao.user.StoreDao;
import com.web.curation.model.BasicResponse;
import com.web.curation.model.user.Store;
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
public class StoreController{
		
	@Autowired
	StoreService service;

    @Autowired
    StoreDao storeDao;
    
    @PostMapping("/store/create")
    @ApiOperation(value = "등록하기")
    public Object signup(@RequestBody Store request) {
    	return service.create(request);
    }
    
    @DeleteMapping("/store/delete")
    @ApiOperation(value = "삭제하기")
    public Object delete(@RequestParam(required = true) final long num) {
    	return service.delete(num);
    }
    
    @GetMapping("/store/list")
    @ApiOperation(value = "식당리스트")
    public Object selectAll() {
        return service.selectAll();
    }
    
    @PostMapping("/store/searchName")
    @ApiOperation(value = "이름으로 검색")
    public Object searchName(@RequestParam(required = true) final String name) {
		return service.searchName(name);

    }
    @PostMapping("/store/selectOne")
    @ApiOperation(value = "식당정보")
    public Object selectOne(@RequestParam(required = true) final long num) {
		return service.selectOne(num);
    }
    
    @PostMapping("/store/tags")
    @ApiOperation(value = "태그정보")
    public Object tags(@RequestParam(required = true) final long num) {
    	
    	
		return service.selectOne(num);
    }
    
    
    
    
}
