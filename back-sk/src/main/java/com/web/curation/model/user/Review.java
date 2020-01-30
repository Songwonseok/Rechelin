package com.web.curation.model.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Review {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long review_num;
	
	private long store_num;
	private String user_email;
	private String str;
	private String weak;
	private String picture;
	private String title;
	private String hashtag;
	
	private int likes;
	private int unlikes;
	private int views;
	
//	@Temporal(TemporalType.TIMESTAMP)
	private String wdate;
	
	private int score_tatal;
	private int score_taste;
	private int score_price;
	private int score_kindness;
	
	public long getReview_num() {
		return review_num;
	}
	public long getStore_num() {
		return store_num;
	}
	public String getUser_email() {
		return user_email;
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
	public int getLikes() {
		return likes;
	}
	public int getUnlikes() {
		return unlikes;
	}
	public int getViews() {
		return views;
	}
	public String getWdate() {
		return wdate;
	}
	public int getScore_tatal() {
		return score_tatal;
	}
	public int getScore_taste() {
		return score_taste;
	}
	public int getScore_price() {
		return score_price;
	}
	public int getScore_kindness() {
		return score_kindness;
	}
	
	
}
