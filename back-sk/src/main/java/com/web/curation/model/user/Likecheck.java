package com.web.curation.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Likecheck {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long num;
	
	@NonNull
	@Column(name="review_num")
	private long rnum;
	
	@NonNull
	@Column(name="user_email")
	private String email;
	
	private int status;
	
	public Likecheck() {
	}
	
	public Likecheck(long rnum, String email, int status) {
		this.rnum = rnum;
		this.email = email;
		this.status = status;
	}

	public long getRnum() {
		return rnum;
	}

	public String getEmail() {
		return email;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
		
	}
	

}
