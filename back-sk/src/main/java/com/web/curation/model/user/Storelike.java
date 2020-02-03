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
import lombok.NonNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Storelike {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long num;
	
	@ManyToOne
	@JoinColumn(name= "store_num")
	private Store store;
	
	@ManyToOne
	@JoinColumn(name= "user_email")
	private User user;

	public long getNum() {
		return num;
	}

	public Store getStore() {
		return store;
	}

	public User getUser() {
		return user;
	}

}
