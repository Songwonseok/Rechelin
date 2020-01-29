package com.web.curation.service;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.web.curation.dao.user.StoreDao;
import com.web.curation.model.BasicResponse;
import com.web.curation.model.user.Store;

@Service
public class StoreServiceImpl implements StoreService {

	@Autowired
	StoreDao storeDao;
	
	public Object create(Store request) {
		
		final BasicResponse result = new BasicResponse();

		if (storeDao.findByNum(request.getNum()) != null) {
			result.status = false;
			result.data = "이미 등록되었습니다.";
			return new ResponseEntity<>(result, HttpStatus.OK);

		} 
		storeDao.save(request);
		result.status = true;
		result.data = "식당 등록 성공";
		result.object = new JSONObject(request).toMap();

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	public Object delete(long num) {
		Store store = storeDao.findByNum(num);
		storeDao.delete(store);
		final BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "삭제 성공";
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	public Object update(Store request) {
		Store store = storeDao.findByNum(request.getNum());
		store.update(request);
		storeDao.save(store);
				
		final BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "업데이트 성공";
		result.object = new JSONObject(store).toMap();
		return new ResponseEntity<>(result, HttpStatus.OK);		
	}

	public Object selectAll() {
		List<Store> list = storeDao.findAll();
		final BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "삭제 성공";
		result.object = list;
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	public Object selectOne(long num) {
		
		Store store = storeDao.findByNum(num);
		final BasicResponse result = new BasicResponse();
		if (store != null) {
			result.status = true;
			result.data = "성공";
		} else {
			result.status = false;
			result.data = "식당이 없습니다.";
		}
		result.object = new JSONObject(store).toMap();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	public Object searchName(String name) {
		List<Store> list = storeDao.findAllByNameLike(name);
		final BasicResponse result = new BasicResponse();
		if(list != null) {
			result.status = true;
			result.data = "검색 성공";
		}else {
			result.status = false;
			result.data = "검색 결과가 없습니다.";
		}
		result.object = list;
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
