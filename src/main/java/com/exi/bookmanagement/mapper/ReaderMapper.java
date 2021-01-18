package com.exi.bookmanagement.mapper;

import com.exi.bookmanagement.entity.Reader;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 *  Copyright: Copyright (c) 2021 Asiainfo
 *
 *  @ClassName: com.exi.bookmanagement.dao.ReaderMapper
 *  @Description: 该类的功能描述
 * <p>
 *  @version: v1.0.0
 *  @author:   Fengsx
 *  @date: 1/8/21 3:43 PM
 * <p>
 *  Modification History:
 *  Date       Author    Version    Description
 * ----------------------------------------------------------
 *  1/8/21    Fengsx     v1.0.0      修改原因
 */

public interface ReaderMapper {

    @Select("SELECT * FROM reader")
    @Results(id = "readerMap", value = {
            @Result(property = "readerId",  column = "reader_id"),
            @Result(property = "readerName", column = "reader_name"),
            @Result(property = "readerAccount", column = "reader_account"),
            @Result(property = "readerPassword", column = "reader_password"),
            @Result(property = "registrationTime", column = "registration_time"),
            @Result(property = "readerSex", column = "reader_sex"),
            @Result(property = "roleId", column = "role_id")
    })
    List<Reader> getAllReaderBean();

    @Select("SELECT * FROM reader WHERE reader_id = #{readerId}")
    @ResultMap(value = "readerMap")
    Reader getOneReaderBean(Long readerId);

    @Select("SELECT * FROM reader WHERE reader_id = #{readerId}")
    @ResultMap(value = "readerMap")
    Reader getTokenForReaderId(Integer readerId);

    @Insert("INSERT INTO reader(reader_name,reader_account,reader_password,registration_time,reader_sex,role_id) " +
            "VALUES(#{readerName}, #{readerAccount}, #{readerPassword}, #{registrationTime}, #{readerSex}, #{roleId})")
    @Options(useGeneratedKeys = true, keyProperty = "reader_id")
    int insertReaderBean(Reader reader);

    @Update("UPDATE reader SET reader_name=#{readerName},reader_account=#{readerAccount}," +
            "reader_password=#{readerPassword},reader_sex=#{readerSex} WHERE reader_id =#{readerId}")
    int updateReaderBean(Reader reader);

    @Delete("DELETE FROM reader WHERE reader_id =#{readerId}")
    int deleteReaderBean(Long readerId);

    @Select("SELECT * FROM reader WHERE reader.reader_name LIKE CONCAT('%',#{readerName},'%')")
    @ResultMap(value = "readerMap")
    List<Reader> getReaderLikeNameList(String readerName);

    @Select("SELECT * FROM reader WHERE reader.reader_account = #{readerAccount} AND reader.reader_password = #{readerPassword}")
    @ResultMap(value = "readerMap")
    Reader selectReaderAccountAndPassword(String readerAccount, String readerPassword);
}
