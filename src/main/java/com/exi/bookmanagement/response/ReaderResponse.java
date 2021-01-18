package com.exi.bookmanagement.response;

import com.exi.bookmanagement.entity.Reader;
import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;

/**
 *  Copyright: Copyright (c) 2021 Asiainfo
 *
 *  @ClassName: com.exi.bookmanagement.response.ReaderResponse
 *  @Description: 该类的功能描述
 * <p>
 *  @version: v1.0.0
 *  @author:   Fengsx
 *  @date: 1/13/21 7:09 PM
 * <p>
 *  Modification History:
 *  Date       Author    Version    Description
 * ----------------------------------------------------------
 *  1/13/21    Fengsx     v1.0.0      修改原因
 */
public class ReaderResponse implements Serializable {
    private Integer code;
    private String message;
    private Integer result;
    private Reader reader;
    private List<Reader> readerList;
    private PageInfo pageInfo;
    private String token;



    @Override
    public String toString() {
        return "ReaderResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", reader=" + reader +
                ", pageInfo=" + pageInfo +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Reader> getReaderList() {
        return readerList;
    }

    public void setReaderList(List<Reader> readerList) {
        this.readerList = readerList;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
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

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }
}
