package com.exi.bookmanagement.mapper;

import com.exi.bookmanagement.entity.AppleBook;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Copyright: Copyright (c) 2021 Asiainfo
 *
 * @ClassName: com.exi.bookmanagement.mapper.AppleBookMapper
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: Fengsx
 * @date: 2021/2/1 17:52
 * <p>
 * Modification History:
 * Date       Author    Version    Description
 * ----------------------------------------------------------
 * 2021/2/1    Fengsx     v1.0.0      修改原因
 */
public interface AppleBookMapper {
    @Select("SELECT * FROM apple_book")
    @Results(id = "bookMap", value = {
            @Result(property = "bookId",  column = "book_id"),
            @Result(property = "bookAuthor", column = "book_author"),
            @Result(property = "bookName", column = "book_name"),
            @Result(property = "bookRepertory", column = "book_repertory"),
            @Result(property = "bookPicture", column = "book_picture"),
            @Result(property = "bookIsbn", column = "book_isbn"),
            @Result(property = "bookIntro", column = "book_intro"),
            @Result(property = "bookPress", column = "book_press"),
            @Result(property = "categoryId", column = "category_id"),
            @Result(property = "state", column = "state"),
            @Result(property = "readerId", column = "reader_id"),
            //这里的 column 是映射回分类表的主键
            @Result(column="category_id",property="category",
                    one=@One(select="com.exi.bookmanagement.mapper.CategoryMapper.getOneCategoryById")),
            @Result(column="reader_id",property="reader",
                    one=@One(select="com.exi.bookmanagement.mapper.ReaderMapper.getOneReaderBean"))
    })
    List<AppleBook> getAllBookBean();

    @Select("SELECT * FROM apple_book WHERE reader_id = #{readerId}")
    @ResultMap(value = "bookMap")
    List<AppleBook> getBookBeanById(Long readerId);


    @Select("SELECT * FROM apple_book WHERE book_id = #{bookId}")
    @ResultMap(value = "bookMap")
    AppleBook getOneBookBeanById(Long bookId);

    @Select({"SELECT * FROM apple_book WHERE category_id = #{category_id}"})
    @ResultMap(value = "bookMap")
    AppleBook getOneBookBeanByCategoryId(Long category_id);


    @Insert({"INSERT INTO apple_book(book_author,book_name, book_repertory, book_picture, book_isbn, book_intro, book_press, state, reader_id, category_id) " +
            "VALUES(#{bookAuthor}, #{bookName}, #{bookRepertory}, #{bookPicture}, #{bookIsbn}, #{bookIntro}, #{bookPress}, #{state}, #{readerId}, #{categoryId})"})
    @Options(useGeneratedKeys = true, keyProperty = "book_id")
    int insertBookBean(AppleBook appleBook);

    @Update("UPDATE apple_book SET book_author=#{bookAuthor},book_name=#{bookName}," +
            "book_repertory=#{bookRepertory}, book_picture=#{bookPicture}, book_isbn=#{bookIsbn}," +
            "book_intro=#{bookIntro}, book_press=#{bookPress}, state=#{state}, reader_id=#{readerId}, category_id=#{categoryId}" +
            " WHERE book_id =#{bookId}")
    int updateBookBean(AppleBook appleBook);

    @Update("UPDATE apple_book SET state=#{state} WHERE book_id = #{bookId}")
    int updateBookBeanByState(AppleBook appleBook);

    @Delete("DELETE FROM apple_book WHERE book_id =#{bookId}")
    int deleteBookBean(Long bookId);

    @Select("SELECT * FROM apple_book WHERE apple_book.book_name LIKE CONCAT('%',#{bookName},'%')")
    @ResultMap(value = "bookMap")
    List<AppleBook> getBookLikeNameList(String bookName);
}
