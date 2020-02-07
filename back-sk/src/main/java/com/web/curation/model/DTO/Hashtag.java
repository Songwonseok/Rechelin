 package com.web.curation.model.DTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Hashtag {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long num;
	
	@NonNull
	private String keyword;
	
	@NonNull
	private String category;

	public long getNum() {
		return num;
	}
	
	public String getKeyword() {
		return keyword;
	}

	public String getCategory() {
		return category;
	}

}
