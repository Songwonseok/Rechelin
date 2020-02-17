package com.web.curation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.curation.model.DTO.User;
import com.web.curation.security.JwtTokenProvider;
import com.web.curation.service.AcountService;
import com.web.curation.service.JwtService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@Controller
public class SocialController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtTokenProvider tokenProvider;
	
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
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPw()));
//
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = tokenProvider.generateToken(authentication);
			return "redirect:http://localhost:3000/token?="+jwt;			
		}else {
			// 로그인 실패
			return "redirect:http://localhost:3000/404";
		}
	}
	
}
