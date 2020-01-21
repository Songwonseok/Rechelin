package com.web.curation.naver.login;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class NaverLogin {
	private String naverClientSecret = "whK5eQJhow";
	
	public String getUserInfo(String access_token) {
        String header = "Bearer " + access_token; // Bearer 다음에 공백 추가
        try {
            String apiURL = "https://openapi.naver.com/v1/nid/me";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", header);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer res = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                res.append(inputLine);
            }
            br.close();
            return res.toString();
        } catch (Exception e) {
            System.err.println(e);
            return "Err";
        }
    }
	public String createJWTToken(int id, String nickname, String email) {
        String token = null;
        DecodedJWT jwt = null;

//        try {
//            Long EXPIRATION_TIME = 1000L * 60L * 10L;
//            Date issuedAt = new Date();
//            Date notBefore = new Date(issuedAt.getTime());
//            Date expiresAt = new Date(issuedAt.getTime() + EXPIRATION_TIME);
//
//            Algorithm algorithm = Algorithm.HMAC256(tokenSecret);
//            token = JWT.create()
//                    .withIssuer("auth0")
//                    .withSubject(nickname)
//                    .withAudience("1ilsang")
//                    .withClaim("id", id)
//                    .withClaim("email", email)
//                    .withNotBefore(notBefore)
//                    .withExpiresAt(expiresAt)
//                    .sign(algorithm);
//        } catch (Exception e) {
//            System.err.println("err: " + e);
//        }
        return token;
    }
}
