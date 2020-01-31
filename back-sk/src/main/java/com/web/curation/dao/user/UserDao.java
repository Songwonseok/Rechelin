//디비 셋팅 주석 
package com.web.curation.dao.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.model.user.User;


public interface UserDao extends JpaRepository<User, String> {
	User findUserByEmailAndPw(String email, String pw);
	User findByNickname(String nickname);
	User findByEmail(String email);

}
