package com.exi.bookmanagement.entity;


import java.io.Serializable;
import java.sql.Date;

public class Reader implements Serializable {

  private long readerId;
  private String readerName;
  private String readerAccount;
  private String readerPassword;
  private String registrationTime;
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


  public String getRegistrationTime() {
    return registrationTime;
  }

  public void setRegistrationTime(String registrationTime) {
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
