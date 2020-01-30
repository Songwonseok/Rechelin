package com.web.curation.service;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.web.curation.dao.user.AlarmDao;
import com.web.curation.dao.user.FollowDao;
import com.web.curation.model.BasicResponse;
import com.web.curation.model.user.Alarm;
import com.web.curation.model.user.Follow;

@Service
public class FollowServiceImpl implements FollowService {

	@Autowired
	FollowDao followDao;

	@Autowired
	AlarmDao alarmDao;

	public boolean addFollow(String fan, String star) {
		Alarm alarm = alarmDao.findByFanAndStar(fan, star);
		if (followDao.findByFanAndStar(fan, star) == null && alarm != null) {
			Follow follow = new Follow(fan, star);
			followDao.save(follow);
			alarmDao.delete(alarm);
			return true;
		} else {
			return false;
		}
	}

	public boolean declineFollow(String fan, String star) {
		Alarm alarm = alarmDao.findByFanAndStar(fan, star);
		if (alarm != null) {
			alarmDao.delete(alarm);
			return true;
		} else {
			return false;
		}
	}

	public boolean requestFollow(String fan, String star) {
		if (alarmDao.findByFanAndStar(fan, star) == null) {
			Alarm alarm = new Alarm(fan, star);
			alarmDao.save(alarm);
			return true;
		} else {
			return false;
		}
	}

	public List<Follow> starList(String email) {

		return followDao.findAllByFan(email);
	}

	public List<Follow> fanList(String email) {
		return followDao.findAllByStar(email);
	}

	public List<Alarm> alarmList(String email) {
		return alarmDao.findAllByStar(email);
	}

	@Override
	public boolean alarmCheck(String email) {
		List<Alarm> list = alarmDao.findAllByStar(email);
		if (list != null) {
			for (Alarm a : list) {
				a.updateCheck();
				alarmDao.save(a);
				return true;
			}
		}
		return false;
	}

	public boolean newAlarm(String email) {
		List<Alarm> list = alarmDao.findAllByStar(email);
		for (Alarm a : list) {
			if (a.getChecking() == 0) {
				return true;
			}
		}
		return false;
	}

}
