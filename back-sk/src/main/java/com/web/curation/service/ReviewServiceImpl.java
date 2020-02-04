package com.web.curation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dao.user.LikecheckDao;
import com.web.curation.dao.user.ReviewDao;
import com.web.curation.model.user.Likecheck;
import com.web.curation.model.user.Review;

@Service
public class ReviewServiceImpl implements ReviewService {
	@Autowired
	private ReviewDao dao;
	
	@Autowired
	private LikecheckDao likedao;
	
	
	@Override
	public void register(Review review) {
		review.setViews(0);
		
		
		dao.save(review);
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
		System.out.println(tmp);
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
		
		Likecheck tmp = likedao.findByReviewAndUser(check.getReview(), check.getUser());
		if(tmp!=null) {
			// 똑같은 값이 들어오면 - 버튼 취소
			if(check.getStatus() == tmp.getStatus()) {
				likedao.delete(tmp);
				return 0;
			}else {				
				// 이미 있다는거니까 - 수정
				tmp.setStatus(check.getStatus());
				likedao.save(tmp);
				return 1;
			}
		}else {
			likedao.save(check);
			return 2;
		}
				
	}

}
