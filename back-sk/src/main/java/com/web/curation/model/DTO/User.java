// 디비 셋팅 주석 
 package com.web.curation.model.DTO;

 import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

 @Entity
 @NoArgsConstructor
 @AllArgsConstructor
 public class User {
     @Id
 	 @GeneratedValue(strategy=GenerationType.IDENTITY)
     private long id;
     
     private String email;
     
     private String pw;
     private String nickname;
     private String phone;
     
     private String profile;
     
     public long getId() {
		return id;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@ManyToMany(fetch = FetchType.LAZY)
     @JoinTable(name = "user_roles",
             joinColumns = @JoinColumn(name = "user_id"),
             inverseJoinColumns = @JoinColumn(name = "role_id"))
     private Set<Role> role = new HashSet<>(); //순서 상관 없는 집합

	public void setRole(Set<Role> role) {
		this.role = role;
	}
	public User() {
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

	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getProfile() {
		return profile;
	}
	
	public void updateUser(User a) {
		this.pw = a.getPw();
		this.nickname = a.getNickname();
		this.phone = a.getPhone();
		this.profile = a.getProfile();
		this.role = a.getRole();
	}
	
	@Override
	public String toString() {
		return "User [email=" + email + ", pw=" + pw + ", nickname=" + nickname + ", phone=" + phone + ", profile="
				+ profile + "]";
	}
	public Set<Role> getRole() {
        return role;
    }


 }
