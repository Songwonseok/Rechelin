package com.web.curation.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider { //Jwt 토큰 생성 및 유효성 검증 컴포넌트 

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    private static String SECRETKEY = "TimelineApplicationSecretkey";

    private static int jwtExpirationInMs = 6 * 1000 * 60 * 60 ; //12시간 동안 토큰 유효

    //Jwt 토큰 생성
    public String generateToken(Authentication authentication) {

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs); //만기 날짜

        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId())) //데이터
                .setIssuedAt(new Date()) //토큰 발행 일자
                .setExpiration(expiryDate) //만기 기간
                .signWith(SignatureAlgorithm.HS512, SECRETKEY) //암호화 알고리즘, secret값 세팅
                .compact();
        
    }

    // Jwt 토큰에서 회원구별 정보 추출
    public Long  getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRETKEY)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    // Jwt 토큰의 유효성 확인
    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(SECRETKEY).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }
        return false;
    }

}