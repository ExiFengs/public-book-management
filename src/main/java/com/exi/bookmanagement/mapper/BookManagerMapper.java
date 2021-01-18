package com.exi.bookmanagement.mapper;

import com.exi.bookmanagement.entity.BookManager;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Copyright: Copyright (c) 2021 Asiainfo
 *
 * @ClassName: com.exi.bookmanagement.mapper.BookManagerMapper
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: Fengsx
 * @date: 2021/1/18 16:44
 * <p>
 * Modification History:
 * Date       Author    Version    Description
 * ----------------------------------------------------------
 * 2021/1/18    Fengsx     v1.0.0      修改原因
 */
public interface BookManagerMapper {
    @Select("SELECT * FROM book_manager")
    @Results(id = "bookManagerMap", value = {
            @Result(property = "bookManagerId",  column = "book_manager_id"),
            @Result(property = "readerName", column = "reader_name"),
            @Result(property = "readerAccount", column = "reader_account"),
            @Result(property = "readerPassword", column = "reader_password"),
            @Result(property = "roleId", column = "role_id")
    })
    List<BookManager> getAllBookManagerBean();

    @Select("SELECT * FROM book_manager WHERE book_manager_id = #{bookManagerId}")
    @ResultMap(value = "bookManagerMap")
    BookManager getOneBookManagerBeanById(Long bookManagerId);

    /**
     * 登录的时候入参要为 int，不然框架不识别
     **/
    @Select("SELECT * FROM book_manager WHERE book_manager_id = #{bookManagerId}")
    @ResultMap(value = "bookManagerMap")
    BookManager getTokenForBookManagerId(Integer bookManagerId);

    @Insert("INSERT INTO book_manager(reader_name,reader_account,reader_password,role_id) " +
            "VALUES(#{readerName}, #{readerAccount}, #{readerPassword}, #{roleId})")
    @Options(useGeneratedKeys = true, keyProperty = "book_manager_id")
    int insertBookManagerBean(BookManager bookManager);

    @Update("UPDATE book_manager SET reader_name=#{readerName},reader_account=#{readerAccount}," +
            "reader_password=#{readerPassword} WHERE book_manager_id =#{bookManagerId}")
    int updateBookManagerBean(BookManager bookManager);

    @Delete("DELETE FROM book_manager WHERE book_manager_id =#{bookManagerId}")
    int deleteBookManagerBean(Long bookManagerId);

    @Select("SELECT * FROM book_manager WHERE book_manager.reader_name LIKE CONCAT('%',#{readerName},'%')")
    @ResultMap(value = "bookManagerMap")
    List<BookManager> getBookManagerLikeNameList(String readerName);

    @Select("SELECT * FROM book_manager WHERE book_manager.reader_account = #{readerAccount} AND book_manager.reader_password = #{readerPassword}")
    @ResultMap(value = "bookManagerMap")
    BookManager selectBookManagerAccountAndPassword(String readerAccount, String readerPassword);
}
