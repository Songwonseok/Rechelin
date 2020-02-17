package com.web.curation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.model.DAO.AlarmDao;
import com.web.curation.model.DAO.FollowDao;
import com.web.curation.model.DAO.UserDao;
import com.web.curation.model.DTO.Alarm;
import com.web.curation.model.DTO.Follow;
import com.web.curation.model.DTO.User;

@Service
public class FollowServiceImpl implements FollowService {

	@Autowired
	FollowDao followDao;
	
	@Autowired
	UserDao userDao;

	@Autowired
	AlarmDao alarmDao;

	public boolean addFollow(long fan, long star) {
		User f = userDao.findById(fan);
		User s = userDao.findById(star);
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

	public boolean declineFollow(long fan, long star) {
		User f = userDao.findById(fan);
		User s = userDao.findById(star);
		Alarm alarm = alarmDao.findByFanAndStar(f, s);
		if (alarm != null) {
			alarmDao.delete(alarm);
			return true;
		} else {
			return false;
		}
	}

	public boolean requestFollow(long fan, long star) {
		User f = userDao.findById(fan);
		f.getEmail();
		User s = userDao.findById(star);
		s.getEmail();
		
		if (alarmDao.findByFanAndStar(f, s) == null) {
			alarmDao.save(new Alarm(f, s));
			return true;
		} else {
			return false;
		}
	}

	public List<User> starList(long id) {
		User user =userDao.findById(id);
		List<Follow> flist = followDao.findAllByFan(user);
		System.out.println("*****"+flist.size());
		List<User> ulist = new ArrayList<>();
		
		for(Follow f : flist) {
			ulist.add(f.getStar());
		}
		return ulist;
	}

	public List<User> fanList(long id) {
		User user =userDao.findById(id);
		System.out.println("user"+ user.toString());
		List<Follow> tmp = followDao.findAll();
		System.out.println("전체 리스트 "+ tmp.size());
		List<Follow> flist = followDao.findAllByStar(user);
		System.out.println(flist.size());

		
		List<User> ulist = new ArrayList<>();
		 
		for(Follow f : flist) {
			ulist.add(f.getFan());
		}
		return ulist;
	}

	public List<Alarm> alarmList(long id) {
		User user =userDao.findById(id);
		List<Alarm> alist = alarmDao.findAllByStar(user);
		return alist;
	}

	@Override
	public boolean alarmCheck(long id) {
		User user = userDao.findById(id);
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

	public boolean newAlarm(long id) {
		User user = userDao.findById(id);
		List<Alarm> list = alarmDao.findAllByStar(user);
		
		for (Alarm a : list) {
			if (a.getChecking() == 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int status(long fan, long star) {
		
		// 1) fan 이 star를 팔로워 하고 있는지 체크
		User ff = userDao.findById(fan);
		User ss = userDao.findById(star);
		Follow follo = followDao.findByFanAndStar(ff,ss);
		if(follo==null) {
			// 팔로우 안되어있는 상태
			
			// 2) 알람에 있는지 체크
			Alarm alarm = alarmDao.findByFanAndStar(ff, ss);
			if(alarm==null) {
				// 팔로우 안되어있는 상태
				return 0;
			}else {
				// 요청중인 상태
				return 1;
			}
			
		}else {
			// 이미 팔로우한 유저
			return 2;
		}
		
	}

	@Override
	public boolean unFollow(long fan, long star) {
		User ff = userDao.findById(fan);
		User ss = userDao.findById(star);
		Follow f = followDao.findByFanAndStar(ff, ss);
		if(f==null) return false;
		else {
			followDao.delete(f);
			return true;
		}
	}


}
