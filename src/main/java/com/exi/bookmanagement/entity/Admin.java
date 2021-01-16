package com.exi.bookmanagement.entity;


import java.io.Serializable;

public class Admin implements Serializable {

  private long adminId;
  private long roleId;
  private String adminName;
  private String adminAccount;
  private String adminPassword;


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


  public String getAdminName() {
    return adminName;
  }

  public void setAdminName(String adminName) {
    this.adminName = adminName;
  }


  public String getAdminAccount() {
    return adminAccount;
  }

  public void setAdminAccount(String adminAccount) {
    this.adminAccount = adminAccount;
  }


  public String getAdminPassword() {
    return adminPassword;
  }

  public void setAdminPassword(String adminPassword) {
    this.adminPassword = adminPassword;
  }

}
