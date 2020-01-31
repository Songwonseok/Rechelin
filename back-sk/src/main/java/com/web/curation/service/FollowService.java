package com.web.curation.service;

import java.util.List;

import com.web.curation.model.user.Alarm;
import com.web.curation.model.user.User;

public interface FollowService {
	public boolean addFollow(String fan, String star);

	public boolean requestFollow(String fan, String star);

	public List<User> starList(String email);

	public List<User> fanList(String email);

	public List<User> alarmList(String email);

	public boolean declineFollow(String fan, String star);

	public boolean alarmCheck(String email); 
	
	public boolean newAlarm(String email);
	 
	 
}
