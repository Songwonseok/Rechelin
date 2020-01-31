package com.web.curation.model.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Store {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long num;

	private String sname;
	private String address;
	private String phone;

	public void update(Store s) {
		this.sname = s.getSname();
		this.address = s.getAddress();
		this.phone = s.getPhone();
	}
	
	public long getNum() {
		return num;
	}

	public String getSname() {
		return sname;
	}

	public String getAddress() {
		return address;
	}

	public String getPhone() {
		return phone;
	}

}
