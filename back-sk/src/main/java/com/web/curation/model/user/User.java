// 디비 셋팅 주석 
 package com.web.curation.model.user;

 import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

 @Entity
 @NoArgsConstructor
 @AllArgsConstructor
 public class User {
     @Id
     private String email;
     
     private String pw;
     private String nickname;
     private String phone;
     
     private String profile;
     
    public User() { }
     
	public User(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	public String getPw() {
		return pw;
	}
	public String getNickname() {
		return nickname;
	}

	public String getPhone() {
		return phone;
	}

	public String getProfile() {
		return profile;
	}
	
	public void updateUser(User a) {
		this.pw = a.getPw();
		this.nickname = a.getNickname();
		this.phone = a.getPhone();
		this.profile = a.getProfile();
	}
	

 }
