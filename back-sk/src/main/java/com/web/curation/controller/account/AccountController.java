package com.web.curation.controller.account;

import javax.validation.Valid;

 import com.web.curation.dao.user.UserDao;
import com.web.curation.model.BasicResponse;
import com.web.curation.model.user.SignupRequest;
 import com.web.curation.model.user.User;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiOperation;


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