package com.web.curation.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.web.curation.service.JwtService;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter{
	/*
	 * 헤더에서 토큰을 꺼내 검사
	 * true - 토큰 확인 -> 다음 일 수행
	 * false - 에러 status 반환
	 * */
//	@Autowired 
//	private JwtService jwtService;
//	
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//		try {
//			// request가 어떻게 오는지 확인 !!!
//	    	String token = request.getHeader("token"); // token값을 가져오기
//			if (jwtService.checkJwt(token) == 0) { 
//				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//				return false;
//			}
//			return true;
//		} catch (Exception e) { 
//			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//			return false;
//		}
//    }
    
}
