package com.web.curation.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Review {

	@Id
	@Column(name="review_num")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long rnum;
	
	@Column(name="store_num")
	private long snum;
	@Column(name="user_email")
	private String email;
	private String str;
	private String weak;
	private String picture;
	private String title;
	private String hashtag;
	private int views;
	
	@Column(name = "wdate", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private String wdate;
	
	@Column(name="score_total")
	private int total;
	@Column(name="score_taste")
	private int taste;
	@Column(name="score_price")
	private int price;
	@Column(name="score_kindness")
	private int kindness;
	
	public Review() {
		
	}
	
	public Review(long snum, String email, String str, String weak, String picture,
			String title, String hashtag, int views, int total, int taste, int price,
			int kindness) {
		super();
		this.snum = snum;
		this.email = email;
		this.str = str;
		this.weak = weak;
		this.picture = picture;
		this.title = title;
		this.setHashtag(hashtag);
		this.views = views;
		this.total = total;
		this.taste = taste;
		this.price = price;
		this.kindness = kindness;
	}


	public void setViews(int views) {
		this.views = views;
	}


	public long getRnum() {
		return rnum;
	}
	public int getViews() {
		return views;
	}

	public String getEmail() {
		return email;
	}

	public long getSnum() {
		return snum;
	}


	public String getStr() {
		return str;
	}


	public String getWeak() {
		return weak;
	}


	public String getPicture() {
		return picture;
	}


	public String getTitle() {
		return title;
	}


	public String getHashtag() {
		return hashtag;
	}


	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}
	
	
	
}
