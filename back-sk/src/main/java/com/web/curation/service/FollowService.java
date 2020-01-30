package com.web.curation.service;

import java.util.List;

import com.web.curation.model.user.Alarm;
import com.web.curation.model.user.Follow;

public interface FollowService {
	public boolean addFollow(String fan, String star);

	public boolean requestFollow(String fan, String star);

	public List<Follow> starList(String email);

	public List<Follow> fanList(String email);

	public List<Alarm> alarmList(String email);

	public boolean declineFollow(String fan, String star);

	public boolean alarmCheck(String email); 
	
	public boolean newAlarm(String email);
	 
	 
}
