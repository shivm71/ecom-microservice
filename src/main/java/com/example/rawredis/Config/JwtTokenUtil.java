package com.example.rawredis.Config;

//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.io.Serializable;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
import java.io.Serializable;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import io.jsonwebtoken.JwtBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Slf4j
@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -2550185165626007488L;

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

//    @Value("${jwt.secret}")
    private String secret="secret";

    //retrieve username from jwt token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    //for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //generate token for user
    public String generateToken(UserDetails userDetails) {
        log.info("jwwt token utils++++++++++++++++");
        Map<String, Object> claims = new HashMap<>();
        log.info("going to call do genrate token +++++"+userDetails.getUsername());
        String result="";
        try {
            result = doGenerateToken(claims, userDetails.getUsername());
        }catch (Exception e){
            log.warn("zxhjbhzjxbhjzbxhj"+e);
        }
        log.warn("humne war kiya+++"+result);
        return result;
    }

    //while creating the token -
    //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    //2. Sign the JWT using the HS512 algorithm and secret key.
    //3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
    //   compaction of the JWT to a URL-safe string
    /*
    public String generateToken(User u) {
        Claims claims = Jwts.claims().setSubject(u.getUsername());
        claims.put("userId", u.getId() + "");
        claims.put("role", u.getRole());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
    * */
    private String doGenerateToken(Map<String, Object> claims, String subject) {
//    return subject;

//        claims = Jwts.claims().setSubject(subject);
//
//        Date now = new Date();
//        Date validity = new Date(now.getTime() + JWT_TOKEN_VALIDITY);
//        String secretKey = Base64.getEncoder().encodeToString(secret.getBytes());
//
//        return Jwts.builder()//
//                .setClaims(claims)//
//                .setIssuedAt(now)//
//                .setExpiration(validity)//
//                .signWith(SignatureAlgorithm.HS256, secretKey)//
//                .compact();
//    log.info("caleed++++++++++++++++");
//        claims().setSubject(u.getUsername());
       JwtBuilder l1=Jwts.builder();
                JwtBuilder l2=l1.setClaims(claims);
        JwtBuilder l3= l2.setSubject(subject);
        JwtBuilder l4= l3.setIssuedAt(new Date(System.currentTimeMillis()));
               JwtBuilder l5=l4.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000));
//                .sign(HMAC512(SECRET.getBytes());
        String res=l5.signWith(SignatureAlgorithm.HS256, secret).compact();
       return res;

//        claims.put("userId", u.getId() + "");
//        claims.put("role", u.getRole());
//        String res="cxcx";
//        try {
//            log.info("warning+++++");
//            claims = Jwts.claims().setSubject(subject);
//           res= Jwts.builder()
//                    .setClaims(claims)
//                    .signWith(SignatureAlgorithm.HS512, Base64.getEncoder().encodeToString(secret.getBytes()))
//                    .compact();
//           return res;
//        }catch(Exception e){
//            log.warn("777"+e);
//        }
//        return res;
    }

    //validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}