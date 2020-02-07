package com.web.curation.model.DTO;

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
public class Storetags {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long num;
	
	@ManyToOne
	@JoinColumn(name= "hashtag_num")
	private Hashtag hashtag;
	
	@ManyToOne
	@JoinColumn(name= "review_num")
	private Review review;

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
