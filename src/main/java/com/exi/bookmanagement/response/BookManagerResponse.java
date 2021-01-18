package com.exi.bookmanagement.response;

import com.exi.bookmanagement.entity.BookManager;
import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;

/**
 * Copyright: Copyright (c) 2021 Asiainfo
 *
 * @ClassName: com.exi.bookmanagement.response.BookManagerResponse
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: Fengsx
 * @date: 2021/1/18 17:31
 * <p>
 * Modification History:
 * Date       Author    Version    Description
 * ----------------------------------------------------------
 * 2021/1/18    Fengsx     v1.0.0      修改原因
 */
public class BookManagerResponse implements Serializable {
    private Integer code;
    private String message;
    private Integer result;
    private BookManager bookManager;
    private List<BookManager> bookManagerList;
    private PageInfo pageInfo;
    private String token;

    @Override
    public String toString() {
        return "BookManagerResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", result=" + result +
                ", bookManager=" + bookManager +
                ", bookManagerList=" + bookManagerList +
                ", pageInfo=" + pageInfo +
                ", token='" + token + '\'' +
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

    public BookManager getBookManager() {
        return bookManager;
    }

    public void setBookManager(BookManager bookManager) {
        this.bookManager = bookManager;
    }

    public List<BookManager> getBookManagerList() {
        return bookManagerList;
    }

    public void setBookManagerList(List<BookManager> bookManagerList) {
        this.bookManagerList = bookManagerList;
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
}
