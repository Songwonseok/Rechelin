package com.web.curation.model.DTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.NonNull;

@Entity
public class Search {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long num;
	
	@NonNull
	private String email;
	
	@NonNull
	private String searchname;
	
	@NonNull
	private String category;
	
	@Column(name = "sdate", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private String sdate;
	
	public Search() {}

	public Search(@NonNull String email, @NonNull String searchname, @NonNull String category) {
		this.email = email;
		this.searchname = searchname;
		this.category = category;
	}

	public long getNum() {
		return num;
	}

	public String getEmail() {
		return email;
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
