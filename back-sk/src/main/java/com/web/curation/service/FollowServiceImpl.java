package com.web.curation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dao.user.AlarmDao;
import com.web.curation.dao.user.FollowDao;
import com.web.curation.dao.user.UserDao;
import com.web.curation.model.user.Alarm;
import com.web.curation.model.user.Follow;
import com.web.curation.model.user.User;

@Service
public class FollowServiceImpl implements FollowService {

	@Autowired
	FollowDao followDao;
	
	@Autowired
	UserDao userDao;

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

	public List<User> starList(String email) {
		List<Follow> flist = followDao.findAllByFan(email);
		List<User> ulist = new ArrayList<>();
		System.out.println(flist.size());
		for(Follow f : flist) {
			User u = userDao.findByEmail(f.getStar());
			System.out.println(f.getStar());
			ulist.add(u);
		}
		System.out.println(ulist.size());
		return ulist;
	}

	public List<User> fanList(String email) {
		List<Follow> flist = followDao.findAllByStar(email);
		System.out.println(flist.size());
		List<User> ulist = new ArrayList<>();
		
		for(Follow f : flist) {
			User u = userDao.findByEmail(f.getFan());
			System.out.println(f.getStar());
			ulist.add(u);
		}
		System.out.println(ulist.size());
		return ulist;
	}

	public List<User> alarmList(String email) {
		List<Alarm> alist = alarmDao.findAllByStar(email);
		List<User> ulist = new ArrayList<>();
		for(Alarm a : alist) {
			User u = userDao.findByEmail(a.getFan());
			ulist.add(u);
		}
		return ulist;
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
