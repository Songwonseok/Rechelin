// 디비 셋팅 주석 
 package com.web.curation.model.user;

 import lombok.AllArgsConstructor;
 import lombok.Data;
 import lombok.NoArgsConstructor;

 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.GeneratedValue;
 import javax.persistence.GenerationType;
 import javax.persistence.Id;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.UniqueElements;

import com.fasterxml.jackson.annotation.JsonIgnore;

 import java.time.LocalDateTime;

 @Entity
 @Data
 @NoArgsConstructor
 @AllArgsConstructor
 public class User {
     @Id
     private String email;
     @NotNull
     private String pw;
     @NotNull
     @UniqueElements
     private String nickname;
     @NotNull
     private String phone;
     private String profile;
     
     
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

 }
