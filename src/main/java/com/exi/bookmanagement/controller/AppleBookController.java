package com.exi.bookmanagement.controller;

import com.alibaba.fastjson.JSON;
import com.exi.bookmanagement.entity.AppleBook;
import com.exi.bookmanagement.entity.Book;
import com.exi.bookmanagement.mapper.AppleBookMapper;
import com.exi.bookmanagement.mapper.BookMapper;
import com.exi.bookmanagement.response.AppleBookResponse;
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
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Copyright: Copyright (c) 2021 Asiainfo
 *
 * @ClassName: com.exi.bookmanagement.controller.AppleBookController
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: Fengsx
 * @date: 2021/2/2 11:18
 * <p>
 * Modification History:
 * Date       Author    Version    Description
 * ----------------------------------------------------------
 * 2021/2/2    Fengsx     v1.0.0      修改原因
 */
@Api(tags = "捐纸质图书管理")
@RestController
@CrossOrigin
@Transactional
@Slf4j
@RequestMapping(value = "/appleBook")
public class AppleBookController {
    @Autowired
    private AppleBookMapper appleBookMapper;

    @Autowired
    private BookMapper bookMapper;

    @ApiOperation("上架纸质书籍")
    @PutMapping(value = "/putAway")
    public AppleBookResponse putAway(@RequestBody AppleBook appleBook){
        AppleBookResponse appleBookResponse = new AppleBookResponse();
        appleBook.setState(1);
        Book book = new Book();
        book.setBookRepertory(appleBook.getBookRepertory());
        book.setBookAuthor(appleBook.getBookAuthor());
        book.setBookIntro(appleBook.getBookIntro());
        book.setBookIsbn(appleBook.getBookIsbn());
        book.setBookName(appleBook.getBookName());
        book.setCategoryId(appleBook.getCategoryId());
        book.setBookPress(appleBook.getBookPress());
        book.setBookPicture(appleBook.getBookPicture());
        log.info("book:{}", JSON.toJSONString(book));
        try{
            int i = bookMapper.insertBookBean(book);
            appleBookMapper.updateBookBeanByState(appleBook);
            if(i != 0){
                appleBookResponse.setResult(i);
                appleBookResponse.setAppleBook(appleBook);
                appleBookResponse.setCode(20000);
                appleBookResponse.setMessage("返回 date 上架成功的 response");
                return appleBookResponse;
            }else{
                appleBookResponse.setCode(88888);
                appleBookResponse.setMessage("上架失败！");
                return appleBookResponse;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        appleBookResponse.setCode(88888);
        appleBookResponse.setMessage("上架失败！");
        return appleBookResponse;
    }

    @ApiOperation("拒绝上架纸质书籍")
    @PutMapping(value = "/refuse")
    public AppleBookResponse refuse(@RequestBody AppleBook appleBook){
        AppleBookResponse appleBookResponse = new AppleBookResponse();
        appleBook.setState(2);
        try{
            int i = appleBookMapper.updateBookBeanByState(appleBook);
            if (i != 0){
                appleBookResponse.setResult(i);
                appleBookResponse.setAppleBook(appleBook);
                appleBookResponse.setCode(20000);
                appleBookResponse.setMessage("返回 date 取消上架成功的 response");
                return appleBookResponse;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        appleBookResponse.setCode(88888);
        appleBookResponse.setMessage("取消上架失败！");
        return appleBookResponse;

    }


    @ApiOperation("分页查询纸质图书信息")
    @GetMapping(value = "/getBooksPage/{pageNum}/{pageSize}")
    public AppleBookResponse getBooksPage(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize){
        Page<AppleBook> pageInfo = PageHelper.startPage(pageNum, pageSize);
        if (pageInfo.getPageNum() == 0 || pageInfo.getPageSize() == 0) {
            log.info("pageNum || pageSize 有值为空");
        }
        //并查询
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        List<AppleBook> appleBookList = appleBookMapper.getAllBookBean();
        // 如果在获取到数据之后就对数据进行转dto操作的话，会获取不到total数据，所以又定义了一个PageInfo类然后将数据进行属性复制，来获取数据
        PageInfo<AppleBook> pageInfo1 = new PageInfo<>();
        BeanUtils.copyProperties(new PageInfo<>(appleBookList), pageInfo1);
        log.info("封装后的 pageInfo:{}",pageInfo1);
        // 定义一个 response 把状态码和 message 加到 response 里面，不然前台会拒绝请求
        AppleBookResponse appleBookResponse = new AppleBookResponse();
        appleBookResponse.setCode(20000);
        appleBookResponse.setMessage("返回 date 为 appleBookList 的分页List");
        appleBookResponse.setPageInfo(pageInfo1);
        return appleBookResponse;
    }

    @ApiOperation("分页查询纸质图书信息")
    @GetMapping(value = "/getBooksPageById/{pageNum}/{pageSize}/{readerId}")
    public AppleBookResponse getBooksPage(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize, @PathVariable("readerId")Long readerId){
        Page<AppleBook> pageInfo = PageHelper.startPage(pageNum, pageSize);
        if (pageInfo.getPageNum() == 0 || pageInfo.getPageSize() == 0) {
            log.info("pageNum || pageSize 有值为空");
        }
        //并查询
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        List<AppleBook> appleBookList = appleBookMapper.getBookBeanById(readerId);
        PageInfo<AppleBook> pageInfo1 = new PageInfo<>();
        BeanUtils.copyProperties(new PageInfo<>(appleBookList), pageInfo1);
        log.info("封装后的 pageInfo:{}",pageInfo1);
        // 定义一个 response 把状态码和 message 加到 response 里面，不然前台会拒绝请求
        AppleBookResponse appleBookResponse = new AppleBookResponse();
        appleBookResponse.setCode(20000);
        appleBookResponse.setMessage("返回 date 为 appleBookList 的分页List");
        appleBookResponse.setPageInfo(pageInfo1);
        return appleBookResponse;
    }


    @ApiOperation("按名称查询纸质图书信息")
    @GetMapping(value = "/getBookLikeNameList/{bookName}")
    public AppleBookResponse getBookLikeNameList(@PathVariable("bookName") String bookName){
        List<AppleBook> appleBookList = appleBookMapper.getBookLikeNameList(bookName);
        log.info("appleBookList: {}",appleBookList);
        AppleBookResponse appleBookResponse = new AppleBookResponse();
        appleBookResponse.setCode(20000);
        appleBookResponse.setMessage("返回 date 为 appleBookList 的按名字模糊查询");
        appleBookResponse.setAppleBookList(appleBookList);
        return appleBookResponse;
    }

    @ApiOperation("获取所有纸质图书信息")
    @GetMapping("/getBooks")
    public AppleBookResponse getBooks() {
        List<AppleBook> appleBookList=appleBookMapper.getAllBookBean();
        AppleBookResponse appleBookResponse = new AppleBookResponse();
        appleBookResponse.setAppleBookList(appleBookList);
        appleBookResponse.setCode(20000);
        appleBookResponse.setMessage("返回 date 为 appleBookList 全部数据");
        return appleBookResponse;
    }

    @ApiOperation("按id查询纸质图书信息")
    @GetMapping(value = "/getBookById/{bookId}")
    public AppleBookResponse getBookById(@PathVariable("bookId") Long bookId) {
        AppleBook appleBook =appleBookMapper.getOneBookBeanById(bookId);
        AppleBookResponse appleBookResponse = new AppleBookResponse();
        appleBookResponse.setAppleBook(appleBook);
        appleBookResponse.setCode(20000);
        appleBookResponse.setMessage("返回 date 为 book 的按id查询");
        return appleBookResponse;
    }

    @ApiOperation("添加纸质图书信息")
    @PostMapping("/addBook")
    public AppleBookResponse save(@RequestBody AppleBook book){
        AppleBookResponse appleBookResponse = new AppleBookResponse();
        if (StringUtils.isEmpty(book.getBookPicture()) || book.getBookRepertory() == 0){
            log.info("你没有上传纸质图书照片,库存为 0");
            appleBookResponse.setCode(888888);
            appleBookResponse.setMessage("你没有上传纸质图书照片或者库存为 0");
            return appleBookResponse;
        }
        try {
            //返回的 id 总为 1 ,result 影响条数, 代码是返回自增主键的？
            book.setState(0);
            int id = appleBookMapper.insertBookBean(book);
            appleBookResponse.setAppleBook(book);
            appleBookResponse.setResult(id);
            appleBookResponse.setCode(20000);
            appleBookResponse.setMessage("返回 date 为 appleBookResponse");
            if(id != 0){
                return appleBookResponse;
            }else{
                return null;
            }
        }catch (Exception e){
            log.info("添加纸质图书信息出问题啦");
        }
        return appleBookResponse;
    }

    @ApiOperation("更新纸质图书信息")
    @PutMapping(value="/updateBook")
    public AppleBookResponse update(@RequestBody AppleBook book) {
        AppleBookResponse appleBookResponse = new AppleBookResponse();
        try {
            int result = appleBookMapper.updateBookBean(book);
            log.info("更新后的book:{}",book);
            appleBookResponse.setResult(result);
            appleBookResponse.setAppleBook(book);
            appleBookResponse.setCode(20000);
            appleBookResponse.setMessage("返回 date 为 updateBookResponse");
            System.out.println("======" + result);
            if(result != 0){
                return appleBookResponse;
            }else{
                return null;
            }
        }catch (Exception e){
            log.info("更新纸质图书信息出问题啦");
        }
        return appleBookResponse;
    }

    @ApiOperation("删除纸质图书信息")
    @DeleteMapping(value="/deleteBook/{id}")
    public AppleBookResponse delete(@PathVariable("id") Long id) {
        AppleBookResponse appleBookResponse = new AppleBookResponse();
        try {
            int result = appleBookMapper.deleteBookBean(id);
            appleBookResponse.setResult(result);
            appleBookResponse.setCode(20000);
            appleBookResponse.setMessage("返回 date 为 deleteBookBeanResponse");
            if (result != 0){
                return appleBookResponse;
            }else {
                return null;
            }
        }catch (Exception e){
            log.info("删除纸质图书信息出问题啦");
        }
        return appleBookResponse;
    }

    /**
     * 图片上传
     */
    @ApiOperation("图片上传")
    @PostMapping("/uploadBookImg")
    public AppleBookResponse uploadBookImg(@RequestParam("uploadFile") MultipartFile uploadFile ) throws IOException {
        AppleBookResponse appleBookResponse = new AppleBookResponse();
        //获得项目的类路径
        String path = ResourceUtils.getURL("classpath:").getPath();
        //空文件夹在编译时不会打包进入target中
        File uploadDir = new File(path+"/static/img/appleBook");
        if (!uploadDir.exists()) {
            System.out.println("上传图片路径不存在，正在创建...");
            uploadDir.mkdirs();
            System.out.println(uploadDir.getPath());
        }
        if ( uploadFile != null) {
            //获得上传文件的文件名
            String oldName = uploadFile.getOriginalFilename();
            System.out.println("[上传的文件名]：" + oldName);
            File avatar = new File(path + "/static/img/appleBook/" , oldName);
            try {
                //保存图片
                uploadFile.transferTo(avatar);
                //返回成功结果，附带文件的相对路径
                //http://localhost:8888/bookManagement/img/ad/WechatIMG33.jpg
                appleBookResponse.setCode(20000);
                appleBookResponse.setMessage("返回 date 为 上传图片文件的相对路径");
                appleBookResponse.setFileName("/img/appleBook/"+oldName);
                return appleBookResponse;
            }catch (IOException e) {
                e.printStackTrace();
                appleBookResponse.setCode(888888);
                appleBookResponse.setMessage("上传失败");
                return appleBookResponse;
            }
        }else {
            System.out.println("上传的文件为空");
            appleBookResponse.setCode(888888);
            appleBookResponse.setMessage("文件传输错误");
            return appleBookResponse;
        }

    }
}
