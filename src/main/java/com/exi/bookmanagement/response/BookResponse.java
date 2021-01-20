package com.exi.bookmanagement.response;

import com.exi.bookmanagement.entity.Book;
import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;

/**
 * Copyright: Copyright (c) 2021 Asiainfo
 *
 * @ClassName: com.exi.bookmanagement.response.BookResponse
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: Fengsx
 * @date: 2021/1/20 19:47
 * <p>
 * Modification History:
 * Date       Author    Version    Description
 * ----------------------------------------------------------
 * 2021/1/20    Fengsx     v1.0.0      修改原因
 */
public class BookResponse implements Serializable {
    private Integer code;
    private String message;
    private Integer result;
    private Book book;
    private List<Book> bookList;
    private PageInfo pageInfo;
    private String token;
    private String fileName;

    @Override
    public String toString() {
        return "BookResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", result=" + result +
                ", book=" + book +
                ", bookList=" + bookList +
                ", pageInfo=" + pageInfo +
                ", token='" + token + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
