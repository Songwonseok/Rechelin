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
		try {
			Alarm alarm = alarmDao.findByFanAndStar(fan, star);
			alarmDao.delete(alarm);
			Follow follow = new Follow(fan, star);
			followDao.save(follow);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean declineFollow(String fan, String star) {
		final BasicResponse result = new BasicResponse();
		Alarm alarm = alarmDao.findByFanAndStar(fan, star);
		alarmDao.delete(alarm);

//		result.status = true;
//		result.data = "알람 삭제";
//		result.object = new JSONObject(alarm).toMap();
//		return new ResponseEntity<>(result, HttpStatus.OK);

		return false;
	}

	public boolean requestFollow(String fan, String star) {

		Alarm alarm = new Alarm(fan, star);

//		return alarmDao.save(alarm);
		return false;
	}

	public List<Follow> starList(String email) {
		List<Follow> list = followDao.findAllByFan(email);
		final BasicResponse result = new BasicResponse();
		if (list == null) {
			result.status = false;
			result.data = "리스트가 없습니다.";
		} else {
			result.status = true;
			result.data = "팔로잉 리스트";
			result.object = list;
		}
//		return new ResponseEntity<>(result, HttpStatus.OK);
		return null;
	}

	public List<Follow> fanList(String email) {
		List<Follow> list = followDao.findAllByStar(email);
		final BasicResponse result = new BasicResponse();
		if (list == null) {
			result.status = false;
			result.data = "리스트가 없습니다.";
		} else {
			result.status = true;
			result.data = "팔로워 리스트";
			result.object = list;
		}
//		return new ResponseEntity<>(result, HttpStatus.OK);
		return null;
	}

	public List<Alarm> alarmList(String email) {
		List<Alarm> list = alarmDao.findAllByStar(email);
		final BasicResponse result = new BasicResponse();
		if (list == null) {
			result.status = false;
			result.data = "리스트가 없습니다.";
		} else {
			result.status = true;
			result.data = "알람 리스트";
			result.object = list;
		}
//		return new ResponseEntity<>(result, HttpStatus.OK);
		return null;
	}

	@Override
	public boolean alarmCheck(String email) {
		List<Alarm> list = alarmDao.findAllByStar(email);
		final BasicResponse result = new BasicResponse();
		if (list == null) {
			result.status = false;
			result.data = "리스트가 없습니다.";
		} else {
			for (Alarm a : list) {
				a.updateCheck();
				alarmDao.save(a);
			}
			result.status = true;
			result.data = "확인 완료";
			result.object = list;
		}
//		return new ResponseEntity<>(result, HttpStatus.OK);
		return false;
	}

	public boolean newAlarm(String email) {
		List<Alarm> list = alarmDao.findAllByStar(email);
		boolean check = false;
		final BasicResponse result = new BasicResponse();
		if (list == null) {
			result.status = false;
			result.data = "알람이 없습니다.";
		} else {
			for (Alarm a : list) {
				if (a.getChecking() == 0) {
					check = true;
					break;
				}
			}
		}
		if (check) {
			result.status = true;
			result.data = "새 알람이 있습니다.";
			result.object = list;
		} else {
			result.status = false;
			result.data = "새 알람이 없습니다.";
		}
//		return new ResponseEntity<>(result, HttpStatus.OK);
		return false;
	}

}
