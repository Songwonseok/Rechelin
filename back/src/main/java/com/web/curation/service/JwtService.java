package com.web.curation.service;

import com.web.curation.model.DTO.User;

public interface JwtService {
	String makeJwt(User user);

	int checkJwt(String jwt) throws Exception;
}
