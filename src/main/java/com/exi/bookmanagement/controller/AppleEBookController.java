package com.exi.bookmanagement.controller;

import com.alibaba.fastjson.JSON;
import com.exi.bookmanagement.entity.AppleEBook;
import com.exi.bookmanagement.entity.EBook;
import com.exi.bookmanagement.mapper.AppleEBookMapper;
import com.exi.bookmanagement.mapper.EBookMapper;
import com.exi.bookmanagement.response.AppleEBookResponse;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Copyright: Copyright (c) 2021 Asiainfo
 *
 * @ClassName: com.exi.bookmanagement.controller.AppleEBookController
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: Fengsx
 * @date: 2021/2/2 17:10
 * <p>
 * Modification History:
 * Date       Author    Version    Description
 * ----------------------------------------------------------
 * 2021/2/2    Fengsx     v1.0.0      修改原因
 */
@Api(tags = "捐电子图书管理")
@RestController
@CrossOrigin
@Slf4j
@RequestMapping(value = "/appleEBook")
public class AppleEBookController {
    @Autowired
    private AppleEBookMapper appleEBookMapper;
    
    @Autowired
    private EBookMapper eBookMapper;

    @ApiOperation("上架纸质书籍")
    @PutMapping(value = "/putAway")
    public AppleEBookResponse putAway(@RequestBody AppleEBook appleEBook){
        AppleEBookResponse appleEBookResponse = new AppleEBookResponse();
        appleEBook.setState(1);
        log.info("appleEBook:{}", JSON.toJSONString(appleEBook));
        EBook eBook = new EBook();
        eBook.setEBookAuthor(appleEBook.getEBookAuthor());
        eBook.setEBookIntro(appleEBook.getEBookIntro());
        eBook.setEBookIsbn(appleEBook.getEBookIsbn());
        eBook.setCategoryId(appleEBook.getCategoryId());
        eBook.setEBookName(appleEBook.getEBookName());
        eBook.setEBookFileUrl(appleEBook.getEBookFileUrl());
        eBook.setEBookPicture(appleEBook.getEBookPicture());
        eBook.setEBookPress(appleEBook.getEBookPress());
        log.info("book:{}", JSON.toJSONString(eBook));
        try{
            int i = eBookMapper.insertEBookBean(eBook);
            appleEBookMapper.updateEBookBeanByState(appleEBook);
            if(i != 0){
                appleEBookResponse.setResult(i);
                appleEBookResponse.setAppleEBook(appleEBook);
                appleEBookResponse.setCode(20000);
                appleEBookResponse.setMessage("返回 date 上架成功的 response");
                return appleEBookResponse;
            }else{
                appleEBookResponse.setCode(88888);
                appleEBookResponse.setMessage("上架失败！");
                return appleEBookResponse;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        appleEBookResponse.setCode(88888);
        appleEBookResponse.setMessage("上架失败！");
        return appleEBookResponse;
    }

    @ApiOperation("拒绝上架纸质书籍")
    @PutMapping(value = "/refuse")
    public AppleEBookResponse refuse(@RequestBody AppleEBook appleEBook){
        AppleEBookResponse appleEBookResponse = new AppleEBookResponse();
        appleEBook.setState(2);
        try{
            int i = appleEBookMapper.updateEBookBeanByState(appleEBook);
            if (i != 0){
                appleEBookResponse.setResult(i);
                appleEBookResponse.setAppleEBook(appleEBook);
                appleEBookResponse.setCode(20000);
                appleEBookResponse.setMessage("返回 date 取消上架成功的 response");
                return appleEBookResponse;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        appleEBookResponse.setCode(88888);
        appleEBookResponse.setMessage("取消上架失败！");
        return appleEBookResponse;

    }


    @ApiOperation("分页查询纸质图书信息")
    @GetMapping(value = "/getBooksPage/{pageNum}/{pageSize}")
    public AppleEBookResponse getBooksPage(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize){
        Page<AppleEBook> pageInfo = PageHelper.startPage(pageNum, pageSize);
        if (pageInfo.getPageNum() == 0 || pageInfo.getPageSize() == 0) {
            log.info("pageNum || pageSize 有值为空");
        }
        //并查询
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        List<AppleEBook> appleBookList = appleEBookMapper.getAllEBookBean();
        // 如果在获取到数据之后就对数据进行转dto操作的话，会获取不到total数据，所以又定义了一个PageInfo类然后将数据进行属性复制，来获取数据
        PageInfo<AppleEBook> pageInfo1 = new PageInfo<>();
        BeanUtils.copyProperties(new PageInfo<>(appleBookList), pageInfo1);
        log.info("封装后的 pageInfo:{}",pageInfo1);
        // 定义一个 response 把状态码和 message 加到 response 里面，不然前台会拒绝请求
        AppleEBookResponse appleEBookResponse = new AppleEBookResponse();
        appleEBookResponse.setCode(20000);
        appleEBookResponse.setMessage("返回 date 为 appleBookList 的分页List");
        appleEBookResponse.setPageInfo(pageInfo1);
        return appleEBookResponse;
    }

    @ApiOperation("按名称查询纸质图书信息")
    @GetMapping(value = "/getBookLikeNameList/{bookName}")
    public AppleEBookResponse getBookLikeNameList(@PathVariable("bookName") String bookName){
        List<AppleEBook> appleBookList = appleEBookMapper.getEBookLikeNameList(bookName);
        log.info("appleBookList: {}",appleBookList);
        AppleEBookResponse appleEBookResponse = new AppleEBookResponse();
        appleEBookResponse.setCode(20000);
        appleEBookResponse.setMessage("返回 date 为 appleBookList 的按名字模糊查询");
        appleEBookResponse.setAppleEBookList(appleBookList);
        return appleEBookResponse;
    }

    @ApiOperation("获取所有纸质图书信息")
    @GetMapping("/getBooks")
    public AppleEBookResponse getBooks() {
        List<AppleEBook> appleBookList=appleEBookMapper.getAllEBookBean();
        AppleEBookResponse appleEBookResponse = new AppleEBookResponse();
        appleEBookResponse.setAppleEBookList(appleBookList);
        appleEBookResponse.setCode(20000);
        appleEBookResponse.setMessage("返回 date 为 appleBookList 全部数据");
        return appleEBookResponse;
    }

    @ApiOperation("按id查询纸质图书信息")
    @GetMapping(value = "/getBookById/{bookId}")
    public AppleEBookResponse getBookById(@PathVariable("bookId") Long bookId) {
        AppleEBook appleEBook =appleEBookMapper.getOneEBookBeanById(bookId);
        AppleEBookResponse appleEBookResponse = new AppleEBookResponse();
        appleEBookResponse.setAppleEBook(appleEBook);
        appleEBookResponse.setCode(20000);
        appleEBookResponse.setMessage("返回 date 为 book 的按id查询");
        return appleEBookResponse;
    }

    @ApiOperation("添加纸质图书信息")
    @PostMapping("/addBook")
    public AppleEBookResponse save(@RequestBody AppleEBook book){
        AppleEBookResponse appleEBookResponse = new AppleEBookResponse();
        try {
            //返回的 id 总为 1 ,result 影响条数, 代码是返回自增主键的？
            book.setState(0);
            int id = appleEBookMapper.insertEBookBean(book);
            appleEBookResponse.setAppleEBook(book);
            appleEBookResponse.setResult(id);
            appleEBookResponse.setCode(20000);
            appleEBookResponse.setMessage("返回 date 为 appleEBookResponse");
            if(id != 0){
                return appleEBookResponse;
            }else{
                return null;
            }
        }catch (Exception e){
            log.info("添加纸质图书信息出问题啦");
        }
        return appleEBookResponse;
    }

    @ApiOperation("更新纸质图书信息")
    @PutMapping(value="/updateBook")
    public AppleEBookResponse update(@RequestBody AppleEBook book) {
        AppleEBookResponse appleEBookResponse = new AppleEBookResponse();
        try {
            int result = appleEBookMapper.updateEBookBean(book);
            log.info("更新后的book:{}",book);
            appleEBookResponse.setResult(result);
            appleEBookResponse.setAppleEBook(book);
            appleEBookResponse.setCode(20000);
            appleEBookResponse.setMessage("返回 date 为 updateBookResponse");
            System.out.println("======" + result);
            if(result != 0){
                return appleEBookResponse;
            }else{
                return null;
            }
        }catch (Exception e){
            log.info("更新纸质图书信息出问题啦");
        }
        return appleEBookResponse;
    }

    @ApiOperation("删除纸质图书信息")
    @DeleteMapping(value="/deleteBook/{id}")
    public AppleEBookResponse delete(@PathVariable("id") Long id) {
        AppleEBookResponse appleEBookResponse = new AppleEBookResponse();
        try {
            int result = appleEBookMapper.deleteEBookBean(id);
            appleEBookResponse.setResult(result);
            appleEBookResponse.setCode(20000);
            appleEBookResponse.setMessage("返回 date 为 deleteBookBeanResponse");
            if (result != 0){
                return appleEBookResponse;
            }else {
                return null;
            }
        }catch (Exception e){
            log.info("删除纸质图书信息出问题啦");
        }
        return appleEBookResponse;
    }

    /**
     * 图片上传
     */
    @ApiOperation("图片上传")
    @PostMapping("/uploadBookImg")
    public AppleEBookResponse uploadBookImg(@RequestParam("uploadFile") MultipartFile uploadFile ) throws IOException {
        AppleEBookResponse appleEBookResponse = new AppleEBookResponse();
        //获得项目的类路径
        String path = ResourceUtils.getURL("classpath:").getPath();
        //空文件夹在编译时不会打包进入target中
        File uploadDir = new File(path+"/static/img/appleEBook");
        if (!uploadDir.exists()) {
            System.out.println("上传图片路径不存在，正在创建...");
            uploadDir.mkdirs();
            System.out.println(uploadDir.getPath());
        }
        if ( uploadFile != null) {
            //获得上传文件的文件名
            String oldName = uploadFile.getOriginalFilename();
            System.out.println("[上传的文件名]：" + oldName);
            File avatar = new File(path + "/static/img/appleEBook/" , oldName);
            try {
                //保存图片
                uploadFile.transferTo(avatar);
                //返回成功结果，附带文件的相对路径
                //http://localhost:8888/bookManagement/img/ad/WechatIMG33.jpg
                appleEBookResponse.setCode(20000);
                appleEBookResponse.setMessage("返回 date 为 上传图片文件的相对路径");
                appleEBookResponse.setFileName("/img/appleEBook/"+oldName);
                return appleEBookResponse;
            }catch (IOException e) {
                e.printStackTrace();
                appleEBookResponse.setCode(888888);
                appleEBookResponse.setMessage("上传失败");
                return appleEBookResponse;
            }
        }else {
            System.out.println("上传的文件为空");
            appleEBookResponse.setCode(888888);
            appleEBookResponse.setMessage("文件传输错误");
            return appleEBookResponse;
        }

    }
    /**
     * PDF 文件上传
     */
    @ApiOperation("PDF文件上传")
    @PostMapping("/uploadEBookFile")
    public AppleEBookResponse uploadEBookFile(@RequestParam("uploadEBookFile") MultipartFile uploadEBookFile ) throws IOException {
        AppleEBookResponse appleEBookResponse = new AppleEBookResponse();
        //获得项目的类路径
        String path = ResourceUtils.getURL("classpath:").getPath();
        //空文件夹在编译时不会打包进入target中
        File uploadDir = new File(path+"/static/file/appleEbook");
        if (!uploadDir.exists()) {
            System.out.println("上传图片路径不存在，正在创建...");
            uploadDir.mkdirs();
            System.out.println(uploadDir.getPath());
        }
        if ( uploadEBookFile != null) {
            //获得上传文件的文件名
            String oldName = uploadEBookFile.getOriginalFilename();
            System.out.println("[上传的文件名：" + oldName);
            File avatar = new File(path + "/static/file/appleEbook/" , oldName);
            try {
                //保存图片
                uploadEBookFile.transferTo(avatar);
                //返回成功结果，附带文件的相对路径
                //http://localhost:8888/bookManagement/img/ad/WechatIMG33.jpg
                appleEBookResponse.setCode(20000);
                appleEBookResponse.setMessage("返回 date 为 上传图片文件的相对路径");
                appleEBookResponse.setFileName("/file/appleEbook/"+oldName);
                appleEBookResponse.seteBookRealFileName(oldName);
                return appleEBookResponse;
            }catch (IOException e) {
                e.printStackTrace();
                appleEBookResponse.setCode(888888);
                appleEBookResponse.setMessage("上传失败");
                return appleEBookResponse;
            }
        }else {
            System.out.println("上传的文件为空");
            appleEBookResponse.setCode(888888);
            appleEBookResponse.setMessage("文件传输错误");
            return appleEBookResponse;
        }

    }

}
