package com.web.curation.model.DTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.NonNull;

@Entity
public class Search {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long num;
	
	@ManyToOne
	@JoinColumn(name= "user_id")
	private User user;
	
	@NonNull
	private String searchname;
	
	@NonNull
	private String category;
	
	@Column(name = "sdate", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private String sdate;
	
	public Search() {}

	public Search(@NonNull User user, @NonNull String searchname, @NonNull String category) {
		this.user = user;
		this.searchname = searchname;
		this.category = category;
	}

	public long getNum() {
		return num;
	}

	public User getUser() {
		return user;
	}

	public String getSearchname() {
		return searchname;
	}

	public String getCategory() {
		return category;
	}

	public String getSdate() {
		return sdate;
	}
	
}
