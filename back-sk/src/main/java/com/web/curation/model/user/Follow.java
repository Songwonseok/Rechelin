package com.web.curation.model.user;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Follow {
	@Id
	private long num;
	
	@NonNull
	private String fan;
	
	@NonNull
	private String star;

	public long getNum() {
		return num;
	}

	public String getFan() {
		return fan;
	}

	public String getStar() {
		return star;
	}

	
}
