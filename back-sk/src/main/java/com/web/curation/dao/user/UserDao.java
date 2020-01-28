//디비 셋팅 주석 
package com.web.curation.dao.user;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.web.curation.model.user.User;


public interface UserDao extends JpaRepository<User, String> {
	User findUserByEmailAndPw(String email, String pw);
	User findByNickname(String nickname);
	User findByEmail(String email);
	
	
//	@Modifying
//	@Transactional
//	@Query("UPDATE from User u set u.nickname = update, u.pw = asdf1234!@, u.phone= 010-2222-2222, u.profile = asdf  where u.email= ascv@yahoo.com")
//	void updateUserInfo(User request);
}
