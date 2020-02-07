package com.web.curation.model.DTO;

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

	public Store() {}
	
	public Store(String sname, String address, String img, String lat, String lng) {
		this.sname = sname;
		this.address = address;
		this.img = img;
		this.lat = lat;
		this.lng = lng;
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
