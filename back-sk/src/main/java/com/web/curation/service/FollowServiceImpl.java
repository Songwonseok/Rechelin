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
		User f = userDao.findByEmail(fan);
		User s = userDao.findByEmail(star);
		Alarm alarm = alarmDao.findByFanAndStar(f, s);
		if (followDao.findByFanAndStar(f, s) == null) {
			followDao.save(new Follow(f, s));
			if(alarm != null)
				alarmDao.delete(alarm);
			return true;
		} else {
			return false;
		}
	}

	public boolean declineFollow(String fan, String star) {
		User f = userDao.findByEmail(fan);
		User s = userDao.findByEmail(star);
		Alarm alarm = alarmDao.findByFanAndStar(f, s);
		if (alarm != null) {
			alarmDao.delete(alarm);
			return true;
		} else {
			return false;
		}
	}

	public boolean requestFollow(String fan, String star) {
		User f = userDao.findByEmail(fan);
		User s = userDao.findByEmail(star);
		if (alarmDao.findByFanAndStar(f, s) == null) {
			alarmDao.save(new Alarm(f, s));
			return true;
		} else {
			return false;
		}
	}

	public List<User> starList(String email) {
		User user =userDao.findByEmail(email);
		List<Follow> flist = followDao.findAllByFan(user);
		List<User> ulist = new ArrayList<>();
		
		for(Follow f : flist) {
			ulist.add(f.getStar());
		}
		return ulist;
	}

	public List<User> fanList(String email) {
		User user =userDao.findByEmail(email);
		List<Follow> flist = followDao.findAllByStar(user);
		List<User> ulist = new ArrayList<>();
		
		for(Follow f : flist) {
			ulist.add(f.getFan());
		}
		System.out.println(ulist.size());
		return ulist;
	}

	public List<Alarm> alarmList(String email) {
		User user =userDao.findByEmail(email);
		List<Alarm> alist = alarmDao.findAllByStar(user);
		return alist;
	}

	@Override
	public boolean alarmCheck(String email) {
		User user = userDao.findByEmail(email);
		List<Alarm> list = alarmDao.findAllByStar(user);
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
		User user = userDao.findByEmail(email);
		List<Alarm> list = alarmDao.findAllByStar(user);
		
		for (Alarm a : list) {
			if (a.getChecking() == 0) {
				return true;
			}
		}
		return false;
	}

}
