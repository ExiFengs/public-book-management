package com.exi.bookmanagement.bean;

import java.io.Serializable;

/**
 *  Copyright: Copyright (c) 2021 Asiainfo
 *
 *  @ClassName: com.exi.bookmanagement.bean.ReaderBean
 *  @Description: 该类的功能描述
 * <p>
 *  @version: v1.0.0
 *  @author:   Fengsx
 *  @date: 1/12/21 6:59 PM
 * <p>
 *  Modification History:
 *  Date       Author    Version    Description
 * ----------------------------------------------------------
 *  1/12/21    Fengsx     v1.0.0      修改原因
 */


public class ReaderBean implements Serializable {

    private long readerId;
    private String readerName;
    private String readerAccount;
    private String readerPassword;
    private java.sql.Timestamp registrationTime;
    private String readerSex;
    private long roleId;


    @Override
    public String toString() {
        return "Reader{" +
                "readerId=" + readerId +
                ", readerName='" + readerName + '\'' +
                ", readerAccount='" + readerAccount + '\'' +
                ", readerPassword='" + readerPassword + '\'' +
                ", registrationTime=" + registrationTime +
                ", readerSex='" + readerSex + '\'' +
                ", roleId=" + roleId +
                '}';
    }

    public long getReaderId() {
        return readerId;
    }

    public void setReaderId(long readerId) {
        this.readerId = readerId;
    }


    public String getReaderName() {
        return readerName;
    }

    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }


    public String getReaderAccount() {
        return readerAccount;
    }

    public void setReaderAccount(String readerAccount) {
        this.readerAccount = readerAccount;
    }


    public String getReaderPassword() {
        return readerPassword;
    }

    public void setReaderPassword(String readerPassword) {
        this.readerPassword = readerPassword;
    }


    public java.sql.Timestamp getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(java.sql.Timestamp registrationTime) {
        this.registrationTime = registrationTime;
    }


    public String getReaderSex() {
        return readerSex;
    }

    public void setReaderSex(String readerSex) {
        this.readerSex = readerSex;
    }


    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

}
