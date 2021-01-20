package com.exi.bookmanagement.response;

import com.exi.bookmanagement.entity.EBook;
import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;

/**
 * Copyright: Copyright (c) 2021 Asiainfo
 *
 * @ClassName: com.exi.bookmanagement.response.EBookResponse
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: Fengsx
 * @date: 2021/1/20 20:30
 * <p>
 * Modification History:
 * Date       Author    Version    Description
 * ----------------------------------------------------------
 * 2021/1/20    Fengsx     v1.0.0      修改原因
 */
public class EBookResponse implements Serializable {
    private Integer code;
    private String message;
    private Integer result;
    private EBook eBook;
    private List<EBook> eBookList;
    private PageInfo pageInfo;
    private String token;
    private String fileName;
    private String eBookFileName;


    @Override
    public String toString() {
        return "EBookResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", result=" + result +
                ", eBook=" + eBook +
                ", eBookList=" + eBookList +
                ", pageInfo=" + pageInfo +
                ", token='" + token + '\'' +
                ", fileName='" + fileName + '\'' +
                ", eBookFileName='" + eBookFileName + '\'' +
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

    public EBook geteBook() {
        return eBook;
    }

    public void seteBook(EBook eBook) {
        this.eBook = eBook;
    }

    public List<EBook> geteBookList() {
        return eBookList;
    }

    public void seteBookList(List<EBook> eBookList) {
        this.eBookList = eBookList;
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

    public String geteBookFileName() {
        return eBookFileName;
    }

    public void seteBookFileName(String eBookFileName) {
        this.eBookFileName = eBookFileName;
    }
}
