package com.exi.bookmanagement.mapper;

import com.exi.bookmanagement.entity.BorrowBook;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Copyright: Copyright (c) 2021 Asiainfo
 *
 * @ClassName: com.exi.bookmanagement.mapper.BorrowBookMapper
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: Fengsx
 * @date: 2021/1/28 15:16
 * <p>
 * Modification History:
 * Date       Author    Version    Description
 * ----------------------------------------------------------
 * 2021/1/28    Fengsx     v1.0.0      修改原因
 */
@Transactional
public interface BorrowBookMapper {

    @Select("SELECT * FROM borrow_book")
    @Results(id = "borrowBookMap", value = {
            @Result(property = "borBookId",  column = "bor_book_id"),
            @Result(property = "readerId",  column = "reader_id"),
            @Result(property = "bookId", column = "book_id"),
    })
    List<BorrowBook> getAllBorrowBookBean();

    @Insert("INSERT INTO borrow_book(book_id, reader_id) " +
            "VALUES(#{bookId}, #{readerId})")
    @Options(useGeneratedKeys = true, keyProperty = "borBookId", keyColumn = "bor_book_id")
    void insertBorrowBookBean(BorrowBook borrowBook);

    /**
     * 根据读者查询对应的电子书阅读记录
     **/
    @Select("SELECT * FROM borrow_book WHERE reader_id = #{readerId}")
    @Results(value = {
            @Result(property = "borBookId",  column = "bor_book_id"),
            @Result(property = "readerId",  column = "reader_id"),
            @Result(property = "bookId", column = "book_id"),
            @Result(column="book_id",property="bookList",javaType=List.class,
                    one=@One(select="com.exi.bookmanagement.mapper.BookMapper.getOneBookBeanById")),
            @Result(column="bor_book_id",property="borrowBookHisList",javaType=List.class,
                    one=@One(select="com.exi.bookmanagement.mapper.BorrowBookHisMapper.getOneBorrowBookHisBean")),
    })
    List<BorrowBook> getBorrowBookListByBookIdAndBorBookId(Long readerId);

    /**
     * 后台查看具体的阅读数据
     **/
    @Select("SELECT * FROM borrow_book ORDER BY borrow_book.reader_id")
    @Results(value = {
            @Result(property = "borBookId",  column = "bor_book_id"),
            @Result(property = "readerId",  column = "reader_id"),
            @Result(property = "bookId", column = "book_id"),
            @Result(column="book_id",property="bookList",javaType=List.class,
                    one=@One(select="com.exi.bookmanagement.mapper.BookMapper.getOneBookBeanById")),
            @Result(column="bor_book_id",property="borrowBookHisList",javaType=List.class,
                    one=@One(select="com.exi.bookmanagement.mapper.BorrowBookHisMapper.getOneBorrowBookHisBean")),
            @Result(column="reader_id",property="readerList",javaType=List.class,
                    one=@One(select="com.exi.bookmanagement.mapper.ReaderMapper.getOneReaderBean")),
    })
    List<BorrowBook> getAllBorrowBookByBookIdAndBorBookIdAndReaderId();

    @Select("SELECT * FROM borrow_book WHERE reader_id = #{readerId} AND book_id = #{bookId}")
    @ResultMap(value = "borrowBookMap")
    BorrowBook getBorrowBookBeanByReaderIdAndBookId(Long readerId, Long bookId);

    /**
     * 根据主键查找 bookId
     **/
    @Select("SELECT book_id FROM borrow_book WHERE bor_book_id = #{borBookId}")
    @Results(value = {
            @Result(property = "bookId", column = "book_id"),})
    Long getBookByBorBookId(Long borBookId);

}
