package com.web.curation.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Storelike {
	@Id
	private long num;
	
	@NonNull
	@Column(name="store_num")
	private long storenum;
	
	@NonNull
	@Column(name="user_email")
	private String useremail;

	public long getNum() {
		return num;
	}

	public long getStorenum() {
		return storenum;
	}

	public String getUseremail() {
		return useremail;
	}

}
