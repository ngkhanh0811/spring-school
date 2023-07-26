package com.example.springschool.service.impl;

import com.example.springschool.entity.User;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;

@Component
public class JWTUtils {
    public static final String SECRET_KEY = "1112343223523523515235235211111111111111111111111111";
    public static final String USERNAME = "username";
    public static final int expireTime = 86400000;
    public static final int refreshExpireTime = 106400000;
    public static String genRefreshToken(User user){
        String token = null;
        try {
            JWSSigner signer = new MACSigner(generateShareSecret());
            JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();
            builder.claim(USERNAME, user.getUsername());
            builder.expirationTime(new Date(System.currentTimeMillis() + refreshExpireTime)); // lấy ra thời gian thực tế hết hạn
            JWTClaimsSet claimsSet = builder.build(); // đóng gói payload của token
            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
            signedJWT.sign(signer); // kí
            token = signedJWT.serialize();
            return token;
        } catch(Exception e){
            System.err.println(e);
        }
        return token;
    }

//    public static String genToken(User user){
//        String token = null;
//        try {
//            JWSSigner signer = new MACSigner(generateShareSecret());
//            JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();
//            builder.claim(USERNAME, user.getUsername());
//            builder.expirationTime(new Date(System.currentTimeMillis() + expireTime)); // lấy ra thời gian thực tế hết hạn
//            JWTClaimsSet claimsSet = builder.build(); // đóng gói payload của token
//            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
//            signedJWT.sign(signer); // kí
//            token = signedJWT.serialize();
//            return token;
//        } catch(Exception e){
//            System.err.println(e);
//        }
//        return token;
//    }


    public static String genToken(User user) {
        String token = null;
        try {
            JWSSigner signer = new MACSigner(generateShareSecret());
            JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();
            builder.claim(USERNAME, user.getUsername());
            builder.expirationTime(new Date(System.currentTimeMillis() + expireTime));
            JWTClaimsSet claimsSet = builder.build(); // đóng gói cho payload token
            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
            signedJWT.sign(signer);
            token = signedJWT.serialize();
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }


    public boolean isTokenExpire(String token){
        Date expiredTime = getExpireDateFromToken(token);
        return expiredTime.before(new Date());
    }

    public String getUsernameFromToken(String token){
        String username = null;
        JWTClaimsSet claimsSet = getClaimFromToken(token);
        try {
            username = claimsSet.getStringClaim(USERNAME);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return username;
    }

    private JWTClaimsSet getClaimFromToken(String token){
        JWTClaimsSet claimsSet = null;
        try {
            SignedJWT sign = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(generateShareSecret());
            if (sign.verify(verifier)){
                claimsSet = sign.getJWTClaimsSet();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claimsSet;
    }
    public Date getExpireDateFromToken(String token){
        JWTClaimsSet claimsSet = getClaimFromToken(token);
        return claimsSet.getExpirationTime();
    }
    private static byte[] generateShareSecret(){
        // 256 bit
        byte[] shareSecret = new byte[32];
        return shareSecret = SECRET_KEY.getBytes();
    }
}