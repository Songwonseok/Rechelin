package com.web.curation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.model.DAO.BookmarkDao;
import com.web.curation.model.DAO.CommentDao;
import com.web.curation.model.DAO.HashtagDao;
import com.web.curation.model.DAO.LikecheckDao;
import com.web.curation.model.DAO.ReviewDao;
import com.web.curation.model.DAO.StoreDao;
import com.web.curation.model.DAO.StoreLikeDao;
import com.web.curation.model.DAO.StoreTagsDao;
import com.web.curation.model.DAO.UserDao;
import com.web.curation.model.DTO.Bookmark;
import com.web.curation.model.DTO.Comments;
import com.web.curation.model.DTO.Hashtag;
import com.web.curation.model.DTO.Likecheck;
import com.web.curation.model.DTO.Review;
import com.web.curation.model.DTO.Store;
import com.web.curation.model.DTO.Storetags;
import com.web.curation.model.DTO.User;
import com.web.curation.model.QueryDSL.CustomRepositoryImpl;

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
	@Autowired
	private StoreDao storedao;
	@Autowired
	private HashtagDao hashdao;
	@Autowired
	private StoreLikeDao SLikedao;
	@Autowired
	private StoreTagsDao STagsdao;
	
	@Autowired
	private CustomRepositoryImpl custom;
	
	@Override
	public void register(Review review) {
		dao.save(review);
		
		// review num 가져오기
		Review tmp = dao.findTopByStoreAndUserOrderByRnumDesc(review.getStore(), review.getUser());
		
		// 1) review.hashtag을 가져와서 hashtag에 일치하는 값 확인
		String hashtag = review.getHashtag();
		String[] tagList = hashtag.split(","); // 해시태그 분리
		
		for(int i=0; i<tagList.length; i++) {
			Hashtag tag = hashdao.findByKeyword(tagList[i]);
			
			System.out.println(tagList[i]); 
			System.out.println(" "+ tag.toString());
			if(tag ==null) continue; // 없는 태그가 나오면 DB에 추가?
			
			System.out.println("********************");
			System.out.println(tag.toString());
			Storetags st = new Storetags();
			st.setHashtag(tag);
			st.setReview(tmp);
			// hastag 객체와 review객체를 StoreTags에 저장
			STagsdao.save(st);
		}
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
	public List<Bookmark> getBookmark(long num) {
		// email을 통해 User를 찾는다
		User user = userdao.findById(num);
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
	public List<Review> getcurrentFeed(long num) {
		// email을 통해서 star 리스트를 가져온다.
		User user = userdao.findById(num);
		System.out.println(user.toString());
		List<Review> list = dao.feedList(user);
		for (Review review : list) {
			System.out.println(review.toString());
		}
		// start리스트의 게시물을 최근 순으로 가져온다
		return list;
	}

	@Override
	public List<Review> getReview(long snum) {
		// store의 num으로 통해서 리뷰들을 가져온다
		Store store = storedao.findByNum(snum);
		List<Review> list = dao.findAllByStore(store);
		return list;
	}

	

	@Override
	public List<Review> getMyReview(long num) {
		User user = userdao.findById(num);
		List<Review> list = dao.findAllByUser(user);
		return list;
	}

	@Override
	public List<Review> getCurReview() {
		List<Review> list = dao.findTop30ByOrderByRnumDesc();
		return list;
	}

	@Override
	public List<Review> getviewsTop() {
		List<Review> list = dao.findTop6ByOrderByViewsDesc();
		return list;
	}


	@Override
	public List<Review> getlikeTop() {
		List<Review> list = dao.likeBest();
		return list;
	}

	@Override
	public List<Review> getscoreTop() {
		List<Review> list = dao.findTop6ByOrderByTotalDesc();
		return list;
	}
}
