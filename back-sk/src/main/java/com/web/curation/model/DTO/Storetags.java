package com.web.curation.model.DTO;

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
public class Storetags {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long num;
	
	@ManyToOne
	@JoinColumn(name= "hashtag_num")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Hashtag hashtag;
	
	@ManyToOne
	@JoinColumn(name= "review_num")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Review review;
    
	public Storetags() {}
	

	public void setHashtag(Hashtag hashtag) {
		this.hashtag = hashtag;
	}



	public void setReview(Review review) {
		this.review = review;
	}



	public long getNum() {
		return num;
	}

	public Hashtag getHashtag() {
		return hashtag;
	}

	public Review getReview() {
		return review;
	}

}
