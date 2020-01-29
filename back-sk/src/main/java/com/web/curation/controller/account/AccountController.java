package com.web.curation.controller.account;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.ws.spi.http.HttpContext;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.web.curation.dao.user.UserDao;
import com.web.curation.model.BasicResponse;
import com.web.curation.model.FileInfo;
import com.web.curation.model.user.User;
import com.web.curation.service.AcountService;

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
		
	@Autowired
	AcountService service;

    @Autowired
    UserDao userDao;

    @PostMapping("/account/login")
    @ApiOperation(value = "로그인")
    public Object login(@RequestParam(required = true) final String email,
                        @RequestParam(required = true) final String password){

    	System.out.println("&&&&&&&&&&&&&&&login");
        System.out.println(email+"=========="+password);
        
        final BasicResponse result = new BasicResponse();
        JSONObject dummyUser = new JSONObject();

        User tmp = userDao.findByEmail(email);
        // 1. DB 에 값이 존재하는지 판단
        if(tmp == null) {
        	System.out.println("X");
        	result.status = false;
            result.data = "email이 존재하지않습니다";
            
        }else {
        	// 2. email과 pw일치하는지 판단
        	if(password.equals(tmp.getPw())) {
        		result.status = true;
        		result.data = "success";        		
        	}else {
        		result.status = false;
        		result.data = "비밀번호가 일치하지 않습니다";
        	}
        	
        	dummyUser.put("email",email);
        	dummyUser.put("nickname",tmp.getNickname());
        	result.object = dummyUser.toMap();
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    
    @PostMapping("/account/signup")
    @ApiOperation(value = "가입하기")
    public Object signup(@RequestBody User request) {
    	System.out.println("signUp");
    	return service.signup(request);
    }
    
    @DeleteMapping("/account/delete")
    @ApiOperation(value = "삭제하기")
    public Object delete(@RequestParam(required = true) final String email) {
    	return service.delete(email);
    }
    
    @PutMapping("/account/update")
    @ApiOperation(value = "수정하기")
    public Object update(@RequestBody User request) {
    	System.out.println("update!!!");
    	System.out.println(request.getEmail());
    	System.out.println(request.getPw());
    	System.out.println(request.getNickname());
    	System.out.println(request.getPhone());
    	System.out.println(request.getProfile());
    	
    	return service.update(request);
    }
    
    @GetMapping("/account/list")
    @ApiOperation(value = "유저보기")
    public Object selectAll() {
        return service.selectAll();
    }
    
    @PostMapping("/account/selectEmail")
    @ApiOperation(value = "이메일로 유저찾기")
    public Object selectEmail(@RequestParam(required = true) final String email) {
    	System.out.println(email);
        return service.selectEmail(email);
    }
    
    @PostMapping("/account/selectName")
    @ApiOperation(value = "닉네임으로 유저찾기")
    public Object selectName(@RequestParam(required = true) final String nickname) {
    	System.out.println(nickname);
    	return service.selectName(nickname);
    }
    
    @PostMapping(value = "/account/upload", headers = ("content-type=multipart/*"))
	public Object upload(@RequestParam("file") MultipartFile inputFile) {
		FileInfo fileInfo = new FileInfo();
		HttpHeaders headers = new HttpHeaders();
		if (!inputFile.isEmpty()) {
			try {
				String oriFileNm = inputFile.getOriginalFilename();
				System.out.println("11213");
				File destinationFile = new File(fileInfo.getDir() + File.separator + oriFileNm);
				System.out.println(destinationFile);
				inputFile.transferTo(destinationFile);
				fileInfo.setFileName(destinationFile.getPath());
				fileInfo.setFileSize(inputFile.getSize());
				
				return service.uploadImage(fileInfo, true); 
			} catch (Exception e) {
				return service.uploadImage(fileInfo, false); 
			}
		} else {
			
			/*
			 * 이미지 경로 : default 이미지로 변경하고 upload
			 * 
			 */
			return service.uploadImage(fileInfo, true); 
		}
	}
    
	@GetMapping(value = "/account/getProfile", produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[] getFile(@RequestParam(required = true) final String email) throws IOException {
		
		InputStream in = service.getProfile(email);
		return IOUtils.toByteArray(in);
	}
    
    
}
