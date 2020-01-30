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
public class Bookmark {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long num;
	
	@NonNull
	@Column(name="user_email")
	private String useremail;
	
	@NonNull
	@Column(name="store_num")
	private String storenum;

	public long getNum() {
		return num;
	}

	public String getUseremail() {
		return useremail;
	}

	public String getStorenum() {
		return storenum;
	}

	
}
