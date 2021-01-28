package com.exi.bookmanagement.response;

import com.exi.bookmanagement.entity.ReadBook;
import com.exi.bookmanagement.entity.ReadBookHis;
import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;

/**
 * Copyright: Copyright (c) 2021 Asiainfo
 *
 * @ClassName: com.exi.bookmanagement.response.readBookResponse
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: Fengsx
 * @date: 2021/1/27 14:35
 * <p>
 * Modification History:
 * Date       Author    Version    Description
 * ----------------------------------------------------------
 * 2021/1/27    Fengsx     v1.0.0      修改原因
 */
public class ReadBookResponse implements Serializable {
    /**
     * 返回读者在线阅读的记录
     **/
    private Integer code;
    private String message;
    private Integer result;
    private long eBookId;
    private long readerId;
    private long readId;
    private long readNum;
    private String eBookName;
    private String readerName;
    private ReadBook readBook;
    private ReadBookHis readBookHis;
    private List<ReadBook> readBookList;
    private PageInfo pageInfo;
    private String token;
    private String fileName;
    private String eBookFileName;
    private String eBookRealFileName;
    private List<String> fileNames;


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

    public String geteBookRealFileName() {
        return eBookRealFileName;
    }

    public void seteBookRealFileName(String eBookRealFileName) {
        this.eBookRealFileName = eBookRealFileName;
    }

    public List<String> getFileNames() {
        return fileNames;
    }

    public void setFileNames(List<String> fileNames) {
        this.fileNames = fileNames;
    }

    public List<ReadBook> getReadBookList() {
        return readBookList;
    }

    public void setReadBookList(List<ReadBook> readBookList) {
        this.readBookList = readBookList;
    }

    public ReadBook getReadBook() {
        return readBook;
    }

    public void setReadBook(ReadBook readBook) {
        this.readBook = readBook;
    }

    public ReadBookHis getReadBookHis() {
        return readBookHis;
    }

    public void setReadBookHis(ReadBookHis readBookHis) {
        this.readBookHis = readBookHis;
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

    public long geteBookId() {
        return eBookId;
    }

    public void seteBookId(long eBookId) {
        this.eBookId = eBookId;
    }

    public long getReaderId() {
        return readerId;
    }

    public void setReaderId(long readerId) {
        this.readerId = readerId;
    }

    public long getReadId() {
        return readId;
    }

    public void setReadId(long readId) {
        this.readId = readId;
    }

    public long getReadNum() {
        return readNum;
    }

    public void setReadNum(long readNum) {
        this.readNum = readNum;
    }

    public String geteBookName() {
        return eBookName;
    }

    public void seteBookName(String eBookName) {
        this.eBookName = eBookName;
    }

    public String getReaderName() {
        return readerName;
    }

    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }
}
