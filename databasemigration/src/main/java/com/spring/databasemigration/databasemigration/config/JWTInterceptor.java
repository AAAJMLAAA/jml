package com.spring.databasemigration.databasemigration.config;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.databasemigration.databasemigration.util.JWTUtils;

public class JWTInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求头中的令牌
        String token = request.getHeader("token");
        Map<String, Object> map = new LinkedHashMap<>();
        try{
            JWTUtils.verify(token);
            return true;
        }catch (SignatureVerificationException e){
            e.printStackTrace();
            map.put("message","无效签名");
        }catch (TokenExpiredException e){
            e.printStackTrace();
            map.put("message","token过期");
        }catch (AlgorithmMismatchException e){
            e.printStackTrace();
            map.put("message","token算法不一致！");
        }catch (Exception e){
            e.printStackTrace();
            map.put("message","token无效！");
        }
        //设置状态
        map.put("status",false);
        //将map转为json
        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        return false;
    }
}
