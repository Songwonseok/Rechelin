package com.web.curation.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.curation.model.DAO.UserDao;
import com.web.curation.model.DTO.User;

@Service //Repository를 통해 db에서 데이터를 가져온 후 컨트롤러에게 전달해주는 클래스
//(custom)userService는 (custom)userDetail을 반환
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    @Override
    @Transactional //트랜잭션-정상 여부에 따라 Commit, Rollback
    public UserDetails loadUserByUsername(String emailorname)
            throws UsernameNotFoundException {
        // email로 로그인 하게 함
    	
        User user = userDao.findByEmailOrNickname(emailorname, emailorname)
                .orElseThrow(() -> 
                        new UsernameNotFoundException("User not found with username or email : " + emailorname)
        );

        return  UserPrincipal.create(user);
    }
    
 // This method is used by JWTAuthenticationFilter
    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userDao.findById(id).orElseThrow(
            () -> new UsernameNotFoundException("User not found with id : " + id)
        );

        return UserPrincipal.create(user);
    }

}