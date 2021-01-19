package com.exi.bookmanagement.response;

import com.exi.bookmanagement.entity.Advertisement;
import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;

/**
 * Copyright: Copyright (c) 2021 Asiainfo
 *
 * @ClassName: com.exi.bookmanagement.response.AdResponse
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: Fengsx
 * @date: 2021/1/19 14:40
 * <p>
 * Modification History:
 * Date       Author    Version    Description
 * ----------------------------------------------------------
 * 2021/1/19    Fengsx     v1.0.0      修改原因
 */
public class AdResponse implements Serializable {
    private Integer code;
    private String message;
    private Integer result;
    private Advertisement advertisement;
    private List<Advertisement> advertisementList;
    private PageInfo pageInfo;
    private String token;
    private String fileName;

    @Override
    public String toString() {
        return "AdResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", result=" + result +
                ", advertisement=" + advertisement +
                ", advertisementList=" + advertisementList +
                ", pageInfo=" + pageInfo +
                ", token='" + token + '\'' +
                '}';
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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

    public Advertisement getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
    }

    public List<Advertisement> getAdvertisementList() {
        return advertisementList;
    }

    public void setAdvertisementList(List<Advertisement> advertisementList) {
        this.advertisementList = advertisementList;
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
