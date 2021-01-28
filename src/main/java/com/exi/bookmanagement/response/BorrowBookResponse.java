package com.exi.bookmanagement.response;

import com.exi.bookmanagement.entity.Book;
import com.exi.bookmanagement.entity.BorrowBook;
import com.exi.bookmanagement.entity.BorrowBookHis;
import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;

/**
 * Copyright: Copyright (c) 2021 Asiainfo
 *
 * @ClassName: com.exi.bookmanagement.response.BorrowBookResponse
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: Fengsx
 * @date: 2021/1/28 15:16
 * <p>
 * Modification History:
 * Date       Author    Version    Description
 * ----------------------------------------------------------
 * 2021/1/28    Fengsx     v1.0.0      修改原因
 */
public class BorrowBookResponse implements Serializable {
    private Integer code;
    private String message;
    private Integer result;
    private long bookId;
    private long readerId;
    private long borBookId;
    private List<BorrowBook> borrowBookList;
    private BorrowBook borrowBook;
    private BorrowBookHis borrowBookHis;
    private Book book;
    private PageInfo pageInfo;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public BorrowBook getBorrowBook() {
        return borrowBook;
    }

    public void setBorrowBook(BorrowBook borrowBook) {
        this.borrowBook = borrowBook;
    }

    public BorrowBookHis getBorrowBookHis() {
        return borrowBookHis;
    }

    public void setBorrowBookHis(BorrowBookHis borrowBookHis) {
        this.borrowBookHis = borrowBookHis;
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

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public long getReaderId() {
        return readerId;
    }

    public void setReaderId(long readerId) {
        this.readerId = readerId;
    }

    public long getBorBookId() {
        return borBookId;
    }

    public void setBorBookId(long borBookId) {
        this.borBookId = borBookId;
    }

    public List<BorrowBook> getBorrowBookList() {
        return borrowBookList;
    }

    public void setBorrowBookList(List<BorrowBook> borrowBookList) {
        this.borrowBookList = borrowBookList;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }
}
