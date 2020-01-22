package com.web.curation.controller.account;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.web.curation.dao.user.UserDao;
import com.web.curation.model.BasicResponse;
import com.web.curation.model.user.SignupRequest;
import com.web.curation.naver.login.NaverLogin;

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
public class AccountController{
    // 디비 셋팅 후 주석을 푸세요.
    @Autowired
    UserDao userDao;
    
    NaverLogin naver;
    
    
    @GetMapping("/tmp")
    @ApiOperation(value = "로그인")
    public Object test2(
            @RequestParam(value = "code") String code,
            @RequestParam(value = "state") String state
    ) throws Exception {
        System.out.println("##########socail login");
    	String clientId = "yW3gT9TqzIgQqklEfEBF";//애플리케이션 클라이언트 아이디값";
        String naverClientSecret = "whK5eQJhow";

        String apiURL;
        apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
        apiURL += "client_id=" + clientId;
		apiURL += "&client_secret=" + naverClientSecret;
        apiURL += "&code=" + code;
        apiURL += "&state=" + state;
        String access_token = "";
        String refresh_token = "";
        
        try {
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            System.out.println(con);
            int responseCode = con.getResponseCode();
            BufferedReader br;

            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer res = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                res.append(inputLine);
            }
            br.close();
            if(responseCode==200) { // 성공적으로 토큰을 가져온다면
//            	System.out.println(res);
                int id;
                String nickName, email, tmp;
                JsonParser parser = new JsonParser();
                JsonElement accessElement = parser.parse(res.toString());
                access_token = accessElement.getAsJsonObject().get("access_token").getAsString();

                tmp = naver.getUserInfo(access_token);
                JsonElement userInfoElement = parser.parse(tmp);
                System.out.println("****************");
                System.out.println(userInfoElement);
                
                id = userInfoElement.getAsJsonObject().get("response").getAsJsonObject().get("id").getAsInt();
                nickName = userInfoElement.getAsJsonObject().get("response").getAsJsonObject().get("nickname").getAsString();
                email = userInfoElement.getAsJsonObject().get("response").getAsJsonObject().get("email").getAsString();

                access_token = naver.createJWTToken(id, nickName, email);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        JSONObject dummyUser = new JSONObject();

        dummyUser.put("uid","test_uid");
        dummyUser.put("email","test@test.com");
        dummyUser.put("nickname","test_nickname");
        
        System.out.println(dummyUser);
        final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        result.object = dummyUser.toMap();
        return new ResponseEntity<>(result, HttpStatus.OK);
//        return "redirect:http://localhost:8080/agreement?token=" + access_token;
    }

    @PostMapping("/account/login")
    @ApiOperation(value = "로그인")
    public Object login(@RequestParam(required = true) final String email,
                        @RequestParam(required = true) final String password){
    	System.out.println("&&&&&&&&&&&&&&&login");
        System.out.println(email+"=========="+password);
        JSONObject dummyUser = new JSONObject();

        dummyUser.put("uid","test_uid");
        dummyUser.put("email","test@test.com");
        dummyUser.put("nickname","test_nickname");
        
        System.out.println(dummyUser);
        final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        result.object = dummyUser.toMap();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/account/signup")
    @ApiOperation(value = "가입하기")
    public Object signup(@Valid @RequestBody SignupRequest request) {
        //이메일, 닉네임 중복처리 필수
        //회원가입단을 생성해 보세요.
        
    	System.out.println("Join.................");
    	System.out.println(request.getEmail()+" "+request.getNickname()+" "+request.getPassword());
        
    	JSONObject dummyUser = new JSONObject();

        dummyUser.put("uid","test_uid");
        dummyUser.put("email","test@test.com");
        dummyUser.put("nickname","test_nickname");
         
    	final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        result.object = dummyUser;
        System.out.println(result);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    

    
}
