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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.dao.user.StoreDao;
import com.web.curation.model.BasicResponse;
import com.web.curation.model.user.Hashtag;
import com.web.curation.model.user.Store;
import com.web.curation.service.StoreService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
public class StoreController {

	@Autowired
	StoreService service;

	@Autowired
	StoreDao storeDao;

	

	@PostMapping("/store/create")
	@ApiOperation(value = "등록하기")
	public Object signup(@RequestBody Store request) {
		final BasicResponse result = new BasicResponse();
		if(service.create(request)) {
			result.status = true;
			result.data = "식당 등록 성공";
			result.object = new JSONObject(request).toMap();
		}else {
			result.status = false;
			result.data = "이미 등록되었습니다.";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@DeleteMapping("/store/delete")
	@ApiOperation(value = "삭제하기")
	public Object delete(@RequestParam(required = true) final long num) {
		final BasicResponse result = new BasicResponse();
		if (service.delete(num)) {
			result.status = true;
			result.data = "삭제 성공";
		} else {
			result.status = true;
			result.data = "삭제 성공";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/store/list")
	@ApiOperation(value = "식당리스트")
	public Object selectAll() {
		final BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "성공";
		result.object = service.selectAll();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/store/selectOne")
	@ApiOperation(value = "식당정보")
	public Object selectOne(@RequestParam(required = true) final long num) {
		final BasicResponse result = new BasicResponse();
		Store store = service.selectOne(num);
		if(store != null) {
				result.status = true;
				result.data = "성공";
				result.object = new JSONObject(store).toMap();
		}else {
			result.status = false;
			result.data = "식당이 없습니다.";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/store/tags")
	@ApiOperation(value = "태그정보")
	public Object tags(@RequestParam(required = true) final long num) {
		final BasicResponse result = new BasicResponse();
		List<Hashtag> list = service.storeTags(num);
		if(list != null) {
				result.status = true;
				result.data = "성공";
		}else {
			result.status = false;
			result.data = "리뷰가 없습니다.";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
