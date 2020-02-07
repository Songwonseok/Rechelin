//디비 셋팅 주석 
package com.web.curation.model.DAO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.model.DTO.User;


public interface UserDao extends JpaRepository<User, String> {
	Optional<User> findById(Long id);
	User findUserByEmailAndPw(String email, String pw);
	User findByNickname(String nickname);
	User findByEmail(String email);
	Optional<User> findByEmailOrNickname(String email,String name);

}
