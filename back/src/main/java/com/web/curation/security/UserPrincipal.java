package com.web.curation.security;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.web.curation.model.DTO.User;

//custom UserDatails == UserPrincipal
//UserDetailService에서 반환됨
public class UserPrincipal implements UserDetails {
	private Long id;
	
	private String email;

    private String name;


    @JsonIgnore
    private String password;

    private String phone;
    
    
	@Override
	public String getUsername() {
		return name;
	}
    

    private Collection<? extends GrantedAuthority> authorities; //계정이 갖고 있는 권한 목록

    public UserPrincipal(Long id,String email, String name, Collection<? extends GrantedAuthority> authorities) {
    	this.id = id;
        this.email = email;
        this.name = name;
        this.authorities = authorities;
    }

    public UserPrincipal(Long id,String email, String name, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.authorities = authorities;
    }

    

    public static UserPrincipal create(User user) {
        List<GrantedAuthority> authorities = user.getRole().stream().map(role ->
                new SimpleGrantedAuthority(role.getName().name())
        ).collect(Collectors.toList());

        return new UserPrincipal(
        		user.getId(),
                user.getEmail(),
                user.getNickname(),
                user.getPw(),
                authorities
        );
    }

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


	
}