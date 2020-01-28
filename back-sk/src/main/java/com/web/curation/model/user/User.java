// 디비 셋팅 주석 
 package com.web.curation.model.user;

 import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
<<<<<<< HEAD
=======
import lombok.Data;
>>>>>>> 186f4052a9825acc36d67a3b31add9bf6284b14d
import lombok.NoArgsConstructor;

 @Entity
 @NoArgsConstructor
 @AllArgsConstructor
 public class User {
     @Id
     private String email;
<<<<<<< HEAD
     private String pw;
     private String nickname;
     private String phone;
     
     private String profile;

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
=======
     
     private String pw;
     private String nickname;
     private String phone;
     private String profile;
     
     
	public User(String email) {
		super();
		this.email = email;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String email, String pw, String nickname, String phone, String profile) {
		super();
		this.email = email;
		this.pw = pw;
		this.nickname = nickname;
		this.phone = phone;
		this.profile = profile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
>>>>>>> 186f4052a9825acc36d67a3b31add9bf6284b14d

 }
