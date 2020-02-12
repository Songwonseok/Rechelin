package com.web.curation.model.DTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Table(name="alarm")
public class Alarm {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long num;
	
	@ManyToOne
	@JoinColumn(name= "fan")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User fan;
	
	@ManyToOne
	@JoinColumn(name= "star")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User star;
	
	@Column(name="checking")
	@ColumnDefault("0") //default 0
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
