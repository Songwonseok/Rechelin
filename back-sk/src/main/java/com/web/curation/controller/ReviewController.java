package com.web.curation.controller;

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
import com.web.curation.model.user.Bookmark;
import com.web.curation.model.user.Comments;
import com.web.curation.model.user.Likecheck;
import com.web.curation.model.user.Review;
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
	public Object register(@RequestBody Review review) {
		final BasicResponse result = new BasicResponse();
		service.register(review);
		result.status = true;
		result.data = "리뷰 등록 성공";
		result.object = review;
//		if(service.register(review)) {
//			result.status = true;
//		result.data = "리뷰 등록 성공";
//		result.object = review;
//		}else {
//			result.status = false;
//    		result.data = "리뷰 등록 실패 - 유저가 이미 음식점을 등록함";
//		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PutMapping("/review/update")
	@ApiOperation(value = "리뷰 업데이트")
	public Object update(@RequestBody Review review) {
		final BasicResponse result = new BasicResponse();
		
		if(service.update(review)) {
			result.status = true;
    		result.data = "리뷰 업데이트 성공";
    		result.object = review;
		}else {
			result.status = false;
    		result.data = "리뷰 업데이트 실패 - 존재하지않는 리뷰";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/review/delete")
	@ApiOperation(value = "리뷰 삭제")
	public Object delete(long rnum) {
		final BasicResponse result = new BasicResponse();
		
		if(service.delete(rnum)) {
			result.status = true;
    		result.data = "리뷰 삭제 성공";
		}else {
			result.status = false;
    		result.data = "리뷰 삭제 실패 - 존재하지않는 리뷰";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/review/detail/{rnum}")
	@ApiOperation(value = "리뷰 조회")
	public Object detail(@PathVariable long rnum) {
		final BasicResponse result = new BasicResponse();
		System.out.println(rnum);
		Review review = service.detail(rnum);
		if(review!=null) {
			result.status = true;
    		result.data = "리뷰 조회 성공";
    		result.object = review;
		}else {
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

		if(service.clickBookmark(book)) {
			result.data = "북마크 등록 성공";
		}else {
			result.data = "북마크 삭제 성공";
		}
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping("/review/bookmark/getList")
	@ApiOperation(value = "북마크 리스트 가져오기")
	public Object getBookmark(@RequestParam String email) {
		final BasicResponse result = new BasicResponse();
		
		result.status = true;
		result.data = "유저의 모든 북마크 리스트 조회 성공";
		result.object = service.getBookmark(email);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping("/review/feed")
	@ApiOperation(value = "피드 최근 리스트 가져오기")
	public Object getcurrentFeed(@RequestParam String email) {
		final BasicResponse result = new BasicResponse();
		
		System.out.println("CONTROLLER"+email);
		result.status = true;
		result.data = "유저의 모든 북마크 리스트 조회 성공";
		result.object = service.getcurrentFeed(email);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
}
