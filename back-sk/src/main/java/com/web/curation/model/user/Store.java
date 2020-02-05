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
	private String img;
	private String lat;
	private String lng;
	
	public long getNum() {
		return num;
	}

	public String getSname() {
		return sname;
	}

	public String getAddress() {
		return address;
	}

	public String getImg() {
		return img;
	}

	public String getLat() {
		return lat;
	}

	public String getLng() {
		return lng;
	}

}
