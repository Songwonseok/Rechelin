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
public class Storetags {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long num;
	
	@NonNull
	@Column(name="hashtag_num")
	private long hashtagnum;
	
	@NonNull
	@Column(name="review_num")
	private long reviewnum;

	public long getNum() {
		return num;
	}

	public long getHashtagnum() {
		return hashtagnum;
	}

	public long getReviewnum() {
		return reviewnum;
	}

	
}
