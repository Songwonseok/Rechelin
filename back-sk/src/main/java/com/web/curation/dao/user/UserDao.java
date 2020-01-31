//디비 셋팅 주석 
package com.web.curation.dao.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.web.curation.model.user.User;


public interface UserDao extends JpaRepository<User, String> {
	User findUserByEmailAndPw(String email, String pw);
	User findByNickname(String nickname);
	User findByEmail(String email);

}
