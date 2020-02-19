package com.web.curation.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.model.ReviewlistResponse;
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
		
		// review num 媛��졇�삤湲�
		Review tmp = dao.findTopByStoreAndUserOrderByRnumDesc(review.getStore(), review.getUser());
		
		// 1) review.hashtag�쓣 媛��졇���꽌 hashtag�뿉 �씪移섑븯�뒗 媛� �솗�씤
		String hashtag = review.getHashtag();
		String[] tagList = hashtag.split(","); // �빐�떆�깭洹� 遺꾨━
		
		for(int i=0; i<tagList.length; i++) {
			Hashtag tag = hashdao.findByKeyword(tagList[i]);
			
			System.out.println(tagList[i]); 
			System.out.println(" "+ tag.toString());
			if(tag ==null) continue; // �뾾�뒗 �깭洹멸� �굹�삤硫� DB�뿉 異붽�?
			
			System.out.println("********************");
			System.out.println(tag.toString());
			Storetags st = new Storetags();
			st.setHashtag(tag);
			st.setReview(tmp);
			// hastag 媛앹껜�� review媛앹껜瑜� StoreTags�뿉 ���옣
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
			// 議고쉶�닔 1利앷�
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
			// �삊媛숈� 媛믪씠 �뱾�뼱�삤硫� - 踰꾪듉 痍⑥냼
			if(check.getStatus() == tmp.getStatus()) {
				likedao.delete(tmp);
				return 0;
			}else {				
				// �씠誘� �엳�떎�뒗嫄곕땲源� - �닔�젙
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
	public Comments addComment(Comments com) {
		
		comdao.save(com);
		Comments newcom = comdao.findTopByUserOrderByWdateDesc(com.getUser());
		return newcom;
	}

	@Override
	public void deleteComment(long num) {
		Comments com = comdao.findByNum(num);
		if(com!=null) comdao.delete(com);
	}

	@Override
	public List<Comments> getComment(long rnum) {
		// Review 媛앹껜瑜� 李얠븘�꽌
		Review review = dao.findByRnum(rnum);
		List<Comments> list = comdao.findAllByReview(review);
		// �빐�떦�릺�뒗 紐⑤뱺 �뙎湲��뱾�쓣 遺덈윭�삩�떎
		return list;
	}

	@Override
	public List<Bookmark> getBookmark(long num) {
		// email�쓣 �넻�빐 User瑜� 李얜뒗�떎
		User user = userdao.findById(num);
		List<Bookmark> list = bookdao.findAllByUser(user);
		
		return list;
		
	}

	@Override
	public boolean clickBookmark(Bookmark book) {
		Bookmark tmp = bookdao.findByUserAndReview(book.getUser(), book.getReview());
		if(tmp == null) {
			// �뾾�쑝�땲源� �깉濡� 異붽�
			bookdao.save(book);
			return true;
		}else {
			// �엳�뒗�뜲 �떎�떆 �닃���쑝�땲源� 痍⑥냼
			bookdao.delete(tmp);
			return false;
		}
	}

	@Override
	public List<ReviewlistResponse> getcurrentFeed(long num) {
		// email을 통해서 star 리스트를 가져온다.
		User user = userdao.findById(num);
		System.out.println(user.toString());
		List<Review> review = dao.feedList(user);
		List<ReviewlistResponse> list = new ArrayList<ReviewlistResponse>();
		
		for (Review r : review) {
			int like = likedao.countByReviewAndStatus(r, 1);
			int dislike = likedao.countByReviewAndStatus(r, 0);
			int comments = comdao.countByReview(r);
			list.add(new ReviewlistResponse(r,like,dislike,comments));
		}
		// start리스트의 게시물을 최근 순으로 가져온다
		return list;
	}

	@Override
	public List<ReviewlistResponse> getReview(long snum) {
		// store�쓽 num�쑝濡� �넻�빐�꽌 由щ럭�뱾�쓣 媛��졇�삩�떎
		Store store = storedao.findByNum(snum);
		List<Review> rlist =  dao.findAllByStore(store);
		List<ReviewlistResponse> list = new ArrayList<>();
		for(Review review : rlist) {
			int like = likedao.countByReviewAndStatus(review, 1);
			int dislike = likedao.countByReviewAndStatus(review, 0);
			int comments = comdao.countByReview(review);
			list.add(new ReviewlistResponse(review,like,dislike,comments));
		}
		
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

	@Override
	public JSONObject countLike(long rnum) {
		Review review = dao.findByRnum(rnum);
		int like = likedao.countByReviewAndStatus(review, 1);
		int dislike = likedao.countByReviewAndStatus(review, 0);
		JSONObject object = new JSONObject();
		object.put("like", like);
		object.put("dislike", dislike);
		
		return object;
	}

	@Override
	public List<Review> storereviews(long snum) {
		Store store = storedao.findByNum(snum);
		List<Review> list = dao.findAllByStore(store);
		return list;
	}
}
