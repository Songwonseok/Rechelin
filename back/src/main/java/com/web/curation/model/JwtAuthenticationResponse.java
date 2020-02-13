package com.web.curation.model;

public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private Long userid;
    private String email;
    private String nickname;

    public JwtAuthenticationResponse(String accessToken, Long userid ,String email, String nickname) {
    	this.userid = userid;
        this.accessToken = accessToken;
        this.email = email;
        this.nickname = nickname;
    }

    public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

	public String getEmail() {
		return email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


}