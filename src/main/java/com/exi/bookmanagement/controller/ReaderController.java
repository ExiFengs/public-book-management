package com.exi.bookmanagement.controller;

import com.alibaba.fastjson.JSON;
import com.exi.bookmanagement.entity.Reader;
import com.exi.bookmanagement.mapper.ReaderMapper;
import com.exi.bookmanagement.response.ReaderResponse;
import com.exi.bookmanagement.service.IUserLoginService;
import com.exi.bookmanagement.utils.JwtUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *  Copyright: Copyright (c) 2021 Asiainfo
 *
 *  @ClassName: com.exi.bookmanagement.controller.ReaderController
 *  @Description: 该类的功能描述
 * <p>
 *  @version: v1.0.0
 *  @author:   Fengsx
 *  @date: 1/8/21 6:55 PM
 * <p>
 *  Modification History:
 *  Date       Author    Version    Description
 * ----------------------------------------------------------
 *  1/8/21    Fengsx     v1.0.0      修改原因
 */
@Api(tags = "读者管理")
@RestController
@CrossOrigin
@Transactional
@Slf4j
@RequestMapping(value = "/reader")
public class ReaderController {
    @Autowired
    private ReaderMapper readerMapper;

    @Autowired
    private IUserLoginService readerService;

    @ApiOperation("分页查询读者信息")
    @GetMapping(value = "/getReadersPage/{pageNum}/{pageSize}")
    public ReaderResponse getReaderListPage( @PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize){
        Page<Reader> pageInfo = PageHelper.startPage(pageNum, pageSize);
        if (pageInfo.getPageNum() == 0 || pageInfo.getPageSize() == 0) {
            log.info("pageNum || pageSize 有值为空");
        }
        //并查询
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        List<Reader> readerList = readerMapper.getAllReaderBean();
        // 如果在获取到数据之后就对数据进行转dto操作的话，会获取不到total数据，所以又定义了一个PageInfo类然后将数据进行属性复制，来获取数据
        PageInfo<Reader> pageInfo1 = new PageInfo<>();
        BeanUtils.copyProperties(new PageInfo<>(readerList), pageInfo1);
        log.info("封装后的 pageInfo:{}",pageInfo1);
        // 定义一个 response 把状态码和 message 加到 response 里面，不然前台会拒绝请求
        ReaderResponse readerResponse = new ReaderResponse();
        readerResponse.setCode(20000);
        readerResponse.setMessage("返回 date 为 readerList 的分页List");
        readerResponse.setPageInfo(pageInfo1);
        return readerResponse;
    }

    @ApiOperation("按名字查询读者信息")
    @GetMapping(value = "/getReaderLikeNameList/{readerName}")
    public ReaderResponse getReaderLikeNameList(@PathVariable("readerName") String readerName){
        List<Reader> readerLikeNameList = readerMapper.getReaderLikeNameList(readerName);
        log.info("readerLikeNameList: {}",readerLikeNameList);
        ReaderResponse readerResponse = new ReaderResponse();
        readerResponse.setCode(20000);
        readerResponse.setMessage("返回 date 为 readerList 的按名字模糊查询");
        readerResponse.setReaderList(readerLikeNameList);
        return readerResponse;
    }

    @ApiOperation("获取所有读者信息")
    @GetMapping("/getReaders")
    public ReaderResponse getUsers() {
        List<Reader> readerList=readerMapper.getAllReaderBean();
        ReaderResponse readerResponse = new ReaderResponse();
        readerResponse.setReaderList(readerList);
        readerResponse.setCode(20000);
        readerResponse.setMessage("返回 date 为 reader全部数据");
        return readerResponse;
    }

    @ApiOperation("按id查询读者信息")
    @GetMapping(value = "/getReaderById/{readerId}")
    public ReaderResponse getReaderById(@PathVariable("readerId") Long readerId) {
        Reader reader=readerMapper.getOneReaderBean(readerId);
        ReaderResponse readerResponse = new ReaderResponse();
        readerResponse.setReader(reader);
        readerResponse.setCode(20000);
        readerResponse.setMessage("返回 date 为 reader 的按id查询");
        return readerResponse;
    }

    @ApiOperation("添加读者信息")
    @PostMapping("/addReader")
    public ReaderResponse save(@RequestBody Reader reader){
        ReaderResponse readerResponse = new ReaderResponse();
        //不能有重复的 account
        String readerAccount = reader.getReaderAccount();
        Reader readerAccount1 = readerMapper.getReaderAccount(readerAccount);
        if (!ObjectUtils.isEmpty(readerAccount1)){
            readerResponse.setCode(88888);
            readerResponse.setMessage("该账号已被注册");
            return readerResponse;
        }
        Date time = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String current = sdf.format(time);
        reader.setRegistrationTime(current);
        reader.setRoleId(1L);
        log.info(JSON.toJSONString(reader + "==========="));
        try {
            //返回的 id 总为 1 ,result 影响条数, 代码是返回自增主键的？
            int id = readerMapper.insertReaderBean(reader);
            readerResponse.setReader(reader);
            readerResponse.setResult(id);
            readerResponse.setCode(20000);
            readerResponse.setMessage("返回 date 为 addReaderResponse");
            if(id != 0){
                return readerResponse;
            }else{
                return null;
            }
        }catch (Exception e){
            log.info("添加读者信息出问题啦");
        }
        return readerResponse;
    }

    @ApiOperation("更新读者信息")
    @PutMapping(value="updateReader")
    public ReaderResponse update(@RequestBody Reader reader) {
        ReaderResponse readerResponse = new ReaderResponse();
        try {
            int result = readerMapper.updateReaderBean(reader);
            log.info("更新后的reader:{}",reader);
            readerResponse.setResult(result);
            readerResponse.setReader(reader);
            readerResponse.setCode(20000);
            readerResponse.setMessage("返回 date 为 updateReaderResponse");
            System.out.println("======" + result);
            if(result != 0){
                return readerResponse;
            }else{
                return null;
            }
        }catch (Exception e){
            log.info("更新读者信息出问题啦");
        }
        return readerResponse;
    }

    @ApiOperation("删除读者信息")
    @DeleteMapping(value="/deleteReader/{id}")
    public ReaderResponse delete(@PathVariable("id") Long id) {
        ReaderResponse readerResponse = new ReaderResponse();
        try {
            int result = readerMapper.deleteReaderBean(id);
            readerResponse.setResult(result);
            readerResponse.setCode(20000);
            readerResponse.setMessage("返回 date 为 deleteReaderResponse");
            if (result != 0){
                return readerResponse;
            }else {
                return null;
            }
        }catch (Exception e){
            log.info("删除读者信息出问题啦");
        }
        return readerResponse;
    }

    @ApiOperation("读者进行登录")
    @PostMapping(value = "/login")
    public ReaderResponse login(@RequestBody Reader reader){
        ReaderResponse readerResponse = new ReaderResponse();
        // 判空
        if (StringUtils.isEmpty(reader.getReaderAccount()) ||
                StringUtils.isEmpty(reader.getReaderPassword())){
            readerResponse.setCode(888888);
            readerResponse.setMessage("传过来的用户名或密码为空");
            return readerResponse;
        }
        // 根据用户名、密码查询数据
        Reader loginReader = readerService.getUserByNameAndPassword(reader);

        log.info("查到的读者数据：" + loginReader);

        if (loginReader == null){
            readerResponse.setCode(888888);
            readerResponse.setMessage("用户名或密码错误");
            return readerResponse;
        }else if (loginReader != null){
            System.out.println(("登录的读者的 id 为："+loginReader.getReaderId()));
            // 生成token
            String jwtToken = JwtUtils.getJwtToken(loginReader.getReaderId(), loginReader.getReaderAccount());
            System.out.println("返回的 token 为：" + jwtToken);
            readerResponse.setReader(loginReader);
            readerResponse.setToken(jwtToken);
            readerResponse.setCode(20000);
            readerResponse.setMessage("返回 date 为 jwtToken");
            return readerResponse;
        }
        return null;
    }

    @ApiOperation("前端返回 token 后台接收")
    @PostMapping(value = "/getReaderInfo")
    public ReaderResponse getReaderInfo(@RequestBody HttpServletRequest request){
        log.info("这是请求{} ", request.getParameter("token"));
        Integer readerId = JwtUtils.getMemberIdByJwtToken(request);
        System.out.println("readerId 为：：：：：" + readerId);
        ReaderResponse readerResponse = new ReaderResponse();
        Reader readerBean = readerMapper.getTokenForReaderId(readerId);
        readerResponse.setReader(readerBean);
        readerResponse.setCode(20000);
        readerResponse.setMessage("返回 date 为 token解析后的 readerBean");
        log.info("这是readerResponse{} ", readerResponse);
        return readerResponse;
    }
}
