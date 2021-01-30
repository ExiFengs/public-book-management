package com.exi.bookmanagement.mapper;

import com.exi.bookmanagement.entity.ReadBook;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Copyright: Copyright (c) 2021 Asiainfo
 *
 * @ClassName: com.exi.bookmanagement.mapper.ReadBookMapper
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: Fengsx
 * @date: 2021/1/27 11:02
 * <p>
 * Modification History:
 * Date       Author    Version    Description
 * ----------------------------------------------------------
 * 2021/1/27    Fengsx     v1.0.0      修改原因
 */
public interface ReadBookMapper {

    @Select("SELECT * FROM read_book")
    @Results(id = "readBookMap", value = {
            @Result(property = "readId",  column = "read_id"),
            @Result(property = "readerId",  column = "reader_id"),
            @Result(property = "eBookId", column = "e_book_id"),
    })
    List<ReadBook> getAllReadBookBean();

    @Insert("INSERT INTO read_book(reader_id, e_book_id) " +
            "VALUES(#{readerId}, #{eBookId} )")
    @Options(useGeneratedKeys = true, keyProperty = "readId", keyColumn = "read_id")
    void insertReadBookBean(ReadBook readBook);

    /**
     * 根据读者查询对应的电子书阅读记录
     **/
    @Select("SELECT * FROM read_book WHERE reader_id = #{readerId}")
    @Results(value = {
            @Result(property = "readId",  column = "read_id"),
            @Result(property = "readerId",  column = "reader_id"),
            @Result(property = "eBookId", column = "e_book_id"),
            @Result(column="e_book_id",property="eBookList",javaType=List.class,
                    one=@One(select="com.exi.bookmanagement.mapper.EBookMapper.getOneEBookBeanById")),
            @Result(column="read_id",property="readBookHisList",javaType=List.class,
                    one=@One(select="com.exi.bookmanagement.mapper.ReadBookHisMapper.getOneReadBookHisBean")),
    })
    List<ReadBook> getReadBookListByEbookIdAndReadId(Long readerId);

    /**
     * 后台查看具体的阅读数据
     **/
    @Select("SELECT * FROM read_book ORDER BY read_book.reader_id")
    @Results(value = {
            @Result(property = "readId",  column = "read_id"),
            @Result(property = "readerId",  column = "reader_id"),
            @Result(property = "eBookId", column = "e_book_id"),
            @Result(column="e_book_id",property="eBookList",javaType= List.class,
                    one=@One(select="com.exi.bookmanagement.mapper.EBookMapper.getOneEBookBeanById")),
            @Result(column="read_id",property="readBookHisList",javaType=List.class,
                    one=@One(select="com.exi.bookmanagement.mapper.ReadBookHisMapper.getOneReadBookHisBean")),
            @Result(column="reader_id",property="readerList",javaType=List.class,
                    one=@One(select="com.exi.bookmanagement.mapper.ReaderMapper.getOneReaderBean")),
    })
    List<ReadBook> getAllReadBookByEbookIdAndReadIdAndReaderId();

    @Select("SELECT * FROM read_book WHERE reader_id = #{readerId} AND e_book_id = #{eBookId}")
    @ResultMap(value = "readBookMap")
    ReadBook getReadBookBeanByReaderIdAndEbookId(Long readerId, Long eBookId);

    @Select("SELECT * FROM read_book WHERE reader_id = #{readerId}")
    @ResultMap(value = "readBookMap")
    List<ReadBook> getReadBookBeansByReaderId(Long readerId);

    @Select("SELECT * FROM read_book WHERE e_book_id = #{eBookId}")
    @ResultMap(value = "readBookMap")
    List<ReadBook> getReadBookBeansByEbookId(Long eBookId);

}
