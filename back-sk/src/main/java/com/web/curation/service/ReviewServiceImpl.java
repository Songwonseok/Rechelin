package com.web.curation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dao.user.LikecheckDao;
import com.web.curation.dao.user.ReviewDao;
import com.web.curation.dao.user.UserDao;
import com.web.curation.model.user.Likecheck;
import com.web.curation.model.user.Review;

@Service
public class ReviewServiceImpl implements ReviewService {
	@Autowired
	private ReviewDao dao;
	
	@Autowired
	private LikecheckDao checkdao;
	
	@Autowired
	private UserDao userdao;
	
	@Override
	public boolean register(Review review) {
		long snum = review.getSnum();
		String email = review.getEmail();
		List<Review> list = dao.findAllBySnumAndEmail(snum, email);
		if(list.size() == 0) {
			review.setViews(0);
			dao.save(review);
			return true;
		}else {
			// 이미 등록된 리뷰
			return false;
		}
	}

	@Override
	public boolean update(Review after) {
		long rnum = after.getRnum();
		Review befo = dao.findByRnum(rnum);
		if(befo!=null) {
			after.setViews(befo.getViews());
			dao.save(after);
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(long rnum) {
		Review tmp = dao.findByRnum(rnum);
		if(tmp!=null) {
			dao.delete(tmp);
			return true;
		}
		return false;
	}

	@Override
	public Review detail(long rnum) {
		Review tmp = dao.findByRnum(rnum);
		if(tmp!=null) {
			// 조회수 1증가
			int view = tmp.getViews();
			tmp.setViews(view+1);
			dao.save(tmp);
			return tmp;
		}
		return null;
	}

	@Override
	public int useful(Likecheck check) {
		long rnum = check.getRnum();
		String email = check.getEmail();
		
		// rnum이 존재하는지 체크
		if(dao.findByRnum(rnum)!=null) {
			// email이 존재하는지 체크
			if(userdao.findByEmail(email)!=null) {
				Likecheck befo = checkdao.findByRnumAndEmail(check.getRnum(), check.getEmail());
				
				if(befo!=null){
					befo.setStatus(check.getStatus());					
					checkdao.save(befo); //  업데이트
					return 3;
				}else{
					checkdao.save(check);// 생성
					return 2;
				}
			}
			return 1; // email 존재 X
		}else {
			return 0; // rnum 존재 X
		}
		
	}

}
