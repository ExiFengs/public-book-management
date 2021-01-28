package com.exi.bookmanagement.mapper;

import com.exi.bookmanagement.entity.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Copyright: Copyright (c) 2021 Asiainfo
 *
 * @ClassName: com.exi.bookmanagement.mapper.BookMapper
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: Fengsx
 * @date: 2021/1/20 17:37
 * <p>
 * Modification History:
 * Date       Author    Version    Description
 * ----------------------------------------------------------
 * 2021/1/20    Fengsx     v1.0.0      修改原因
 */

@CacheNamespace(blocking = true)
public interface BookMapper {

    @Select("SELECT * FROM book")
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
            //这里的 column 是映射回分类表的主键
            @Result(column="category_id",property="category",
                    one=@One(select="com.exi.bookmanagement.mapper.CategoryMapper.getOneCategoryById"))
    })
    List<Book> getAllBookBean();

    @Select("SELECT * FROM book WHERE book_id = #{bookId}")
    @ResultMap(value = "bookMap")
    Book getOneBookBeanById(Long bookId);

    @Select("SELECT * FROM book WHERE category_id = #{category_id}")
    @ResultMap(value = "bookMap")
    Book getOneBookBeanByCategoryId(Long category_id);


    @Insert("INSERT INTO book(book_author,book_name, book_repertory, book_picture, book_isbn, book_intro, book_press, category_id) " +
            "VALUES(#{bookAuthor}, #{bookName}, #{bookRepertory}, #{bookPicture}, #{bookIsbn}, #{bookIntro}, #{bookPress}, #{categoryId})")
    @Options(useGeneratedKeys = true, keyProperty = "book_id")
    int insertBookBean(Book book);

    @Update("UPDATE book SET book_author=#{bookAuthor},book_name=#{bookName}," +
            "book_repertory=#{bookRepertory}, book_picture=#{bookPicture}, book_isbn=#{bookIsbn}," +
            "book_intro=#{bookIntro}, book_press=#{bookPress}, category_id=#{categoryId}" +
            " WHERE book_id =#{bookId}")
    int updateBookBean(Book book);

    @Delete("DELETE FROM book WHERE book_id =#{bookId}")
    int deleteBookBean(Long bookId);

    @Select("SELECT * FROM book WHERE book.book_name LIKE CONCAT('%',#{bookName},'%')")
    @ResultMap(value = "bookMap")
    List<Book> getBookLikeNameList(String bookName);

}
