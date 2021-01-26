package com.exi.bookmanagement.response;

import com.exi.bookmanagement.entity.Book;
import com.exi.bookmanagement.entity.Category;
import com.exi.bookmanagement.entity.EBook;
import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;

/**
 * Copyright: Copyright (c) 2021 Asiainfo
 *
 * @ClassName: com.exi.bookmanagement.response.CategoryResponse
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: Fengsx
 * @date: 2021/1/21 11:31
 * <p>
 * Modification History:
 * Date       Author    Version    Description
 * ----------------------------------------------------------
 * 2021/1/21    Fengsx     v1.0.0      修改原因
 */
public class CategoryResponse implements Serializable {
    private Integer code;
    private String message;
    private Integer result;
    private Category category;
    private List<Category> categoryList;
    private PageInfo pageInfo;
    private String token;
    private List<Book> bookList;
    private List<EBook> eBookList;
    private Long categoryId;

    @Override
    public String toString() {
        return "CategoryResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", result=" + result +
                ", category=" + category +
                ", categoryList=" + categoryList +
                ", pageInfo=" + pageInfo +
                ", token='" + token + '\'' +
                '}';
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public List<EBook> geteBookList() {
        return eBookList;
    }

    public void seteBookList(List<EBook> eBookList) {
        this.eBookList = eBookList;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
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
