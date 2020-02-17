
package com.web.curation.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.curation.model.BasicResponse;
import com.web.curation.model.DTO.Bookmark;
import com.web.curation.model.DTO.Comments;
import com.web.curation.model.DTO.Likecheck;
import com.web.curation.model.DTO.Review;
import com.web.curation.service.ReviewService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = { "*" }, maxAge = 6000)

@Controller
public class ReviewController {

	@Autowired
	private ReviewService service;

	@PostMapping("/review/register")
	@ApiOperation(value = "리뷰 등록")
	public Object register(@RequestBody(required = true) Review review) {

		final BasicResponse result = new BasicResponse();
		System.out.println("--------------");
		service.register(review);
		
		result.status = true;
		result.data = "리뷰 등록 성공";
		result.object = review;

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PutMapping("/review/update")
	@ApiOperation(value = "리뷰 업데이트")
	public Object update(@RequestBody Review review) {
		final BasicResponse result = new BasicResponse();

		if (service.update(review)) {
			result.status = true;
			result.data = "리뷰 업데이트 성공";
			result.object = review;
		} else {
			result.status = false;
			result.data = "리뷰 업데이트 실패 - 존재하지않는 리뷰";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@DeleteMapping("/review/delete")
	@ApiOperation(value = "리뷰 삭제")
	public Object delete(long rnum) {
		final BasicResponse result = new BasicResponse();

		if (service.delete(rnum)) {
			result.status = true;
			result.data = "리뷰 삭제 성공";
		} else {
			result.status = false;
			result.data = "리뷰 삭제 실패 - 존재하지않는 리뷰";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/review/detail/{rnum}")
	@ApiOperation(value = "리뷰 상세 조회")
	public Object detail(@PathVariable long rnum) {
		final BasicResponse result = new BasicResponse();
		System.out.println(rnum);
		Review review = service.detail(rnum);
		if (review != null) {
			result.status = true;
			result.data = "리뷰 조회 성공";
			result.object = review;
		} else {
			result.status = false;
			result.data = "리뷰 조회 실패 - 존재하지않는 리뷰";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/review/like")
	@ApiOperation(value = "리뷰 도움돼요 / 안돼요")
	public Object like(@RequestBody Likecheck check) {
		////// 1 - 도움 돼요 0 - 안돼요
		final BasicResponse result = new BasicResponse();
		int rs = service.useful(check);
		result.status = true;
		result.object = check;

		switch (rs) {
		case 0:
			result.data = "도움 버튼 삭제 완료";
			break;
		case 1:
			result.data = "도움 버튼 수정 완료";
			break;
		case 2:
			result.data = "도움 버튼 등록 완료";
			break;
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/review/comment")
	@ApiOperation(value = "댓글 등록")
	public Object addComment(@RequestBody Comments com) {
		final BasicResponse result = new BasicResponse();
		service.addComment(com);
		result.status = true;
		result.data = "댓글 등록 성공";
		result.object = com;
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@DeleteMapping("/review/comment/{num}")
	@ApiOperation(value = "댓글 삭제")
	public Object deleteComment(@PathVariable long num) {
		final BasicResponse result = new BasicResponse();
		service.deleteComment(num);
		result.status = true;
		result.data = "댓글 삭제 성공";
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/review/comment/{rnum}")
	@ApiOperation(value = "모든 댓글 가져오기")
	public Object getComment(@PathVariable long rnum) {
		final BasicResponse result = new BasicResponse();

		result.status = true;
		result.data = "모든 댓글 리스트 조회 성공";
		result.object = service.getComment(rnum);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/review/bookmark")
	@ApiOperation(value = "북마크 추가 / 삭제")
	public Object addBookMark(@RequestBody Bookmark book) {
		final BasicResponse result = new BasicResponse();
		result.status = true;
		result.object = book;

		if (service.clickBookmark(book)) {
			result.data = "북마크 등록 성공";
		} else {
			result.data = "북마크 삭제 성공";
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/review/bookmark/getList")
	@ApiOperation(value = "북마크 리스트 가져오기")
	public Object getBookmark(@RequestParam long num) {
		final BasicResponse result = new BasicResponse();

		result.status = true;
		result.data = "유저의 모든 북마크 리스트 조회 성공";
		result.object = service.getBookmark(num);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/review/feed")
	@ApiOperation(value = "팔로우한 피드 최근 리스트")
	public Object getcurrentFeed(@RequestParam long num) {
		final BasicResponse result = new BasicResponse();

		System.out.println("CONTROLLER" + num);
		result.status = true;
		result.data = "팔로우한 피드 최근 리스트 가져오기 성공";
		result.object = service.getcurrentFeed(num);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/review/{snum}")
	@ApiOperation(value = "식당의 모든 리뷰 가져오기")
	public Object getReview(@PathVariable long snum) {
		final BasicResponse result = new BasicResponse();

		result.status = true;
		result.data = "식당의 모든 리뷰 리스트 조회 성공";
		result.object = service.getReview(snum);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("review/myList")
	@ApiOperation(value = "내가 작성한 리뷰")
	public Object getmyList(@RequestParam long num) {
		final BasicResponse result = new BasicResponse();

		result.status = true;
		result.data = "내가 작성한 모든 리뷰 리스트 조회 성공";
		result.object = service.getMyReview(num);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("review/current")
	@ApiOperation(value = "최근 리뷰")
	public Object getCurReview() {
		final BasicResponse result = new BasicResponse();

		result.status = true;
		result.data = "최근 리뷰 리스트 조회 성공";
		result.object = service.getCurReview();

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("review/viewsTop")
	@ApiOperation(value = "조회수 높은 리뷰 TOP6 ")
	public Object getviewsTop() {
		final BasicResponse result = new BasicResponse();

		result.status = true;
		result.data = "조회수 높은 리뷰 TOP6 조회 성공";
		result.object = service.getviewsTop();

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("review/likeTop")
	@ApiOperation(value = "좋아요 높은 리뷰 TOP6 ")
	public Object getlikeTop() {
		final BasicResponse result = new BasicResponse();

		result.status = true;
		result.data = "좋아요 높은 리뷰 TOP6 조회 성공";
		result.object = service.getlikeTop();

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("review/scoreTop")
	@ApiOperation(value = "평점 높은 리뷰 TOP6 ")
	public Object getscoreTop() {
		final BasicResponse result = new BasicResponse();

		result.status = true;
		result.data = "평점 높은 리뷰 TOP6 조회 성공";
		result.object = service.getscoreTop();

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("review/likeCount/{rnum}")
	@ApiOperation(value = "review의  좋아요 갯수")
	public Object sumlike(@PathVariable long rnum) {
		final BasicResponse result = new BasicResponse();
		JSONObject object = service.countLike(rnum);
		result.status = true;
		result.data = "醫뗭븘�슂, �떕�뼱�슂 議고쉶 �꽦怨�";
		result.object = object.toMap();

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
