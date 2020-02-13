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
public class Follow {
	
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
	
	public Follow() {}
	public Follow(User fan, User star) {
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

	
}
