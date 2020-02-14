package com.web.curation.service;

import java.util.List;

import com.web.curation.model.DTO.Alarm;
import com.web.curation.model.DTO.User;

public interface FollowService {
	public boolean addFollow(String fan, String star);

	public boolean requestFollow(String fan, String star);

	public List<User> starList(long fnum);

	public List<User> fanList(long snum);

	public List<Alarm> alarmList(String email);

	public boolean declineFollow(String fan, String star);

	public boolean alarmCheck(String email); 
	
	public boolean newAlarm(String email);
	public int followStatus(long num);
	 
	 
}
