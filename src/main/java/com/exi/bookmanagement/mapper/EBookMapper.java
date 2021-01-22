package com.exi.bookmanagement.mapper;

import com.exi.bookmanagement.entity.Book;
import com.exi.bookmanagement.entity.Category;
import com.exi.bookmanagement.entity.EBook;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * Copyright: Copyright (c) 2021 Asiainfo
 *
 * @ClassName: com.exi.bookmanagement.mapper.EBookMapper
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: Fengsx
 * @date: 2021/1/20 20:18
 * <p>
 * Modification History:
 * Date       Author    Version    Description
 * ----------------------------------------------------------
 * 2021/1/20    Fengsx     v1.0.0      修改原因
 */
public interface EBookMapper {


    @Select("SELECT * FROM e_book")
    @Results(id = "EbookMap", value = {
            @Result(property = "eBookId",  column = "e_book_id"),
            @Result(property = "eBookAuthor", column = "e_book_author"),
            @Result(property = "eBookName", column = "e_book_name"),
            @Result(property = "eBookPicture", column = "e_book_picture"),
            @Result(property = "eBookIsbn", column = "e_book_isbn"),
            @Result(property = "eBookIntro", column = "e_book_intro"),
            @Result(property = "eBookPress", column = "e_book_press"),
            @Result(property = "eBookFileUrl", column = "e_book_file_url"),
            @Result(property = "eCategoryId", column = "e_category_id"),
            @Result(property="category",column="e_category_id",
                    one=@One(select="com.exi.bookmanagement.mapper.CategoryMapper.getOneCategoryById",fetchType= FetchType.EAGER))
    })
    List<EBook> getAllEBookBean();

    @Select("SELECT * FROM e_book WHERE e_book_id = #{eBookId}")
    @ResultMap(value = "EbookMap")
    EBook getOneEBookBeanById(Long eBookId);

    //一本书对应一个类别
    @Select("SELECT * FROM e_book WHERE e_book_id = #{eBookId}")
    @Results(value = {
            @Result(property = "eBookId",  column = "e_book_id"),
            @Result(property = "eBookAuthor", column = "e_book_author"),
            @Result(property = "eBookName", column = "e_book_name"),
            @Result(property = "eBookPicture", column = "e_book_picture"),
            @Result(property = "eBookIsbn", column = "e_book_isbn"),
            @Result(property = "eBookIntro", column = "e_book_intro"),
            @Result(property = "eBookPress", column = "e_book_press"),
            @Result(property = "eBookFileUrl", column = "e_book_file_url"),
            @Result(property = "eCategoryId", column = "e_category_id"),
            @Result(column="e_book_id",property="category",one=@One(select="com.exi.bookmanagement.mapper.CategoryMapper.getOneCategoryById",fetchType= FetchType.EAGER))
    })
    Book getOneEBookBeanByCategoryId(Long bookId);


    //与书类别 形成多对一关系
    @Select("SELECT * FROM e_book")
    @Results(value = {
            @Result(property = "eBookId",  column = "e_book_id"),
            @Result(property = "eBookAuthor", column = "e_book_author"),
            @Result(property = "eBookName", column = "e_book_name"),
            @Result(property = "eBookPicture", column = "e_book_picture"),
            @Result(property = "eBookIsbn", column = "e_book_isbn"),
            @Result(property = "eBookIntro", column = "e_book_intro"),
            @Result(property = "eBookPress", column = "e_book_press"),
            @Result(property = "eBookFileUrl", column = "e_book_file_url"),
            @Result(property = "categoryId", column = "category_id"),
            @Result(property="category",column="category_id",javaType= Category.class,
                    one=@One(select="com.exi.bookmanagement.mapper.CategoryMapper.getCategoryByIdForBook"))
    })
    List<EBook> getEBookBeanByCategory(Long categoryId);

    @Insert("INSERT INTO e_book(e_book_author, e_book_name, e_book_picture, e_book_isbn, e_book_intro, e_book_press, e_book_file_url, e_category_id) " +
            "VALUES(#{eBookAuthor}, #{eBookName}, #{eBookPicture}, #{eBookIsbn}, #{eBookIntro}, #{eBookPress}, #{eBookFileUrl}, #{eCategoryId})")
    @Options(useGeneratedKeys = true, keyProperty = "e_book_id")
    int insertEBookBean(EBook eBook);

    @Update("UPDATE e_book SET e_book_author=#{eBookAuthor},e_book_name=#{eBookName}," +
            "e_book_picture=#{eBookPicture}, e_book_isbn=#{eBookIsbn}, e_book_intro=#{eBookIntro}," +
            "e_book_press=#{eBookPress}, e_book_file_url=#{eBookFileUrl}, e_category_id=#{eCategoryId}" +
            " WHERE e_book_id =#{eBookId}")
    int updateEBookBean(EBook eBook);

    @Delete("DELETE FROM e_book WHERE e_book_id =#{eBookId}")
    int deleteEBookBean(Long eBookId);

    @Select("SELECT * FROM e_book WHERE e_book.e_book_name LIKE CONCAT('%',#{eBookName},'%')")
    @ResultMap(value = "EbookMap")
    List<EBook> getEBookLikeNameList(String eBookName);
}
