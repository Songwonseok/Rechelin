package com.web.curation.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.web.curation.dao.user.FollowDao;
import com.web.curation.model.BasicResponse;
import com.web.curation.model.user.Follow;

@Service
public class FollowServiceImpl implements FollowService {

	@Autowired
	FollowDao followDao;
	
	public Object create(Follow request) {
		
		final BasicResponse result = new BasicResponse();

		result.status = true;
		result.data = "식당 등록 성공";
		result.object = new JSONObject(request).toMap();

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
