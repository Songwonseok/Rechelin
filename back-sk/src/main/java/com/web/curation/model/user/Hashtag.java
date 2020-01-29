package com.web.curation.model.user;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Hashtag {
	@Id
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
