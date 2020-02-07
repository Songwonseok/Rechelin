package com.web.curation.model.DTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Likecheck {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long num;


	@ManyToOne
	@JoinColumn(name= "review_num")
	private Review review;
	
	@ManyToOne
	@JoinColumn(name= "user_id")
	private User user;
	
	private int status;
		
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	public Review getReview() {
		return review;
	}

	public User getUser() {
		return user;
	}
	


}
