package com.web.curation.model.querydsl;

import java.util.List;

import com.web.curation.model.user.Storetags;

public interface CustomRepository {
	List<Storetags> storeTags(long num);
}
