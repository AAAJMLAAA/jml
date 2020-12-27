package com.spring.databasemigration.databasemigration.util;

import java.util.Calendar;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTUtils {

    private static final String SIGN = "sign";
    
    //生成token    header.payload.signature
    public static String getToken(Map<String,String> map){
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE,7);
        JWTCreator.Builder builder = JWT.create();
        map.forEach((k,v) -> {
            builder.withClaim(k,v);
        });
        String token = builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(SIGN));
        return token;
    }
    //验证token，如果验证通过，返回token信息
    public static DecodedJWT verify(String token){
    	/**
    	 * - SignatureVerificationException(签名不一致异常)
	- TokenExpiredException(令牌过期异常)
	- AlgorithmMismatchException(算法不匹配异常)
	- InvalidClaimException(失效的payload异常)
    	 */
        return JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
    }
}

