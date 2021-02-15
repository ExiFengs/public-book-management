package com.exi.bookmanagement.controller;

import com.exi.bookmanagement.entity.BookManager;
import com.exi.bookmanagement.mapper.BookManagerMapper;
import com.exi.bookmanagement.response.BookManagerResponse;
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
import java.util.List;

/**
 *  Copyright: Copyright (c) 2021 Asiainfo
 *
 *  @ClassName: com.exi.bookmanagement.controller.BookMangerController
 *  @Description: TODO 该类的功能描述
 * <p>
 *  @version: v1.0.0
 *  @author:   Fengsx
 *  @date: 2021/1/16 12:10
 * <p>
 *  Modification History:
 *  Date       Author    Version    Description
 * ----------------------------------------------------------
 *  2021/1/16    Fengsx     v1.0.0      修改原因
 */
@Api(tags = "图书管理员管理")
@RestController
@CrossOrigin
@Transactional
@Slf4j
@RequestMapping(value = "/bookManager")
public class BookMangerController {
    @Autowired
    private BookManagerMapper bookManagerMapper;

    @Autowired
    private IUserLoginService bookLoginService;

    @ApiOperation("分页查询图书管理员信息")
    @GetMapping(value = "/getBookManagersPage/{pageNum}/{pageSize}")
    public BookManagerResponse getBookManagersPage( @PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize){
        Page<BookManager> pageInfo = PageHelper.startPage(pageNum, pageSize);
        if (pageInfo.getPageNum() == 0 || pageInfo.getPageSize() == 0) {
            log.info("pageNum || pageSize 有值为空");
        }
        //并查询
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        List<BookManager> bookManagerList = bookManagerMapper.getAllBookManagerBean();
        // 如果在获取到数据之后就对数据进行转dto操作的话，会获取不到total数据，所以又定义了一个PageInfo类然后将数据进行属性复制，来获取数据
        PageInfo<BookManager> pageInfo1 = new PageInfo<>();
        BeanUtils.copyProperties(new PageInfo<>(bookManagerList), pageInfo1);
        log.info("封装后的 pageInfo:{}",pageInfo1);
        // 定义一个 response 把状态码和 message 加到 response 里面，不然前台会拒绝请求
        BookManagerResponse bookManagerResponse = new BookManagerResponse();
        bookManagerResponse.setCode(20000);
        bookManagerResponse.setMessage("返回 date 为 bookManagerList 的分页List");
        bookManagerResponse.setPageInfo(pageInfo1);
        return bookManagerResponse;
    }

    @ApiOperation("按名字查询图书管理员信息")
    @GetMapping(value = "/getBookManagerLikeNameList/{readerName}")
    public BookManagerResponse getBookManagerLikeNameList(@PathVariable("readerName") String readerName){
        List<BookManager> bookManagerLikeNameList = bookManagerMapper.getBookManagerLikeNameList(readerName);
        log.info("bookManagerLikeNameList: {}",bookManagerLikeNameList);
        BookManagerResponse bookManagerResponse = new BookManagerResponse();
        bookManagerResponse.setCode(20000);
        bookManagerResponse.setMessage("返回 date 为 bookManagerLikeNameList 的按名字模糊查询");
        bookManagerResponse.setBookManagerList(bookManagerLikeNameList);
        return bookManagerResponse;
    }

    @ApiOperation("获取所有图书管理员信息")
    @GetMapping("/getBookManagers")
    public BookManagerResponse getUsers() {
        List<BookManager> bookManagerList=bookManagerMapper.getAllBookManagerBean();
        BookManagerResponse bookManagerResponse = new BookManagerResponse();
        bookManagerResponse.setBookManagerList(bookManagerList);
        bookManagerResponse.setCode(20000);
        bookManagerResponse.setMessage("返回 date 为 bookManagerList 全部数据");
        return bookManagerResponse;
    }

    @ApiOperation("按id查询图书管理员信息")
    @GetMapping(value = "/getBookManagerById/{BookManagerId}")
    public BookManagerResponse getBookManagerById(@PathVariable("BookManagerId") Long bookManagerId) {
        BookManager bookManager =bookManagerMapper.getOneBookManagerBeanById(bookManagerId);
        BookManagerResponse bookManagerResponse = new BookManagerResponse();
        bookManagerResponse.setBookManager(bookManager);
        bookManagerResponse.setCode(20000);
        bookManagerResponse.setMessage("返回 date 为 bookManager 的按id查询");
        return bookManagerResponse;
    }

    @ApiOperation("添加图书管理员信息")
    @PostMapping("/addBookManager")
    public BookManagerResponse save(@RequestBody BookManager bookManager){
        BookManagerResponse bookManagerResponse = new BookManagerResponse();
        String readerAccount = bookManager.getReaderAccount();
        BookManager oneBookManagerBeanByreaderAccount = bookManagerMapper.getOneBookManagerBeanByreaderAccount(readerAccount);
        if (!ObjectUtils.isEmpty(oneBookManagerBeanByreaderAccount)){
            bookManagerResponse.setCode(88888);
            bookManagerResponse.setMessage("该账号已被注册");
            return bookManagerResponse;
        }
        try {
            //返回的 id 总为 1 ,result 影响条数, 代码是返回自增主键的？
            bookManager.setRoleId(2L);
            int id = bookManagerMapper.insertBookManagerBean(bookManager);
            bookManagerResponse.setBookManager(bookManager);
            bookManagerResponse.setResult(id);
            bookManagerResponse.setCode(20000);
            bookManagerResponse.setMessage("返回 date 为 addBookManagerResponse");
            if(id != 0){
                return bookManagerResponse;
            }else{
                return null;
            }
        }catch (Exception e){
            log.info("添加图书管理员信息出问题啦");
        }
        return bookManagerResponse;
    }

    @ApiOperation("更新图书管理员信息")
    @PutMapping(value="/updateBookManager")
    public BookManagerResponse update(@RequestBody BookManager bookManager) {
        BookManagerResponse bookManagerResponse = new BookManagerResponse();
        try {
            int result = bookManagerMapper.updateBookManagerBean(bookManager);
            log.info("更新后的bookManager:{}",bookManager);
            bookManagerResponse.setResult(result);
            bookManagerResponse.setBookManager(bookManager);
            bookManagerResponse.setCode(20000);
            bookManagerResponse.setMessage("返回 date 为 updatebookManagerResponse");
            System.out.println("======" + result);
            if(result != 0){
                return bookManagerResponse;
            }else{
                return null;
            }
        }catch (Exception e){
            log.info("更新图书管理员信息出问题啦");
        }
        return bookManagerResponse;
    }

    @ApiOperation("删除图书管理员信息")
    @DeleteMapping(value="/deleteBookManager/{id}")
    public BookManagerResponse delete(@PathVariable("id") Long id) {
        BookManagerResponse bookManagerResponse = new BookManagerResponse();
        try {
            int result = bookManagerMapper.deleteBookManagerBean(id);
            bookManagerResponse.setResult(result);
            bookManagerResponse.setCode(20000);
            bookManagerResponse.setMessage("返回 date 为 deletebookManagerResponse");
            if (result != 0){
                return bookManagerResponse;
            }else {
                return null;
            }
        }catch (Exception e){
            log.info("删除图书管理员信息出问题啦");
        }
        return bookManagerResponse;
    }

    @ApiOperation("图书管理员进行登录")
    @PostMapping(value = "/login")
    public BookManagerResponse login(@RequestBody BookManager bookManager){
        BookManagerResponse bookManagerResponse = new BookManagerResponse();
        // 判空
        if (StringUtils.isEmpty(bookManager.getReaderAccount()) ||
                StringUtils.isEmpty(bookManager.getReaderPassword())){
            bookManagerResponse.setCode(888888);
            bookManagerResponse.setMessage("传过来的用户名或密码为空");
            return bookManagerResponse;
        }
        // 根据用户名、密码查询数据
        BookManager loginBookManager = bookLoginService.getBookMangerByNameAndPassword(bookManager);

        log.info("查到的图书管理员数据：" + loginBookManager);

        if (loginBookManager == null){
            bookManagerResponse.setCode(888888);
            bookManagerResponse.setMessage("用户名或密码错误");
            return bookManagerResponse;
        }else if (loginBookManager != null){
            System.out.println(("登录的图书管理员的 id 为："+loginBookManager.getBookManagerId()));
            // 生成token
            String jwtToken = JwtUtils.getJwtToken(loginBookManager.getBookManagerId(), loginBookManager.getReaderAccount());
            System.out.println("返回的 token 为：" + jwtToken);
            bookManagerResponse.setBookManager(loginBookManager);
            bookManagerResponse.setToken(jwtToken);
            bookManagerResponse.setCode(20000);
            bookManagerResponse.setMessage("返回 date 为 jwtToken");
            return bookManagerResponse;
        }
        return null;
    }

    @ApiOperation("前端返回 token 后台接收")
    @GetMapping(value = "/getBookManagerInfo")
    public BookManagerResponse getBookManagerInfo(@RequestBody HttpServletRequest request){
        log.info("这是请求{} ", request.getParameter("token"));
        Integer bookManagerId = JwtUtils.getMemberIdByJwtToken(request);
        System.out.println("bookManagerId 为：：：：：" + bookManagerId);
        BookManagerResponse bookManagerResponse = new BookManagerResponse();
        BookManager bookManager = bookManagerMapper.getTokenForBookManagerId(bookManagerId);
        bookManagerResponse.setBookManager(bookManager);
        bookManagerResponse.setCode(20000);
        bookManagerResponse.setMessage("返回 date 为 token解析后的 bookManager");
        log.info("bookManagerResponse{} ", bookManagerResponse);
        return bookManagerResponse;
    }

}
