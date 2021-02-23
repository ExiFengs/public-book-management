package com.exi.bookmanagement.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


/**
 * Copyright: Copyright (c) 2021 Asiainfo
 *
 * @ClassName: com.exi.bookmanagement.utils.JwtUtils
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: Fengsx
 * @date: 2021/1/17 15:34
 * <p>
 * Modification History:
 * Date       Author    Version    Description
 * ----------------------------------------------------------
 * 2021/1/17    Fengsx     v1.0.0      修改原因
 */
public class JwtUtils {
    //token过期时间, 这里设置的是一天的有效时间
    public static final long EXPIRE = 1000 * 60 * 60 * 24;
    //秘钥，自己随便设
    public static final String APP_SECRET = "ukc8BDbRigUDaY6pZFfWus2jZWLPHO";

    //生成token字符串的方法
    public static String getJwtToken(Long readerId, String readerAccount){

        String JwtToken = Jwts.builder()
                // JWT的头信息
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                // 设置过期时间
                .setSubject("onlineCourse-user")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                // 数据库中的字段,设置token主体部分 ，存储用户信息
                .claim("reader_id", readerId)
                .claim("reader_account", readerAccount)
                // 签名哈希
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                .compact();

        return JwtToken;
    }

    public static String getBookManagerJwtToken(Long bookManagerId, String readerAccount){

        String JwtToken = Jwts.builder()
                // JWT的头信息
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                // 设置过期时间
                .setSubject("onlineCourse-user")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                // 数据库中的字段
                .claim("book_manager_id", bookManagerId)
                .claim("reader_account", readerAccount)
                // 签名哈希
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                .compact();

        return JwtToken;
    }

    public static String getAdminJwtToken(Long adminId, String readerAccount){
        String JwtToken = Jwts.builder()
                // JWT的头信息
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                // 设置过期时间
                .setSubject("onlineCourse-user")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                // 数据库中的字段
                .claim("admin_id", adminId)
                .claim("reader_account", readerAccount)
                // 签名哈希
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                .compact();

        return JwtToken;
    }

    /**
     * 判断token是否存在与有效
     * @param jwtToken
     * @return
     */
    public static boolean checkToken(String jwtToken) {
        if(StringUtils.isEmpty(jwtToken)) return false;
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 判断token是否存在与有效
     * @param request
     * @return
     */
    public static boolean checkToken(HttpServletRequest request) {
        try {
            String jwtToken = request.getHeader("token");
            if(StringUtils.isEmpty(jwtToken)) return false;
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 根据token字符串获取读者id
     * @param request
     * @return
     */
    public static Integer getMemberIdByJwtToken(HttpServletRequest request) {
        String jwtToken = request.getParameter("token");
        if(StringUtils.isEmpty(jwtToken)) return null;
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        return (Integer)claims.get("reader_id");
    }

    /**
     * 根据token字符串获取图书管理员id
     * @param request
     * @return
     */
    public static Integer getBookManagerIdByJwtToken(HttpServletRequest request) {
        String jwtToken = request.getParameter("token");
        if(StringUtils.isEmpty(jwtToken)) return null;
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        return (Integer)claims.get("book_manager_id");
    }

    /**
     * 根据token字符串获取超级管理员id
     * @param request
     * @return
     */
    public static Integer getAdminIdByJwtToken(HttpServletRequest request) {
        String jwtToken = request.getParameter("token");
        if(StringUtils.isEmpty(jwtToken)) return null;
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        return (Integer)claims.get("admin_id");
    }
}
