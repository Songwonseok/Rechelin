package com.web.curation.model.user;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Store {
	@Id
	private long num;

	private String name;
	private String address;
	private String phone;

	public void update(Store s) {
		this.name = s.getName();
		this.address = s.getAddress();
		this.phone = s.getPhone();
	}
	
	public long getNum() {
		return num;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getPhone() {
		return phone;
	}

}
