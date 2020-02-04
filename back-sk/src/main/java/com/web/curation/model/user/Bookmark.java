package com.web.curation.model.user;

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
public class Bookmark {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long num;
	
	@ManyToOne
	@JoinColumn(name= "user_email")
	private User user;
	
	@ManyToOne
	@JoinColumn(name= "review_num")
	private Review review;

	public long getNum() {
		return num;
	}

	public User getUser() {
		return user;
	}

	public Review getReview() {
		return review;
	}


	
}
