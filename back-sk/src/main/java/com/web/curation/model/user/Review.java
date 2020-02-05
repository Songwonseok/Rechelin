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
	@Column(name="review_num")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long rnum;
	
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
	
	@Column(name="score_total")
	private int total;
	@Column(name="score_taste")
	private int taste;
	@Column(name="score_price")
	private int price;
	@Column(name="score_kindness")
	private int kindness;
	
//	public Review(long snum, String email, ) {
//		
//	}
	public long getRnum() {
		return rnum;

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
	public int getTotal() {
		return total;
	}
	public int getTaste() {
		return taste;
	}
	public int getPrice() {
		return price;
	}
	public int getKindness() {
		return kindness;
	}
	public void setViews(int views) {
		this.views = views;
	}
	@Override
	public String toString() {
		return "Review [rnum=" + rnum + ", store=" + store + ", user=" + user + ", str=" + str + ", weak=" + weak
				+ ", picture=" + picture + ", title=" + title + ", hashtag=" + hashtag + ", views=" + views + ", wdate="
				+ wdate + ", total=" + total + ", taste=" + taste + ", price=" + price + ", kindness=" + kindness + "]";

	}
	
	
	
}
