package com.web.curation.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.model.BasicResponse;
import com.web.curation.service.FollowService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
public class FollowController {

	@Autowired
	FollowService service;

	@PostMapping("/follow/accept")
	@ApiOperation(value = "팔로우 승낙")
	public Object addFollow(@RequestParam(required = true) final String fan,
			@RequestParam(required = true) final String star ) {
		
		boolean check = service.addFollow(fan,star);
		final BasicResponse result = new BasicResponse();
		
		if(check) {
			result.status = true;
			result.data = "팔로워/팔로잉 리스트 추가";
		}else {
			result.status = false;
			result.data = "추가 실패";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/follow/decline")
	@ApiOperation(value = "팔로우 거절")
	public Object declineFollow(@RequestParam(required = true) final String fan,
			@RequestParam(required = true) final String star ) {
		
		return service.declineFollow(fan,star);
	}
	@PostMapping("/follow/request")
	@ApiOperation(value = "팔로우 요청 알람생성")
	public Object reqFollow(@RequestParam(required = true) final String fan,
			@RequestParam(required = true) final String star ) {
		
		return service.requestFollow(fan, star);
	}
	
	@PostMapping("/follow/starList")
	@ApiOperation(value = "팔로잉 리스트")
	public Object starList(@RequestParam(required = true) final String email) {
		return service.starList(email);
	}
	
	@PostMapping("/follow/fanList")
	@ApiOperation(value = "팔로워 리스트")
	public Object fanList(@RequestParam(required = true) final String email) {
		return service.fanList(email);
	}
	
	@PostMapping("/follow/alarmList")
	@ApiOperation(value = "알람 리스트")
	public Object alarmList(@RequestParam(required = true) final String email) {
		
		return service.alarmList(email);
	}
	
	@PostMapping("/follow/alarmCheck")
	@ApiOperation(value = "알람 읽음표시로 변경")
	public Object alarmCheck(@RequestParam(required = true) final String email) {
		
		return service.alarmCheck(email);
	}
	
	@PostMapping("/follow/nesAlarm")
	@ApiOperation(value = "새 알람 확인")
	public Object newAlarm(@RequestParam(required = true) final String email) {
		
		return service.newAlarm(email);
	}
	
}
