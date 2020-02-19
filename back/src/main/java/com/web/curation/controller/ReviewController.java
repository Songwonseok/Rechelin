
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
	@ApiOperation(value = "由щ럭 �벑濡�")
	public Object register(@RequestBody(required = true) Review review) {

		final BasicResponse result = new BasicResponse();
		System.out.println("--------------");
		service.register(review);
		
		result.status = true;
		result.data = "由щ럭 �벑濡� �꽦怨�";
		result.object = review;

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PutMapping("/review/update")
	@ApiOperation(value = "由щ럭 �뾽�뜲�씠�듃")
	public Object update(@RequestBody Review review) {
		final BasicResponse result = new BasicResponse();

		if (service.update(review)) {
			result.status = true;
			result.data = "由щ럭 �뾽�뜲�씠�듃 �꽦怨�";
			result.object = review;
		} else {
			result.status = false;
			result.data = "由щ럭 �뾽�뜲�씠�듃 �떎�뙣 - 議댁옱�븯吏��븡�뒗 由щ럭";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@DeleteMapping("/review/delete")
	@ApiOperation(value = "由щ럭 �궘�젣")
	public Object delete(long rnum) {
		final BasicResponse result = new BasicResponse();

		if (service.delete(rnum)) {
			result.status = true;
			result.data = "由щ럭 �궘�젣 �꽦怨�";
		} else {
			result.status = false;
			result.data = "由щ럭 �궘�젣 �떎�뙣 - 議댁옱�븯吏��븡�뒗 由щ럭";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/review/detail/{rnum}")
	@ApiOperation(value = "由щ럭 �긽�꽭 議고쉶")
	public Object detail(@PathVariable long rnum) {
		final BasicResponse result = new BasicResponse();
		System.out.println(rnum);
		Review review = service.detail(rnum);
		if (review != null) {
			result.status = true;
			result.data = "由щ럭 議고쉶 �꽦怨�";
			result.object = review;
		} else {
			result.status = false;
			result.data = "由щ럭 議고쉶 �떎�뙣 - 議댁옱�븯吏��븡�뒗 由щ럭";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/review/like")
	@ApiOperation(value = "由щ럭 �룄���뤌�슂 / �븞�뤌�슂")
	public Object like(@RequestBody Likecheck check) {
		////// 1 - �룄�� �뤌�슂 0 - �븞�뤌�슂
		final BasicResponse result = new BasicResponse();
		int rs = service.useful(check);
		result.status = true;
		result.object = check;

		switch (rs) {
		case 0:
			result.data = "�룄�� 踰꾪듉 �궘�젣 �셿猷�";
			break;
		case 1:
			result.data = "�룄�� 踰꾪듉 �닔�젙 �셿猷�";
			break;
		case 2:
			result.data = "�룄�� 踰꾪듉 �벑濡� �셿猷�";
			break;
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/review/comment")
	@ApiOperation(value = "�뙎湲� �벑濡�")
	public Object addComment(@RequestBody Comments com) {
		final BasicResponse result = new BasicResponse();
		
		result.status = true;
		result.data = "�뙎湲� �벑濡� �꽦怨�";
		result.object = service.addComment(com);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@DeleteMapping("/review/comment/{num}")
	@ApiOperation(value = "�뙎湲� �궘�젣")
	public Object deleteComment(@PathVariable long num) {
		final BasicResponse result = new BasicResponse();
		service.deleteComment(num);
		result.status = true;
		result.data = "�뙎湲� �궘�젣 �꽦怨�";
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/review/comment/{rnum}")
	@ApiOperation(value = "紐⑤뱺 �뙎湲� 媛��졇�삤湲�")
	public Object getComment(@PathVariable long rnum) {
		final BasicResponse result = new BasicResponse();

		result.status = true;
		result.data = "紐⑤뱺 �뙎湲� 由ъ뒪�듃 議고쉶 �꽦怨�";
		result.object = service.getComment(rnum);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/review/bookmark")
	@ApiOperation(value = "遺곷쭏�겕 異붽� / �궘�젣")
	public Object addBookMark(@RequestBody Bookmark book) {
		final BasicResponse result = new BasicResponse();
		result.status = true;
		result.object = book;

		if (service.clickBookmark(book)) {
			result.data = "遺곷쭏�겕 �벑濡� �꽦怨�";
		} else {
			result.data = "遺곷쭏�겕 �궘�젣 �꽦怨�";
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/review/bookmark/getList")
	@ApiOperation(value = "遺곷쭏�겕 由ъ뒪�듃 媛��졇�삤湲�")
	public Object getBookmark(@RequestParam long num) {
		final BasicResponse result = new BasicResponse();

		result.status = true;
		result.data = "�쑀���쓽 紐⑤뱺 遺곷쭏�겕 由ъ뒪�듃 議고쉶 �꽦怨�";
		result.object = service.getBookmark(num);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/review/feed")
	@ApiOperation(value = "�뙏濡쒖슦�븳 �뵾�뱶 理쒓렐 由ъ뒪�듃")
	public Object getcurrentFeed(@RequestParam long num) {
		final BasicResponse result = new BasicResponse();

		System.out.println("CONTROLLER" + num);
		result.status = true;
		result.data = "�뙏濡쒖슦�븳 �뵾�뱶 理쒓렐 由ъ뒪�듃 媛��졇�삤湲� �꽦怨�";
		result.object = service.getcurrentFeed(num);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/review/{snum}")
	@ApiOperation(value = "�떇�떦�쓽 紐⑤뱺 由щ럭 媛��졇�삤湲�")
	public Object getReview(@PathVariable long snum) {
		final BasicResponse result = new BasicResponse();
		
		result.status = true;
		result.data = "�떇�떦�쓽 紐⑤뱺 由щ럭 由ъ뒪�듃 議고쉶 �꽦怨�";
		result.object = service.getReview(snum);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("review/myList")
	@ApiOperation(value = "�궡媛� �옉�꽦�븳 由щ럭")
	public Object getmyList(@RequestParam long num) {
		final BasicResponse result = new BasicResponse();

		result.status = true;
		result.data = "�궡媛� �옉�꽦�븳 紐⑤뱺 由щ럭 由ъ뒪�듃 議고쉶 �꽦怨�";
		result.object = service.getMyReview(num);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("review/current")
	@ApiOperation(value = "理쒓렐 由щ럭")
	public Object getCurReview() {
		final BasicResponse result = new BasicResponse();

		result.status = true;
		result.data = "理쒓렐 由щ럭 由ъ뒪�듃 議고쉶 �꽦怨�";
		result.object = service.getCurReview();

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("review/viewsTop")
	@ApiOperation(value = "議고쉶�닔 �넂�� 由щ럭 TOP6 ")
	public Object getviewsTop() {
		final BasicResponse result = new BasicResponse();

		result.status = true;
		result.data = "議고쉶�닔 �넂�� 由щ럭 TOP6 議고쉶 �꽦怨�";
		result.object = service.getviewsTop();

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("review/likeTop")
	@ApiOperation(value = "醫뗭븘�슂 �넂�� 由щ럭 TOP6 ")
	public Object getlikeTop() {
		final BasicResponse result = new BasicResponse();

		result.status = true;
		result.data = "醫뗭븘�슂 �넂�� 由щ럭 TOP6 議고쉶 �꽦怨�";
		result.object = service.getlikeTop();

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("review/scoreTop")
	@ApiOperation(value = "�룊�젏 �넂�� 由щ럭 TOP6 ")
	public Object getscoreTop() {
		final BasicResponse result = new BasicResponse();

		result.status = true;
		result.data = "�룊�젏 �넂�� 由щ럭 TOP6 議고쉶 �꽦怨�";
		result.object = service.getscoreTop();

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("review/likeCount/{rnum}")
	@ApiOperation(value = "由щ럭 醫뗭븘�슂, �떕�뼱�슂 �닔")
	public Object sumlike(@PathVariable long rnum) {
		final BasicResponse result = new BasicResponse();
		JSONObject object = service.countLike(rnum);
		result.status = true;
		result.data = "醫뗭븘�슂, �떕�뼱�슂 議고쉶 �꽦怨�";
		result.object = object.toMap();

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
}
