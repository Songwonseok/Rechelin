package com.web.curation.service;


import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Service;

import com.web.curation.model.DTO.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtServiceImpl implements JwtService {

	private static String SECRETKEY = "TimelineApplicationSecretkey";
		
	private static int HOUR = 6; //12시간 동안 토큰 유효
	
	@Override
	public String makeJwt(User user) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		Date expireTime = new Date();
		expireTime.setTime(expireTime.getTime() + 1000 * 60 * 60 * HOUR);
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRETKEY);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
		
		Map<String, Object> headerMap = new HashMap<>();
		headerMap.put("typ", "JWT");
		headerMap.put("alg", "HS256");
		
		Map<String,Object> map = new HashMap<>();
		map.put("email", user.getEmail());
		map.put("nickname", user.getNickname());
		
		JwtBuilder builder = Jwts.builder().setHeader(headerMap)
				.setClaims(map)
				.setExpiration(expireTime)
				.signWith(signatureAlgorithm, signingKey);
		
		return builder.compact();
	}

	@Override
	public int checkJwt(String jwt) throws Exception {
		try {
			@SuppressWarnings("unused")
			Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(SECRETKEY))
					.parseClaimsJws(jwt).getBody(); //수행 되면 정상
			System.out.println("checkJWT!!");
			System.out.println(claims.toString());
			return 0;
		} catch (ExpiredJwtException exception) {
			System.out.println("토큰만료 !");
			//토큰 만료
			return 1;
		} catch (JwtException exception) {
			System.out.println("토큰변조!");
			//토큰 변조
			return 2;
		}
	}

}