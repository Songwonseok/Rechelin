package com.web.curation.model;

import java.util.List;

import com.web.curation.model.DTO.Hashtag;
import com.web.curation.model.DTO.Store;

public class TagsResponse {
	private Store store;
	private List<Hashtag> tags;
	
	public TagsResponse() {}
	
	public TagsResponse(Store store, List<Hashtag> tags){
		this.store = store;
		this.tags = tags;
	}

	public Store getStore() {
		return store;
	}

	public List<Hashtag> getTags() {
		return tags;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public void setTags(List<Hashtag> tags) {
		this.tags = tags;
	}
	
	
}
