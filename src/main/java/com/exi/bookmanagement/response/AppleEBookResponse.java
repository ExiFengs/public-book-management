package com.exi.bookmanagement.response;

import com.exi.bookmanagement.entity.AppleEBook;
import com.exi.bookmanagement.entity.EBook;
import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;

/**
 * Copyright: Copyright (c) 2021 Asiainfo
 *
 * @ClassName: com.exi.bookmanagement.response.AppleEBookResponse
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: Fengsx
 * @date: 2021/2/2 16:27
 * <p>
 * Modification History:
 * Date       Author    Version    Description
 * ----------------------------------------------------------
 * 2021/2/2    Fengsx     v1.0.0      修改原因
 */
public class AppleEBookResponse implements Serializable {
    private Integer code;
    private String message;
    private Integer result;
    private EBook eBook;
    private AppleEBook appleEBook;
    private List<AppleEBook> appleEBookList;
    private PageInfo pageInfo;
    private String fileName;
    private String eBookFileName;
    private String eBookRealFileName;

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

    public EBook geteBook() {
        return eBook;
    }

    public void seteBook(EBook eBook) {
        this.eBook = eBook;
    }

    public AppleEBook getAppleEBook() {
        return appleEBook;
    }

    public void setAppleEBook(AppleEBook appleEBook) {
        this.appleEBook = appleEBook;
    }

    public List<AppleEBook> getAppleEBookList() {
        return appleEBookList;
    }

    public void setAppleEBookList(List<AppleEBook> appleEBookList) {
        this.appleEBookList = appleEBookList;
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

    public String geteBookFileName() {
        return eBookFileName;
    }

    public void seteBookFileName(String eBookFileName) {
        this.eBookFileName = eBookFileName;
    }

    public String geteBookRealFileName() {
        return eBookRealFileName;
    }

    public void seteBookRealFileName(String eBookRealFileName) {
        this.eBookRealFileName = eBookRealFileName;
    }
}
