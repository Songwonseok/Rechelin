package com.web.curation.model.user;

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
public class Review {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long review_num;
	
	@ManyToOne
	@JoinColumn(name= "store_num")
	private Store store;
	
	@ManyToOne
	@JoinColumn(name= "user_email")
	private User user;
	
	private String str;
	private String weak;
	private String picture;
	private String title;
	private String hashtag;
	
	private int views;
	
	@Column(name = "wdate", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private String wdate;
	
	@Column(name = "score_total")
	private int scoretotal;
	
	@Column(name = "score_taste")
	private int scoretaste;
	
	@Column(name = "score_price")
	private int scoreprice;
	
	@Column(name = "score_kindness")
	private int scorekindness;
	
	public long getReview_num() {
		return review_num;
	}
	public Store getStore() {
		return store;
	}
	public User getUser() {
		return user;
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
	public int getViews() {
		return views;
	}
	public String getWdate() {
		return wdate;
	}
	public int getScoretotal() {
		return scoretotal;
	}
	public int getScoretaste() {
		return scoretaste;
	}
	public int getScoreprice() {
		return scoreprice;
	}
	public int getScorekindness() {
		return scorekindness;
	}
	
	
	
}
