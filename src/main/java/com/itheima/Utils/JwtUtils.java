package com.itheima.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.nio.charset.StandardCharsets;

import java.util.Date;
import java.util.Map;


public class JwtUtils {

    /**
     * 生成token
     */
    public static String createJWT(String secretKey, long ttlMillis, Map<String, Object> claims) {
        //指定算法签名
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis() + ttlMillis;
        Date exp = new Date(nowMillis);

        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setExpiration(exp)
                .signWith(signatureAlgorithm, secretKey.getBytes(StandardCharsets.UTF_8));

        return builder.compact();
    }

    /**
     * token解密
     */
    public static Claims parseJWT(String secretKey, String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                .build()
                .parseClaimsJws(token)
                .getPayload();
        return claims;

    }

}
