package com.exi.bookmanagement.controller;

import com.exi.bookmanagement.entity.Book;
import com.exi.bookmanagement.entity.BorrowBook;
import com.exi.bookmanagement.mapper.BookMapper;
import com.exi.bookmanagement.mapper.BorrowBookHisMapper;
import com.exi.bookmanagement.mapper.BorrowBookMapper;
import com.exi.bookmanagement.response.BookResponse;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Copyright: Copyright (c) 2021 Asiainfo
 *
 * @ClassName: com.exi.bookmanagement.controller.BookController
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: Fengsx
 * @date: 2021/1/20 19:43
 * <p>
 * Modification History:
 * Date       Author    Version    Description
 * ----------------------------------------------------------
 * 2021/1/20    Fengsx     v1.0.0      修改原因
 */
@Api(tags = "纸质图书管理")
@RestController
@CrossOrigin
@Slf4j
@RequestMapping(value = "/book")
public class BookController {
    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BorrowBookMapper borrowBookMapper;

    @Autowired
    private BorrowBookHisMapper borrowBookHisMapper;


    @ApiOperation("分页查询纸质图书信息")
    @GetMapping(value = "/getBooksPage/{pageNum}/{pageSize}")
    public BookResponse getBooksPage(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize){
        Page<Book> pageInfo = PageHelper.startPage(pageNum, pageSize);
        if (pageInfo.getPageNum() == 0 || pageInfo.getPageSize() == 0) {
            log.info("pageNum || pageSize 有值为空");
        }
        //并查询
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        List<Book> bookList = bookMapper.getAllBookBean();
        // 如果在获取到数据之后就对数据进行转dto操作的话，会获取不到total数据，所以又定义了一个PageInfo类然后将数据进行属性复制，来获取数据
        PageInfo<Book> pageInfo1 = new PageInfo<>();
        BeanUtils.copyProperties(new PageInfo<>(bookList), pageInfo1);
        log.info("封装后的 pageInfo:{}",pageInfo1);
        // 定义一个 response 把状态码和 message 加到 response 里面，不然前台会拒绝请求
        BookResponse bookResponse = new BookResponse();
        bookResponse.setCode(20000);
        bookResponse.setMessage("返回 date 为 bookList 的分页List");
        bookResponse.setPageInfo(pageInfo1);
        return bookResponse;
    }

    @ApiOperation("按名称查询纸质图书信息")
    @GetMapping(value = "/getBookLikeNameList/{bookName}")
    public BookResponse getBookLikeNameList(@PathVariable("bookName") String bookName){
        List<Book> bookList = bookMapper.getBookLikeNameList(bookName);
        log.info("bookList: {}",bookList);
        BookResponse bookResponse = new BookResponse();
        bookResponse.setCode(20000);
        bookResponse.setMessage("返回 date 为 bookList 的按名字模糊查询");
        bookResponse.setBookList(bookList);
        return bookResponse;
    }

    @ApiOperation("获取所有纸质图书信息")
    @GetMapping("/getBooks")
    public BookResponse getBooks() {
        List<Book> bookList=bookMapper.getAllBookBean();
        BookResponse bookResponse = new BookResponse();
        bookResponse.setBookList(bookList);
        bookResponse.setCode(20000);
        bookResponse.setMessage("返回 date 为 bookList 全部数据");
        return bookResponse;
    }

    @ApiOperation("按id查询纸质图书信息")
    @GetMapping(value = "/getBookById/{bookId}")
    public BookResponse getBookById(@PathVariable("bookId") Long bookId) {
        Book book =bookMapper.getOneBookBeanById(bookId);
        BookResponse bookResponse = new BookResponse();
        bookResponse.setBook(book);
        bookResponse.setCode(20000);
        bookResponse.setMessage("返回 date 为 book 的按id查询");
        return bookResponse;
    }

    @ApiOperation("添加纸质图书信息")
    @PostMapping("/addBook")
    public BookResponse save(@RequestBody Book book){
        BookResponse bookResponse = new BookResponse();
        try {
            //返回的 id 总为 1 ,result 影响条数, 代码是返回自增主键的？
            int id = bookMapper.insertBookBean(book);
            bookResponse.setBook(book);
            bookResponse.setResult(id);
            bookResponse.setCode(20000);
            bookResponse.setMessage("返回 date 为 bookResponse");
            if(id != 0){
                return bookResponse;
            }else{
                return null;
            }
        }catch (Exception e){
            log.info("添加纸质图书信息出问题啦");
        }
        return bookResponse;
    }

    @ApiOperation("更新纸质图书信息")
    @PutMapping(value="/updateBook")
    public BookResponse update(@RequestBody Book book) {
        BookResponse bookResponse = new BookResponse();
        try {
            int result = bookMapper.updateBookBean(book);
            log.info("更新后的book:{}",book);
            bookResponse.setResult(result);
            bookResponse.setBook(book);
            bookResponse.setCode(20000);
            bookResponse.setMessage("返回 date 为 updateBookResponse");
            System.out.println("======" + result);
            if(result != 0){
                return bookResponse;
            }else{
                return null;
            }
        }catch (Exception e){
            log.info("更新纸质图书信息出问题啦");
        }
        return bookResponse;
    }

    @ApiOperation("删除纸质图书信息")
    @DeleteMapping(value="/deleteBook/{id}")
    public BookResponse delete(@PathVariable("id") Long id) {
        BookResponse bookResponse = new BookResponse();
        List<BorrowBook> borrowBookBeanByBookId = borrowBookMapper.getBorrowBookBeanByBookId(id);
        if (!CollectionUtils.isEmpty(borrowBookBeanByBookId)){
            bookResponse.setCode(88888);
            bookResponse.setMessage("还有读者借阅了这本书，暂时删除不了！");
            return bookResponse;
        }
        try {
            int result = bookMapper.deleteBookBean(id);
            bookResponse.setResult(result);
            bookResponse.setCode(20000);
            bookResponse.setMessage("返回 date 为 deleteBookBeanResponse");
            if (result != 0){
                return bookResponse;
            }else {
                return null;
            }
        }catch (Exception e){
            log.info("删除纸质图书信息出问题啦");
        }
        return bookResponse;
    }

    /**
     * 图片上传
     */
    @ApiOperation("图片上传")
    @PostMapping("/uploadBookImg")
    public BookResponse uploadBookImg(@RequestParam("uploadFile") MultipartFile uploadFile ) throws IOException {
        BookResponse bookResponse = new BookResponse();
        //获得项目的类路径
        String path = ResourceUtils.getURL("classpath:").getPath();
        //空文件夹在编译时不会打包进入target中
        File uploadDir = new File(path+"/static/img/book");
        if (!uploadDir.exists()) {
            System.out.println("上传图片路径不存在，正在创建...");
            uploadDir.mkdirs();
            System.out.println(uploadDir.getPath());
        }
        if ( uploadFile != null) {
            //获得上传文件的文件名
            String oldName = uploadFile.getOriginalFilename();
            System.out.println("[上传的文件名]：" + oldName);
            File avatar = new File(path + "/static/img/book/" , oldName);
            try {
                //保存图片
                uploadFile.transferTo(avatar);
                //返回成功结果，附带文件的相对路径
                //http://localhost:8888/bookManagement/img/ad/WechatIMG33.jpg
                bookResponse.setCode(20000);
                bookResponse.setMessage("返回 date 为 上传图片文件的相对路径");
                bookResponse.setFileName("/img/book/"+oldName);
                return bookResponse;
            }catch (IOException e) {
                e.printStackTrace();
                bookResponse.setCode(888888);
                bookResponse.setMessage("上传失败");
                return bookResponse;
            }
        }else {
            System.out.println("上传的文件为空");
            bookResponse.setCode(888888);
            bookResponse.setMessage("文件传输错误");
            return bookResponse;
        }

    }
}
