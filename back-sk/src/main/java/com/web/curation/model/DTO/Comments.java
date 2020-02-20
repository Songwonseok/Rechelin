package com.web.curation.model.DTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Comments {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long num;
	
	
	@ManyToOne
	@JoinColumn(name= "review_num")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Review review;
	
	@ManyToOne
	@JoinColumn(name= "user_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	@Column(name = "wdate", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private String wdate;
	
	private String content;

	public void setUser(User user) {
		this.user = user;
	}

	public void setReview(Review review) {
		this.review = review;
	}

	public long getNum() {
		return num;
	}

	public Review getReview() {
		return review;
	}

	public User getUser() {
		return user;
	}

	public String getWdate() {
		return wdate;
	}

	public String getContent() {
		return content;
	}
}
