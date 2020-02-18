package com.web.curation.model.DTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Storelike {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long num;
	
	@ManyToOne
	@JoinColumn(name= "store_num")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Store store;
	
	@ManyToOne
	@JoinColumn(name= "user_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;

	public Storelike() {}
	
	public Storelike(Store store, User user) {
		this.store = store;
		this.user = user;
	}

	public long getNum() {
		return num;
	}

	public Store getStore() {
		return store;
	}

	public User getUser() {
		return user;
	}

	public void setNum(long num) {
		this.num = num;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
