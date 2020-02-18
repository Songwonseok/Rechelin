package com.web.curation.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.web.curation.model.NaverLogin;
import com.web.curation.model.DAO.UserDao;
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
	UserDao userDao;
	
	NaverLogin naver;
	
	@GetMapping("/account/naverlogin")
	@ApiOperation(value = "로그인")
	public String naverlogin(@RequestParam(value = "code") String code, @RequestParam(value = "state") String state){
		
		String apiURL;
		apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
		apiURL += "client_id=" + naver.ClientId;
		apiURL += "&client_secret=" + naver.ClientSecret;
		apiURL += "&code=" + code;
		apiURL += "&state=" + state;
		String access_token = "";
		String refresh_token = "";
		String email = "";
		
		try {
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			System.out.println(con);
			int responseCode = con.getResponseCode();
			BufferedReader br;
			
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer res = new StringBuffer();
			JSONObject dummyUser = new JSONObject();

			while ((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}
			br.close();
			if (responseCode == 200) { // 성공적으로 토큰을 가져온다면
				System.out.println(res.toString());

				JsonParser parser = new JsonParser();
				JsonElement accessElement = parser.parse(res.toString());
				access_token = accessElement.getAsJsonObject().get("access_token").getAsString();

				String tmp = NaverLogin.getUserInfo(access_token);
				JsonElement userInfoElement = parser.parse(tmp);
				System.out.println("UserInfo");
				System.out.println(userInfoElement);

				email = userInfoElement.getAsJsonObject().get("response").getAsJsonObject().get("email").getAsString();
				String nickname = userInfoElement.getAsJsonObject().get("response").getAsJsonObject().get("name").getAsString();
				
				// DB 에 존재하는지 확인
				// 이메일로 찾고 -> num을 가져온다
				User user = userDao.findByEmail(email);
				long num = 0;
				if (user == null) {
					// DB에 계정 저장
					System.out.println("XXXX");
					user = new User(email, nickname);
					userDao.save(user);
					user = userDao.findByEmail(email);
				}
				
				num = user.getId();
				service.changePW(user.getId(), "12341234a");
				System.out.println(user.toString());
				
				Authentication authentication = authenticationManager
						.authenticate(new UsernamePasswordAuthenticationToken(email, "12341234a"));
				
				System.out.println("***************************************");
				System.out.println(authentication.toString());
				SecurityContextHolder.getContext().setAuthentication(authentication);

				String jwt = tokenProvider.generateToken(authentication);
				
				//// DB에서 존재하는 이메일인지 체크
				//// 없으면 DB 에저장
				//// ===> login으로 바로 이동
				return "redirect:http://localhost:3000/access_token/"+access_token+"/userid/"+num+"/jwt/"+jwt;
			}
			return "redirect:http://localhost:3000/sfda";
			
		} catch (Exception e) {
			return "redirect:http://localhost:3000/loasfdgin";
		}
	}
	
}
