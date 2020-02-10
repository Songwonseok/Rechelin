package com.web.curation.model;

import java.util.List;

import com.web.curation.model.DTO.Hashtag;
import com.web.curation.model.DTO.Store;

public class Tagsearch {
	private Store store;
	private List<Hashtag> tags;
	
	public Tagsearch(Store store, List<Hashtag> tags){
		this.store = store;
		this.tags = tags;
	}
}
