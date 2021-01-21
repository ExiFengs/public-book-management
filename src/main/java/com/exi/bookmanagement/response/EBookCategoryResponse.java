package com.exi.bookmanagement.response;

import com.exi.bookmanagement.entity.EBookCategory;
import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;

/**
 * Copyright: Copyright (c) 2021 Asiainfo
 *
 * @ClassName: com.exi.bookmanagement.response.EBookCategoryResponse
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: Fengsx
 * @date: 2021/1/21 14:47
 * <p>
 * Modification History:
 * Date       Author    Version    Description
 * ----------------------------------------------------------
 * 2021/1/21    Fengsx     v1.0.0      修改原因
 */
public class EBookCategoryResponse implements Serializable {
    private Integer code;
    private String message;
    private Integer result;
    private EBookCategory eBookCategory;
    private List<EBookCategory> eBookCategoryList;
    private PageInfo pageInfo;
    private String token;

    @Override
    public String toString() {
        return "EBookCategoryResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", result=" + result +
                ", eBookCategory=" + eBookCategory +
                ", eBookCategoryList=" + eBookCategoryList +
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

    public EBookCategory geteBookCategory() {
        return eBookCategory;
    }

    public void seteBookCategory(EBookCategory eBookCategory) {
        this.eBookCategory = eBookCategory;
    }

    public List<EBookCategory> geteBookCategoryList() {
        return eBookCategoryList;
    }

    public void seteBookCategoryList(List<EBookCategory> eBookCategoryList) {
        this.eBookCategoryList = eBookCategoryList;
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
