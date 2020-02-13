package com.web.curation.model.DTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="bookmark")
public class Bookmark {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long num;
	
	@ManyToOne
	@JoinColumn(name= "user_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	@ManyToOne
	@JoinColumn(name= "review_num")
	@OnDelete(action = OnDeleteAction.CASCADE)
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
