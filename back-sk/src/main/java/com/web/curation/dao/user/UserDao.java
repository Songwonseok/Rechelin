//디비 셋팅 주석 
package com.web.curation.dao.user;

import com.web.curation.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, String> {
	User getUserByEmail(String email);
	User findUserByEmailAndPw(String email, String pw);

}
