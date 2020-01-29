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

	public User() {
		super();
	}
	public User(String email, String nickname) {
		this.email = email;
		this.nickname = nickname;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getPw() {
		return pw;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
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
	@Override
	public String toString() {
		return "User [email=" + email + ", pw=" + pw + ", nickname=" + nickname + ", phone=" + phone + ", profile="
				+ profile + "]";
	}



 }
