package com.exi.bookmanagement.mapper;

import com.exi.bookmanagement.entity.Admin;
import com.exi.bookmanagement.entity.BookManager;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Copyright: Copyright (c) 2021 Asiainfo
 *
 * @ClassName: com.exi.bookmanagement.mapper.AdminMapper
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: Fengsx
 * @date: 2021/2/3 16:27
 * <p>
 * Modification History:
 * Date       Author    Version    Description
 * ----------------------------------------------------------
 * 2021/2/3    Fengsx     v1.0.0      修改原因
 */
@CacheNamespace(blocking = true)
public interface AdminMapper {
    @Select("SELECT * FROM admin")
    @Results(id = "adminMap", value = {
            @Result(property = "adminId",  column = "admin_Id"),
            @Result(property = "readerName", column = "reader_name"),
            @Result(property = "readerAccount", column = "reader_account"),
            @Result(property = "readerPassword", column = "reader_password"),
            @Result(property = "roleId", column = "role_id")
    })
    List<Admin> getAllAdminBean();

    @Select("SELECT * FROM admin WHERE admin.reader_account = #{readerAccount} AND admin.reader_password = #{readerPassword}")
    @ResultMap(value = "adminMap")
    Admin selectAdminAccountAndPassword(String readerAccount, String readerPassword);
}
