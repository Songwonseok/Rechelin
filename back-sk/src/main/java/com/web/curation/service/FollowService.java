package com.web.curation.service;

import java.util.List;

import com.web.curation.model.DTO.Alarm;
import com.web.curation.model.DTO.User;

public interface FollowService {
	public boolean addFollow(long fan, long star);

	public boolean requestFollow(long fan, long star);

	public List<User> starList(long id);

	public List<User> fanList(long id);

	public List<Alarm> alarmList(long id);

	public boolean declineFollow(long fan, long star);

	public boolean alarmCheck(long id); 
	
	public boolean newAlarm(long id);
	public int status(long fan, long star);
	 
	 
}
