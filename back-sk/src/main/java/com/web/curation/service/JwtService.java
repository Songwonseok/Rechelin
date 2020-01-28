package com.web.curation.service;

import com.web.curation.model.user.User;

public interface JwtService {
	String makeJwt(User user);

	boolean checkJwt(String jwt) throws Exception;
}
