package com.web.curation.model.DTO;

import javax.persistence.*;


@Entity
@Table(name="alarm")
public class Alarm {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long num;
	
	@ManyToOne
	@JoinColumn(name= "fan")
	private User fan;
	
	@ManyToOne
	@JoinColumn(name= "star")
	private User star;
	
	@Column(name="checking")
	private int checking;
	
	public Alarm() {}
	
	public Alarm(User fan, User star) {
		this.fan = fan;
		this.star = star;
	}
	
	public long getNum() {
		return num;
	}

	public User getFan() {
		return fan;
	}

	public User getStar() {
		return star;
	}
	
	public int getChecking() {
		return checking;
	}
	
	public void updateCheck() {
		this.checking = 1;
	}
	
}
