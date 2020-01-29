package com.web.curation.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Subreview {
	@Id
	private long num;
	
	@NonNull
	@Column(name="review_num")
	private long reviewnum;
	
	@NonNull
	@Column(name="user_email")
	private String useremail;
	
//	@Temporal(TemporalType.TIMESTAMP)
	private String date;
	
	private String content;

	public long getNum() {
		return num;
	}

	public long getReviewnum() {
		return reviewnum;
	}

	public String getUseremail() {
		return useremail;
	}

	public String getDate() {
		return date;
	}

	public String getContent() {
		return content;
	}
}
