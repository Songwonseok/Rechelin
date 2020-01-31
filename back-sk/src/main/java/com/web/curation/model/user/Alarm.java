package com.web.curation.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.NonNull;

@Entity
public class Alarm {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long num;
	
	@NonNull
	@Column(name="fan")
	private String fan;
	
	@NonNull
	@Column(name="star")
	private String star;
	
	@Column(name="checking")
	private int checking;
	
	public Alarm() {}
	
	public Alarm(String fan, String star) {
		this.fan = fan;
		this.star = star;
	}
	
	public long getNum() {
		return num;
	}

	public String getFan() {
		return fan;
	}

	public String getStar() {
		return star;
	}
	
	public int getChecking() {
		return checking;
	}
	
	public void updateCheck() {
		this.checking = 1;
	}
	
}
