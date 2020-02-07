package com.web.curation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.curation.model.user.User;
import com.web.curation.service.AcountService;
import com.web.curation.service.JwtService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@Controller
public class SocialController {
	
	@Autowired
	private AcountService service;
	
	@Autowired
	private JwtService jwtService;
	
	@GetMapping("account/naverlogin")
	@ApiOperation(value = "로그인")
	public String naverlogin(@RequestParam(value = "code") String code, @RequestParam(value = "state") String state){
		User user = service.NaverLogin(code, state);
		if(user!=null) {
			// 로그인 성공페이지
			return "redirect:http://localhost:3000/main/token?="+jwtService.makeJwt(user);			
		}else {
			// 로그인 실패
			return "redirect:http://localhost:3000/main/404";
		}
	}
	
}
