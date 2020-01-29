package com.web.curation.dao.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.model.user.Bookmark;

public interface BookmarkDao extends JpaRepository<Bookmark, Long> {
	List<Bookmark> findAllByUseremail(String useremail);
}
