package com.web.curation.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Likecheck {
	@Id
	private long num;
	
	@NonNull
	@Column(name="review_num")
	private long reviewnum;
	
	@NonNull
	@Column(name="user_email")
	private String useremail;

	public long getNum() {
		return num;
	}

	public long getReviewnum() {
		return reviewnum;
	}

	public String getUser_email() {
		return useremail;
	}

}
