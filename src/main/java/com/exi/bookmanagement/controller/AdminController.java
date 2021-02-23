package com.exi.bookmanagement.controller;

import com.exi.bookmanagement.entity.Admin;
import com.exi.bookmanagement.mapper.AdminMapper;
import com.exi.bookmanagement.response.AdminResponse;
import com.exi.bookmanagement.service.IUserLoginService;
import com.exi.bookmanagement.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Copyright: Copyright (c) 2021 Asiainfo
 *
 * @ClassName: com.exi.bookmanagement.controller.AdminController
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: Fengsx
 * @date: 2021/2/23 18:25
 * <p>
 * Modification History:
 * Date       Author    Version    Description
 * ----------------------------------------------------------
 * 2021/2/23    Fengsx     v1.0.0      修改原因
 */
@Api(tags = "超级管理员端")
@RestController
@CrossOrigin
@Transactional
@Slf4j
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    private IUserLoginService bookLoginService;

    @Autowired
    private AdminMapper adminMapper;
    
    @ApiOperation("超级管理员进行登录")
    @PostMapping(value = "/login")
    public AdminResponse login(@RequestBody Admin admin){
        AdminResponse adminResponse = new AdminResponse();
        // 判空
        if (StringUtils.isEmpty(admin.getReaderAccount()) ||
                StringUtils.isEmpty(admin.getReaderPassword())){
            adminResponse.setCode(888888);
            adminResponse.setMessage("传过来的用户名或密码为空");
            return adminResponse;
        }
        // 根据用户名、密码查询数据
        Admin loginBookManager = bookLoginService.getAdminByNameAndPassword(admin);
        log.info("查到的超级管理员数据：" + loginBookManager);
        if (loginBookManager == null){
            adminResponse.setCode(888888);
            adminResponse.setMessage("用户名或密码错误");
            return adminResponse;
        }else if (loginBookManager != null){
            System.out.println(("登录的超级管理员的 id 为："+loginBookManager.getAdminId()));
            // 生成token
            String jwtToken = JwtUtils.getAdminJwtToken(loginBookManager.getAdminId(), loginBookManager.getReaderAccount());
            System.out.println("返回的 token 为：" + jwtToken);
            adminResponse.setAdmin(loginBookManager);
            adminResponse.setToken(jwtToken);
            adminResponse.setCode(20000);
            adminResponse.setMessage("返回 date 为 jwtToken");
            return adminResponse;
        }
        return null;
    }

    @ApiOperation("前端返回 token 后台接收")
    @PostMapping(value = "/getBookManagerInfo")
    public AdminResponse getBookManagerInfo(@RequestBody HttpServletRequest request){
        log.info("这是后台接收请求的 token{} ", request.getParameter("token"));
        AdminResponse adminResponse = new AdminResponse();
        try {
            Integer bookManagerId = JwtUtils.getAdminIdByJwtToken(request);
            System.out.println("AdminId 为：" + bookManagerId);
            Admin admin = adminMapper.getTokenForAdminId(bookManagerId);
            adminResponse.setAdmin(admin);
            adminResponse.setCode(20000);
            adminResponse.setMessage("返回 date 为 token解析后的 admin");
            log.info("adminResponse{} ", adminResponse);
            return adminResponse;
        }catch (Exception e){
            e.printStackTrace();
            adminResponse.setCode(88888);
            adminResponse.setMessage("管理员登录出错啦");
            log.info("adminResponse{} ", adminResponse);
            return adminResponse;
        }
    }

}
