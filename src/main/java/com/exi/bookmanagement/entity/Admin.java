package com.exi.bookmanagement.entity;


import java.io.Serializable;

public class Admin implements Serializable {

  private long adminId;
  private long roleId;
  private String readerName;
  private String readerAccount;
  private String readerPassword;

  @Override
  public String toString() {
    return "Admin{" +
            "adminId=" + adminId +
            ", roleId=" + roleId +
            ", readerName='" + readerName + '\'' +
            ", readerAccount='" + readerAccount + '\'' +
            ", readerPassword='" + readerPassword + '\'' +
            '}';
  }

  public long getAdminId() {
    return adminId;
  }

  public void setAdminId(long adminId) {
    this.adminId = adminId;
  }


  public long getRoleId() {
    return roleId;
  }

  public void setRoleId(long roleId) {
    this.roleId = roleId;
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

}
