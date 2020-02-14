package com.web.curation.service;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.web.curation.model.NaverLogin;
import com.web.curation.model.DAO.RoleDao;
import com.web.curation.model.DAO.UserDao;
import com.web.curation.model.DTO.Role;
import com.web.curation.model.DTO.RoleName;
import com.web.curation.model.DTO.User;

@Service
public class AcountServiceImpl implements AcountService {

	@Autowired
	UserDao userDao;
	@Autowired
	RoleDao roleDao;
	
	@Autowired
	PasswordEncoder passwordEncoder; // 鍮꾨�踰덊샇 �븫�샇�솕

	NaverLogin naver;
	
	public User login(String email, String password) {
		
		User tmp = userDao.findByEmail(email);
		if (tmp != null) {
			// 鍮꾨�踰덊샇 �씪移� X

			System.out.println(password + " " + tmp.getPw());
			
			if (!passwordEncoder.matches(password, tmp.getPw())) {
				System.out.println("鍮꾨�踰덊샇 ��由�");
				tmp.setPw("");
			}
			return tmp;
		}
		System.out.println("�쑀�� 議댁옱X");
		// DB�뿉 議댁옱 X
		return null;

	}

	public User signup(User request) {
		if (userDao.findByEmail(request.getEmail()) != null) {
			// �씠硫붿씪 以묐났
			request.setEmail("");
		} else if (userDao.findByNickname(request.getNickname()) != null) {
			// �땳�꽕�엫 以묐났
			request.setNickname("");
		}
		request.setPw(EncodePW(request.getPw())); // �븫�샇�솕
		request.setProfile(null); // 泥섏쓬 �봽濡쒗븘�쓣 留뚮뱾�뼱二쇱� �븡�뒗�떎
		Role userRole = roleDao.findByName(RoleName.ROLE_USER);
		request.setRole(Collections.singleton(userRole));
		userDao.save(request);
		return request;
	}

	public boolean delete(long id) {
		User user = userDao.findById(id);
		if (user != null) {
			userDao.deleteById(id);;
			return true;
		} else
			return false;
	}

	public boolean update(User request) {
		User user = userDao.findByEmail(request.getEmail());
		if(user!=null) {
			user.updateUser(request);
			// profile怨� 鍮꾨�踰덊샇 �젣�쇅�븯怨� �뾽�뜲�씠�듃
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

	public boolean uploadProfile(long id,String profile) {
		// 1) email 濡�  �쑀�� 李얘린
		User user = userDao.findById(id);
		
		if(user!=null) {
			// 2) User�젙蹂� �뾽�뜲�씠�듃
			user.setProfile(profile);
			// 3) userDao.save()
			userDao.save(user);
			System.out.println(user.toString());
			return true;
		}
		return false;
	}

	@Override
	public User getProfile(long id) {
		User user = userDao.findById(id);
		return user;
	}

	public String EncodePW(String Pw) {
		return passwordEncoder.encode(Pw);
	}

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
			
			if (responseCode == 200) { // �젙�긽 �샇異�
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // �뿉�윭 諛쒖깮
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer res = new StringBuffer();
			JSONObject dummyUser = new JSONObject();

			while ((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}
			br.close();
			if (responseCode == 200) { // �꽦怨듭쟻�쑝濡� �넗�겙�쓣 媛��졇�삩�떎硫�
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
				
				// DB �뿉 議댁옱�븯�뒗吏� �솗�씤
				User user = userDao.findByEmail(email);
				if (user == null) {
					// DB�뿉 怨꾩젙 ���옣
					System.out.println("XXXX");
					user = new User(email, nickname);
					userDao.save(user);
				}
				System.out.println(user.toString());

				//// DB�뿉�꽌 議댁옱�븯�뒗 �씠硫붿씪�씤吏� 泥댄겕
				//// �뾾�쑝硫� DB �뿉���옣
				//// ===> login�쑝濡� 諛붾줈 �씠�룞
				
				return user;
			}
			return null;
			
		} catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public boolean changePW(long id, String password) {
		User user = userDao.findById(id);
		if(user!=null) {
			user.setPw(EncodePW(password));
			userDao.save(user);
			return true;
		}else return false;
	}

	@Override
	public User selectId(long id) {
		User user = userDao.findById(id);
		return user;
	}

	@Override
	public List<User> userTop() {
		List<User> list = userDao.topUser();
		return list;
	}

}
