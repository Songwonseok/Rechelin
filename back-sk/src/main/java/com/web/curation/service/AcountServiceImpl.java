package com.web.curation.service;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.web.curation.dao.user.UserDao;
import com.web.curation.model.BasicResponse;
import com.web.curation.model.FileInfo;
import com.web.curation.model.NaverLogin;
import com.web.curation.model.user.User;

@Service
public class AcountServiceImpl implements AcountService {

	@Autowired
	UserDao userDao;

	@Autowired
	PasswordEncoder passwordEncoder; // 비밀번호 암호화

	NaverLogin naver;
	
	public User login(String email, String password) {
		
		User tmp = userDao.findByEmail(email);
		if (tmp != null) {
			// 비밀번호 일치 X

			System.out.println(password + " " + tmp.getPw());
			if (!passwordEncoder.matches(password, tmp.getPw())) {
				System.out.println("비밀번호 틀림");
				tmp.setPw("");
			}
			return tmp;
		}
		System.out.println("유저 존재X");
		// DB에 존재 X
		return null;

	}

	public User signup(User request) {
		if (userDao.findByEmail(request.getEmail()) != null) {
			// 이메일 중복
			request.setEmail("");
		} else if (userDao.findByNickname(request.getNickname()) != null) {
			// 닉네임 중복
			request.setNickname("");
		}

		request.setPw(EncodePW(request.getPw())); // 암호화
		userDao.save(request);

		return request;
	}

	public boolean delete(String email) {
		User user = userDao.findByEmail(email);
		if (user != null) {
			userDao.delete(user);
			return true;
		} else
			return false;
	}

	public boolean update(User request) {
		User user = userDao.findByEmail(request.getEmail());
		if(user!=null) {
			request.setPw(passwordEncoder.encode(request.getPw()));
			user.updateUser(request);
			userDao.save(user);
			return true;
		}
		return false;
	}

	public List<User> selectAll() {
		return userDao.findAll();
	}

	public User selectEmail(String email) {
		User user = userDao.findByEmail(email);
		return user;
	}

	public User selectName(String nickname) {
		return userDao.findByNickname(nickname);
	}

	public Object uploadImage(FileInfo fileInfo, boolean check) {
		final BasicResponse result = new BasicResponse();
		if (check) {
			result.status = true;
			result.data = "업로드 성공";
			result.object = new JSONObject(fileInfo).toMap();
		} else {
			result.status = false;
			result.data = "업로드 실패";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@Override
	public InputStream getProfile(String email) {
		User user = userDao.findByEmail(email);
		InputStream in = getClass().getResourceAsStream(user.getProfile());
		// 사진이 없으면 defalut 이미지 경로 넣음
		// if(....)
		return in;
	}

	@Override
	public String EncodePW(String Pw) {
		return passwordEncoder.encode(Pw);
	}

	@Override
	public User NaverLogin(String code, String state) {
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
				User user = userDao.findByEmail(email);
				if (user == null) {
					// DB에 계정 저장
					System.out.println("XXXX");
					user = new User(email, nickname);
					userDao.save(user);
				}
				System.out.println(user.toString());

				//// DB에서 존재하는 이메일인지 체크
				//// 없으면 DB 에저장
				//// ===> login으로 바로 이동
				
				return user;
			}
			return null;
			
		} catch (Exception e) {
			return null;
		}
		
	}

}
