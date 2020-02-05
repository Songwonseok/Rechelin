package com.web.curation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dao.user.BookmarkDao;
import com.web.curation.dao.user.CommentDao;
import com.web.curation.dao.user.LikecheckDao;
import com.web.curation.dao.user.ReviewDao;
import com.web.curation.dao.user.UserDao;
import com.web.curation.model.user.Bookmark;
import com.web.curation.model.user.Comments;
import com.web.curation.model.user.Likecheck;
import com.web.curation.model.user.Review;
import com.web.curation.model.user.User;

@Service
public class ReviewServiceImpl implements ReviewService {
	@Autowired
	private ReviewDao dao;
	@Autowired
	private LikecheckDao likedao;
	@Autowired
	private CommentDao comdao;
	@Autowired
	private BookmarkDao bookdao;
	@Autowired
	private UserDao userdao;
	
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
			System.out.println(after.toString());
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

	@Override
	public void addComment(Comments com) {
		comdao.save(com);
	}

	@Override
	public void deleteComment(long num) {
		Comments com = comdao.findByNum(num);
		if(com!=null) comdao.delete(com);
	}

	@Override
	public List<Comments> getComment(long rnum) {
		// Review 객체를 찾아서
		Review review = dao.findByRnum(rnum);
		List<Comments> list = comdao.findAllByReview(review);
		// 해당되는 모든 댓글들을 불러온다
		return list;
	}

	@Override
	public List<Bookmark> getBookmark(String email) {
		// email을 통해 User를 찾는다
		User user = userdao.findByEmail(email);
		List<Bookmark> list = bookdao.findAllByUser(user);
		
		return list;
		
	}

	@Override
	public boolean clickBookmark(Bookmark book) {
		Bookmark tmp = bookdao.findByUserAndReview(book.getUser(), book.getReview());
		if(tmp == null) {
			// 없으니까 새로 추가
			bookdao.save(book);
			return true;
		}else {
			// 있는데 다시 눌렀으니까 취소
			bookdao.delete(tmp);
			return false;
		}
	}

	@Override
	public List<Review> getcurrentFeed(String email) {
		// email을 통해서 star 리스트를 가져온다.
		User user = userdao.findByEmail(email);
		System.out.println("SERVICE "+email);
		System.out.println(user.toString());
		List<Review> list = dao.feedList(user);
		for (Review review : list) {
			System.out.println(review.toString());
		}
		// start리스트의 게시물을 최근 순으로 가져온다
		return list;
	}

}
