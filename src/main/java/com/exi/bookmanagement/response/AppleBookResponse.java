package com.exi.bookmanagement.response;

import com.exi.bookmanagement.entity.AppleBook;
import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;

/**
 * Copyright: Copyright (c) 2021 Asiainfo
 *
 * @ClassName: com.exi.bookmanagement.response.AppleBookResponse
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: Fengsx
 * @date: 2021/2/2 11:20
 * <p>
 * Modification History:
 * Date       Author    Version    Description
 * ----------------------------------------------------------
 * 2021/2/2    Fengsx     v1.0.0      修改原因
 */
public class AppleBookResponse implements Serializable {
    private Integer code;
    private String message;
    private Integer result;
    private AppleBook appleBook;
    private List<AppleBook> appleBookList;
    private PageInfo pageInfo;
    private String fileName;

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

    public AppleBook getAppleBook() {
        return appleBook;
    }

    public void setAppleBook(AppleBook appleBook) {
        this.appleBook = appleBook;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<AppleBook> getAppleBookList() {
        return appleBookList;
    }

    public void setAppleBookList(List<AppleBook> appleBookList) {
        this.appleBookList = appleBookList;
    }
}
